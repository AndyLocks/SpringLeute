package com.leute.spring_leute.service;

import com.leute.spring_leute.entity.*;
import com.leute.spring_leute.repository.LeuteDAO;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class DiscordService implements LeuteService {
    @Autowired
    private LeuteDAO repository;

    private Logger logger = LoggerFactory.getLogger(DiscordService.class);

    public ResponseEntity addDiscordAccount(DiscordAccountDTO discordAccountDTO, String password, String email) {
        try {
            Objects.requireNonNull(discordAccountDTO.getUserId());
            Objects.requireNonNull(discordAccountDTO.getNickname());
        }
        catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Account account = repository.getUserByEmail(email);

        if (account == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        if (!account.getPasswordHash().equals(DigestUtils.sha1Hex(password))) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).header("details", "password is not correct").build();
        }

        if (account.getDiscordAccount() != null) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .header("details", "discord account is already registered")
                    .build();
        }

        DiscordAccount discordAccount = new DiscordAccount();
        discordAccount.setUserId(discordAccountDTO.getUserId());
        discordAccount.setDescription(discordAccountDTO.getDescription());
        discordAccount.setNickname(discordAccountDTO.getNickname());
        discordAccount.setImageUrl(discordAccountDTO.getImageUrl());
        discordAccount.setName(discordAccountDTO.getName());

        account.setDiscordAccount(discordAccount);

        try {
            repository.saveUser(account);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).header("details", e.getMessage()).build();
        }

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<ResponseAccountDTO> getUserByDiscordId(String id) {
        return new ResponseEntity<>(repository.getAccountByDiscordId(id), HttpStatus.OK);
    }
}

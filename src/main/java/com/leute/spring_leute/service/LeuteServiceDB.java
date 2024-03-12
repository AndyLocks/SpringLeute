package com.leute.spring_leute.service;

import com.leute.spring_leute.entity.*;
import com.leute.spring_leute.repository.LeuteDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.regex.Pattern;

@Service
public class LeuteServiceDB implements LeuteService {
    @Autowired
    private LeuteDAO repository;

    @Override
    public ResponseAccountDTO getUserByNickname(String nickname) {
        Account user = repository.getUserByNickname(nickname);

        if(user == null)
            return null;

        DiscordAccount discordAccount = user.getDiscordAccount();

        String discordUserId = null;
        String imageUrl = null;
        String discordNickname = null;
        String discordDescription = null;
        String discordName = null;
        if (discordAccount != null) {
            discordUserId = discordAccount.getUserId();
            imageUrl = discordAccount.getImageUrl();
            discordNickname = discordAccount.getNickname();
            discordDescription = discordAccount.getDescription();
            discordName = discordAccount.getName();
        }


        return new ResponseAccountDTO(
                user.getNickname(),
                user.getRealName(),
                user.getDescription(),
                discordUserId,
                imageUrl,
                discordNickname,
                discordDescription,
                discordName,
                user.getEmail()
        );
    }

    @Override
    public ResponseEntity saveNewDiscordUser(AccountDTO dto) {
        if(!Pattern.compile("^(.+)@(\\S+)$").matcher(dto.getEmail()).matches()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).header("email", "error").build();
        }
        Account discordUser = new Account(
                0,
                dto.getNickname(),
                dto.getRealName(),
                dto.getDescription(),
                dto.getEmail(),
                null
        );
        try {
            this.repository.saveNewDiscordUser(discordUser);
            return ResponseEntity.ok().build();
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @Override
    public ResponseEntity addDiscordAccount(String nickname, DiscordAccountDTO discordAccountDTO) {
        try {
            Objects.requireNonNull(discordAccountDTO.getUserId());
            Objects.requireNonNull(discordAccountDTO.getNickname());
        }
        catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Account account = repository.getUserByNickname(nickname);

        DiscordAccount discordAccount = new DiscordAccount();
        discordAccount.setUserId(discordAccountDTO.getUserId());
        discordAccount.setDescription(discordAccountDTO.getDescription());
        discordAccount.setNickname(discordAccountDTO.getNickname());
        discordAccount.setImageUrl(discordAccountDTO.getImageUrl());
        discordAccount.setName(discordAccountDTO.getName());

        account.setDiscordAccount(discordAccount);

        repository.saveNewDiscordUser(account);

        return ResponseEntity.ok().build();
    }
}

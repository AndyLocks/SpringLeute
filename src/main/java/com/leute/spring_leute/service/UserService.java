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
import java.util.regex.Pattern;

@Service
public class UserService implements LeuteService {
    @Autowired
    private LeuteDAO repository;

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    public ResponseEntity<Boolean> chekLogin(String email, String password) {
        if(!Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$").matcher(email).matches()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).header("details", "invalid email").build();
        }

        Account account = repository.getUserByEmail(email);
        if (account == null) {
            return new ResponseEntity<>(false, HttpStatus.OK);
        }
        return new ResponseEntity<>(account.getPasswordHash().equals(DigestUtils.sha1Hex(password)), HttpStatus.OK);
    }

    public ResponseEntity<ResponseAccountDTO> getUserByNickname(String nickname) {
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


        return new ResponseEntity<>(new ResponseAccountDTO(
                user.getNickname(),
                user.getRealName(),
                user.getDescription(),
                discordUserId,
                imageUrl,
                discordNickname,
                discordDescription,
                discordName,
                user.getEmail()
        ), HttpStatus.OK);
    }

    public ResponseEntity saveNewUser(AccountDTO dto) {
        try {
            Objects.requireNonNull(dto.getEmail());
            Objects.requireNonNull(dto.getDescription());
            Objects.requireNonNull(dto.getNickname());
            Objects.requireNonNull(dto.getPassword());
        }
        catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }


        if(!Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$").matcher(dto.getEmail()).matches()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).header("details", "invalid email").build();
        }

        Account discordUser = new Account(
                0,
                dto.getNickname(),
                dto.getRealName(),
                dto.getDescription(),
                dto.getEmail(),
                null,
                dto.getPassword()
        );

        try {
            this.repository.saveUser(discordUser);
            return ResponseEntity.ok().build();
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    public ResponseEntity updateAccount(AccountUpdateDTO accountUpdateDTO, String nickname, String password) {
        Account account = repository.getUserByNickname(nickname);

        logger.info("Updating account: {}", account.toString());

        if (account == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        if (!account.getPasswordHash().equals(DigestUtils.sha1Hex(password))) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).header("details", "password is not correct").build();
        }

        if (accountUpdateDTO.getDescription() != null) account.setDescription(accountUpdateDTO.getDescription());
        if (accountUpdateDTO.getRealName() != null) account.setRealName(accountUpdateDTO.getRealName());

        repository.updateAccount(account);

        return ResponseEntity.ok().build();
    }

    public ResponseEntity deleteUser(String nickname, String password) {
        Account account = repository.getUserByNickname(nickname);
        if (account == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        if (!account.getPasswordHash().equals(DigestUtils.sha1Hex(password))) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).header("details", "password is not correct").build();
        }

        repository.deleteAccount(account);

        return ResponseEntity.ok().build();
    }
}

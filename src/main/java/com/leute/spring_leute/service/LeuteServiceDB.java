package com.leute.spring_leute.service;

import com.leute.spring_leute.entity.DiscordUser;
import com.leute.spring_leute.entity.DiscordUserDTO;
import com.leute.spring_leute.repository.LeuteDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class LeuteServiceDB implements LeuteService {
    @Autowired
    private LeuteDAO repository;

    @Override
    public DiscordUserDTO getUserByDiscordId(String id) {
        DiscordUser user = repository.getUserById(id);
        if (user == null)
            return null;
        return new DiscordUserDTO(
                user.getNickname(),
                user.getRealName(),
                user.getDescription(),
                user.getDiscordUserId()
        );
    }

    @Override
    public DiscordUserDTO getUserByNickname(String nickname) {
        DiscordUser user = repository.getUserByNickname(nickname);
        if(user == null)
            return null;
        return new DiscordUserDTO(
                user.getNickname(),
                user.getRealName(),
                user.getDescription(),
                user.getDiscordUserId()
        );
    }

    @Override
    public ResponseEntity saveNewDiscordUser(DiscordUserDTO user) {
        DiscordUser discordUser = new DiscordUser(
                0,
                user.getNickname(),
                user.getRealName(),
                user.getDescription(),
                user.getDiscordUserId()
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
    public ResponseEntity deleteUserById(String id) {
        try {
            this.repository.deleteUserById(id);
            return ResponseEntity.ok().build();
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}

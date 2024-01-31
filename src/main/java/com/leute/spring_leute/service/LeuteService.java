package com.leute.spring_leute.service;

import com.leute.spring_leute.entity.DiscordUserDTO;
import org.springframework.http.ResponseEntity;

public interface LeuteService {
    DiscordUserDTO getUserByDiscordId(String id);
    DiscordUserDTO getUserByNickname(String nickname);
    ResponseEntity saveNewDiscordUser(DiscordUserDTO user);
    ResponseEntity deleteUserById(String id);
}

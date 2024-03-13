package com.leute.spring_leute.service;

import com.leute.spring_leute.entity.*;
import org.springframework.http.ResponseEntity;

public interface LeuteService {
    ResponseAccountDTO getUserByNickname(String nickname);
    ResponseEntity saveNewDiscordUser(AccountDTO user);
    ResponseEntity addDiscordAccount(String nickname, DiscordAccountDTO discordAccountDTO);

    boolean chekLogin(LoginDTO login);
}

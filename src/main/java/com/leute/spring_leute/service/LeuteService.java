package com.leute.spring_leute.service;

import com.leute.spring_leute.entity.AccountDTO;
import com.leute.spring_leute.entity.DiscordAccountDTO;
import com.leute.spring_leute.entity.ResponseAccountDTO;
import org.springframework.http.ResponseEntity;

public interface LeuteService {
    ResponseAccountDTO getUserByNickname(String nickname);
    ResponseEntity saveNewDiscordUser(AccountDTO user);
    ResponseEntity addDiscordAccount(String nickname, DiscordAccountDTO discordAccountDTO);
}

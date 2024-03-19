package com.leute.spring_leute.service;

import com.leute.spring_leute.entity.*;
import org.springframework.http.ResponseEntity;

public interface LeuteService {
    ResponseEntity<ResponseAccountDTO> getUserByNickname(String nickname);
    ResponseEntity saveNewDiscordUser(AccountDTO user);
    ResponseEntity addDiscordAccount(DiscordAccountDTO discordAccountDTO, String password, String email);

    ResponseEntity<Boolean> chekLogin(String email, String password);

    ResponseEntity deleteUser(String nickname, String password);

    ResponseEntity updateAccount(AccountUpdateDTO accountUpdateDTO,String nickname, String password);

    ResponseEntity<ResponseAccountDTO> getUserByDiscordId(String id);
}

package com.leute.spring_leute.controller;

import com.leute.spring_leute.entity.*;
import com.leute.spring_leute.service.LeuteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "main_methods")
@RestController
@RequestMapping("/api/v1/user")
public class LeuteController {

    @Autowired
    private LeuteService service;

    @Operation(
            summary = "Adds a new user"
    )
    @PostMapping("registration")
    public ResponseEntity saveNewDiscordUser(@RequestBody AccountDTO user) {
        return this.service.saveNewDiscordUser(user);
    }

    @Operation(
            summary = "Find user by nickname"
    )
    @GetMapping("{nickname}")
    public ResponseEntity<ResponseAccountDTO> findUserByNickname(@PathVariable String nickname) {
        return this.service.getUserByNickname(nickname);
    }

    @PostMapping("{nickname}/add_discord_account")
    public ResponseEntity addDiscordAccount(@PathVariable String nickname, @RequestBody DiscordAccountDTO discordAccountDTO, @RequestParam String password) {
        return this.service.addDiscordAccount(nickname, discordAccountDTO, password);
    }

    @GetMapping("check_login")
    public ResponseEntity<Boolean> checkLogin(@RequestParam String email, @RequestParam String password) {
        return this.service.chekLogin(email, password);
    }

    @DeleteMapping("delete/{nickname}")
    public ResponseEntity deleteUser(@PathVariable String nickname, @RequestParam String password) {
        return this.service.deleteUser(nickname, password);
    }

    @PutMapping("update/{nickname}")
    public ResponseEntity updateAccount(@RequestBody AccountUpdateDTO accountUpdateDTO, @PathVariable String nickname, @RequestParam String password) {
        return this.service.updateAccount(accountUpdateDTO, nickname, password);
    }

    @GetMapping("discord_id/{id}")
    public ResponseEntity<ResponseAccountDTO> getAccountByDiscordId(@PathVariable String id) {
        return this.service.getUserByDiscordId(id);
    }
}

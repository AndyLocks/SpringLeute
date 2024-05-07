package com.leute.spring_leute.controller;

import com.leute.spring_leute.entity.*;
import com.leute.spring_leute.service.DiscordService;
import com.leute.spring_leute.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "main_methods")
@RestController
@CrossOrigin
@RequestMapping("/api/v1/user")
public class LeuteController {
    @Autowired
    private DiscordService discordService;

    @Autowired
    private UserService userService;

    @Operation(
            summary = "Adds a new user"
    )
    @PostMapping("registration")
    public ResponseEntity saveNewUser(@RequestBody AccountDTO user) {
        return this.userService.saveNewUser(user);
    }

    @Operation(
            summary = "Find user by nickname"
    )
    @GetMapping("{nickname}")
    public ResponseEntity<ResponseAccountDTO> findUserByNickname(@PathVariable String nickname) {
        return this.userService.getUserByNickname(nickname);
    }

    @PostMapping("add_discord_account")
    public ResponseEntity addDiscordAccount(@RequestBody DiscordAccountDTO discordAccountDTO, @RequestParam String email, @RequestParam String password) {
        return this.discordService.addDiscordAccount(discordAccountDTO, password, email);
    }

    @GetMapping("check_login")
    public ResponseEntity<Boolean> checkLogin(@RequestParam String email, @RequestParam String password) {
        return this.userService.chekLogin(email, password);
    }

    @DeleteMapping("delete/{nickname}")
    public ResponseEntity deleteUser(@PathVariable String nickname, @RequestParam String password) {
        return this.userService.deleteUser(nickname, password);
    }

    @PutMapping("update/{nickname}")
    public ResponseEntity updateAccount(@RequestBody AccountUpdateDTO accountUpdateDTO, @PathVariable String nickname, @RequestParam String password) {
        return this.userService.updateAccount(accountUpdateDTO, nickname, password);
    }

    @GetMapping("discord_id/{id}")
    public ResponseEntity<ResponseAccountDTO> getAccountByDiscordId(@PathVariable String id) {
        return this.discordService.getUserByDiscordId(id);
    }
}

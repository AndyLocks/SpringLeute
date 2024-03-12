package com.leute.spring_leute.controller;

import com.leute.spring_leute.entity.AccountDTO;
import com.leute.spring_leute.entity.DiscordAccountDTO;
import com.leute.spring_leute.entity.ResponseAccountDTO;
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
    @PostMapping("save")
    public ResponseEntity saveNewDiscordUser(@RequestBody AccountDTO user) {
        return this.service.saveNewDiscordUser(user);
    }

    @Operation(
            summary = "Find user by nickname"
    )
    @GetMapping("{nickname}")
    public ResponseAccountDTO findUserByNickname(@PathVariable String nickname) {
        return this.service.getUserByNickname(nickname);
    }

    @PostMapping("{nickname}/add_discord_account")
    public ResponseEntity addDiscordAccount(@PathVariable String nickname, @RequestBody DiscordAccountDTO discordAccountDTO) {
        return this.service.addDiscordAccount(nickname, discordAccountDTO);
    }
}

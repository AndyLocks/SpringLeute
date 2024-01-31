package com.leute.spring_leute.controller;

import com.leute.spring_leute.entity.DiscordUserDTO;
import com.leute.spring_leute.service.LeuteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "main_methods")
@RestController
@RequestMapping("/api/v1/discord")
public class LeuteController {

    @Autowired
    private LeuteService service;

    @Operation(
            summary = "Adds a new user"
    )
    @PostMapping("save")
    public ResponseEntity saveNewDiscordUser(@RequestBody DiscordUserDTO user) {
        return this.service.saveNewDiscordUser(user);
    }

    @Operation(
            summary = "Find user by id",
            description = "Find user by discord user id"
    )
    @GetMapping("user/id/{id}")
    public DiscordUserDTO findUserById(@PathVariable String id) {
        return this.service.getUserByDiscordId(id);
    }

    @Operation(
            summary = "Find user by nickname"
    )
    @GetMapping("user/nickname/{nickname}")
    public DiscordUserDTO findUserByNickname(@PathVariable String nickname) {
        return this.service.getUserByNickname(nickname);
    }

    @Operation(
            summary = "Delete user by id",
            description = "Delete user by discord id"
    )
    @DeleteMapping("user/delete/id/{id}")
    public ResponseEntity deleteUserById(@PathVariable String id) {
        return this.service.deleteUserById(id);
    }
}

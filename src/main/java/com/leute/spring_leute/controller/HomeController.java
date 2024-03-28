package com.leute.spring_leute.controller;

import com.leute.spring_leute.entity.AccountDTO;
import com.leute.spring_leute.service.LeuteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private LeuteService service;

    @GetMapping
    public String index() {
        return "index";
    }

    @PostMapping("/new-user")
    public String newAccount(@ModelAttribute AccountDTO accountDTO) {
        this.service.saveNewDiscordUser(accountDTO);

        return "redirect:/new_account_message";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("account", new AccountDTO());
        return "register";
    }

    @GetMapping("/new_account_message")
    public String newAccountMessage() {
        return "new_account_message";
    }
}

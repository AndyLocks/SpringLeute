package com.leute.spring_leute.controller;

import com.leute.spring_leute.entity.Account;
import com.leute.spring_leute.entity.AccountDTO;
import com.leute.spring_leute.entity.DeleteAccount;
import com.leute.spring_leute.entity.SearchAccount;
import com.leute.spring_leute.repository.LeuteDAO;
import com.leute.spring_leute.service.LeuteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private LeuteService service;

    @Autowired
    private LeuteDAO repository;

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

    @GetMapping("/search")
    public String search(Model model) {
        model.addAttribute("account", new SearchAccount(null));
        return "search";
    }

    @GetMapping("user/{nickname}")
    public String showAccountData(Model model, @PathVariable String nickname) {
        Account account = this.repository.getUserByNickname(nickname);

        if (account == null) {
            return "user_not_found";
        }

        model.addAttribute("nickname", account.getNickname());
        model.addAttribute("real_name", account.getRealName());
        model.addAttribute("description", account.getDescription());

        if (account.getDiscordAccount() == null) {
            return "account";
        }
        else {
            model.addAttribute("discord_nickname", account.getDiscordAccount().getNickname());
            model.addAttribute("image", account.getDiscordAccount().getImageUrl());
            return "account_and_discord_info";
        }
    }

    @PostMapping("/search")
    public String postSearch(@ModelAttribute SearchAccount searchAccount) {
        String nickname = searchAccount.nickname();

        Account account = this.repository.getUserByNickname(nickname);

        if (account == null)
            return "user_not_found";

        return "redirect:/user/" + nickname;
    }

    @GetMapping("delete_account")
    public String deleteAccount(Model model) {
        model.addAttribute("account", new DeleteAccount(null, null));

        return "delete";
    }

    @PostMapping("delete_account")
    public String deleteAccountPost(@ModelAttribute DeleteAccount account) {
        this.service.deleteUser(account.nickname(), account.password());

        return "redirect:/";
    }
}

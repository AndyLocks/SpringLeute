package com.leute.spring_leute.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "account")
public class Account {
    public Account() {}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(name = "real_name")
    private String realName;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false, unique = true)
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "discord_account_id", referencedColumnName = "id")
    private DiscordAccount discordAccount = null;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public DiscordAccount getDiscordAccount() {
        return discordAccount;
    }

    public void setDiscordAccount(DiscordAccount discordAccount) {
        this.discordAccount = discordAccount;
    }

    public Account(int id, String nickname, String realName, String description, String email, DiscordAccount discordAccount) {
        this.id = id;
        this.nickname = nickname;
        this.realName = realName;
        this.description = description;
        this.email = email;
        this.discordAccount = discordAccount;
    }
}

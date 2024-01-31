package com.leute.spring_leute.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "discord_user")
public class DiscordUser {
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
    private String discordUserId;

    public DiscordUser(int id, String nickname, String realName, String description, String discordUserId) {
        this.id = id;
        this.nickname = nickname;
        this.realName = realName;
        this.description = description;
        this.discordUserId = discordUserId;
    }

    public DiscordUser() {}

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

    public String getDiscordUserId() {
        return discordUserId;
    }

    public void setDiscordUserId(String discordUserId) {
        this.discordUserId = discordUserId;
    }
}

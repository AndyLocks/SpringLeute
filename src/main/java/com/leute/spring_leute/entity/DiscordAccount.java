package com.leute.spring_leute.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "discord_account")
public class DiscordAccount {
    public DiscordAccount() {}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false, name = "user_id")
    private String userId;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(unique = true, nullable = false)
    private String nickname;

    @Column
    private String name;

    @Column
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DiscordAccount(int id, String userId, String imageUrl, String nickname, String name, String description) {
        this.id = id;
        this.userId = userId;
        this.imageUrl = imageUrl;
        this.nickname = nickname;
        this.name = name;
        this.description = description;
    }
}

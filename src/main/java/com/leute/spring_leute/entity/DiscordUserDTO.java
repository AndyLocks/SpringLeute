package com.leute.spring_leute.entity;

public class DiscordUserDTO {
    private String nickname;
    private String realName;
    private String description;
    private String discordUserId;

    public DiscordUserDTO(String nickname, String realName, String description, String discordUserId) {
        this.nickname = nickname;
        this.realName = realName;
        this.description = description;
        this.discordUserId = discordUserId;
    }

    public DiscordUserDTO() {}

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

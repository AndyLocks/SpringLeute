package com.leute.spring_leute.entity;

import org.yaml.snakeyaml.extensions.compactnotation.PackageCompactConstructor;

import java.util.Objects;

public class ResponseAccountDTO {
    /**
     *
     * @param account
     * @throws NullPointerException if account is null
     * @return
     */
    public static ResponseAccountDTO fromAccount(Account account) {
        Objects.requireNonNull(account);

        return new ResponseAccountDTO(
                account.getNickname(),
                account.getRealName(),
                account.getDescription(),
                account.getDiscordAccount().getUserId(),
                account.getDiscordAccount().getImageUrl(),
                account.getDiscordAccount().getNickname(),
                account.getDiscordAccount().getDescription(),
                account.getDiscordAccount().getName(),
                account.getEmail()
        );
    }
    public ResponseAccountDTO() {}
    private String nickname;
    private String realName;
    private String description;
    private String discord_user_id;
    private String image_url;
    private String discord_nickname;
    private String discord_description;
    private String discord_name;
    private String email;

    public ResponseAccountDTO(String nickname, String realName, String description, String discordUserId, String imageUrl, String discordNickname, String discordDescription, String discordName, String email) {
        this.nickname = nickname;
        this.realName = realName;
        this.description = description;
        this.discord_user_id = discordUserId;
        this.image_url = imageUrl;
        this.discord_nickname = discordNickname;
        this.discord_description = discordDescription;
        this.discord_name = discordName;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiscordName() {
        return discord_name;
    }

    public void setDiscordName(String discordName) {
        this.discord_name = discordName;
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

    public String getDiscord_user_id() {
        return discord_user_id;
    }

    public void setDiscord_user_id(String discord_user_id) {
        this.discord_user_id = discord_user_id;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getDiscord_nickname() {
        return discord_nickname;
    }

    public void setDiscord_nickname(String discord_nickname) {
        this.discord_nickname = discord_nickname;
    }

    public String getDiscord_description() {
        return discord_description;
    }

    public void setDiscord_description(String discord_description) {
        this.discord_description = discord_description;
    }
}

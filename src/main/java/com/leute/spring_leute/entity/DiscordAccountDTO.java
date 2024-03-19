package com.leute.spring_leute.entity;

public class DiscordAccountDTO {
    public DiscordAccountDTO() {}
    private String user_id;

    /**
     * Can be null.
     */
    private String image_url;
    private String nickname;

    /**
     * Can be null.
     */
    private String name;

    /**
     * Can be null.
     */
    private String description;

    public DiscordAccountDTO(String userId, String imageUrl, String nickname, String name, String description) {
        this.user_id = userId;
        this.image_url = imageUrl;
        this.nickname = nickname;
        this.name = name;
        this.description = description;
    }

    public String getUserId() {
        return user_id;
    }

    public void setUserId(String userId) {
        this.user_id = userId;
    }

    public String getImageUrl() {
        return image_url;
    }

    public void setImageUrl(String imageUrl) {
        this.image_url = imageUrl;
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
}

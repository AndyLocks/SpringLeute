package com.leute.spring_leute.entity;

public class LoginDTO {
    public LoginDTO() {}
    private String nickname;
    private String password;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LoginDTO(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
    }
}

package com.leute.spring_leute.entity;

public class AccountUpdateDTO {
    public AccountUpdateDTO() {}
    private String description;
    private String realName;

    public AccountUpdateDTO(String description, String realName) {
        this.description = description;
        this.realName = realName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }
}

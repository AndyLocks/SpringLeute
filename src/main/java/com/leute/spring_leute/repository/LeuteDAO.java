package com.leute.spring_leute.repository;

import com.leute.spring_leute.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LeuteDAO {

    @Autowired
    private LeuteRepo repo;

    public void saveNewDiscordUser(Account user) {
        repo.save(user);
    }

    public Account getUserByNickname(String nickname) {
        return repo.getUserByNickname(nickname);
    }
}

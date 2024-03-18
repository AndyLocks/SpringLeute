package com.leute.spring_leute.repository;

import com.leute.spring_leute.entity.Account;
import com.leute.spring_leute.entity.ResponseAccountDTO;
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

    public void deleteAccount(Account account) {
        repo.delete(account);
    }

    public void updateAccount(Account account) {
        repo.save(account);
    }

    public ResponseAccountDTO getAccountByDiscordId(String id) {
        try {
            return ResponseAccountDTO.fromAccount(repo.findAccountByDiscordId(id));
        }
        catch (NullPointerException e) {
            return null;
        }
    }
}

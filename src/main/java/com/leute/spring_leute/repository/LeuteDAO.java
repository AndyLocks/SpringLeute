package com.leute.spring_leute.repository;

import com.leute.spring_leute.entity.DiscordUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LeuteDAO {

    @Autowired
    private LeuteRepo repo;

    public void saveNewDiscordUser(DiscordUser user) {
        repo.save(user);
    }

    public DiscordUser getUserById(String id) {
        return repo.getUserById(id);
    }

    public DiscordUser getUserByNickname(String nickname) {
        return repo.getUserByNickname(nickname);
    }

    public void deleteUserById(String id) throws IllegalArgumentException {
        DiscordUser user = this.getUserById(id);
        if (user == null)
            throw new IllegalArgumentException();
        repo.delete(user);
    }
}

package com.leute.spring_leute.repository;

import com.leute.spring_leute.entity.Account;
import com.leute.spring_leute.entity.ResponseAccountDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LeuteDAO {

    @Autowired
    private LeuteRepo repo;

    @Autowired
    private RedisCacheRepo redis;

    private final Logger logger = LoggerFactory.getLogger(LeuteDAO.class);

    public void saveUser(Account user) {
        repo.save(user);
        logger.info("Save new user: " + user.toString());
    }

    public Account getUserByNickname(String nickname) {
        logger.info("Searching user by nickname: {}", nickname);
        Account account = redis.getUser(nickname);

        if (account == null) {
            account = repo.getUserByNickname(nickname);
            if (account == null) return null;
            logger.info("User: {} was not found in the cache", nickname);
            redis.saveUser(account);
        }
        else {
            logger.info("User: {} was found in the cache", nickname);
        }

        return account;
    }

    public void deleteAccount(Account account) {
        repo.delete(account);
        logger.info("User {} was deleted", account.getNickname());
        redis.deleteCache(account);
    }

    public void updateAccount(Account account) {
        repo.save(account);
        logger.info("User {} was updated", account.getNickname());
        redis.deleteCache(account);
    }

    public ResponseAccountDTO getAccountByDiscordId(String id) {
        logger.info("Searching for a user by discord id {}", id);
        try {
            Account account = redis.getUserByDiscordAccount(id);

            if (account == null) {
                account = repo.findAccountByDiscordId(id);
                if (account == null) return null;
                logger.info("User {} was not found in cache", account.getNickname());
                redis.saveUserByDiscordId(account);
            }
            else {
                logger.info("User {} was found in cache", account.getNickname());
            }

            return ResponseAccountDTO.fromAccount(account);
        }
        catch (NullPointerException e) {
            return null;
        }
    }

    public Account getUserByEmail(String email) {
        logger.info("Searching for user by email {}", email);

        Account account = redis.getUserByEmail(email);

        if (account == null) {
            account = repo.getUserByEmail(email);
            if (account == null) return null;
            logger.info("User: {} was not found in the cache", account.getNickname());
            redis.saveUserByEmail(account);
        }
        else {
            logger.info("User: {} was found in the cache", account.getNickname());
        }

        return account;
    }
}

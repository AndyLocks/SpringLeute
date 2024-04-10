package com.leute.spring_leute.repository;

import com.leute.spring_leute.entity.Account;
import com.leute.spring_leute.entity.DiscordAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Repository
public class RedisCacheRepo {

    @Autowired
    private JedisPool pool;

    private final Logger logger = LoggerFactory.getLogger(RedisCacheRepo.class);

    public void setPool(JedisPool pool) {
        this.pool = pool;
    }

    public boolean exists(String nickname) {
        try(Jedis jedis = pool.getResource()) {
            return jedis.exists(nickname);
        }
    }

    private Account getAccount(Map<String, String> account) {
        int id = Integer.parseInt(account.get("id"));
        String accountNickname = account.get("nickname");
        String description = account.get("description");
        String realName = account.get("real_name").equals("null") ? null : account.get("real_name");
        String passwordHash = account.get("password_hash");
        String email = account.get("email");

        DiscordAccount discordAccount;
        if (account.get("has_discord_account").equals("true")) {
            int dsId = Integer.parseInt(account.get("ds_id"));
            String discordId = account.get("discord_id");
            String discordNickname = account.get("discord_nickname");
            String imageUrl = account.get("image_url");
            String discordDescription = account.get("discord_description");
            String discordName = account.get("discord_name");

            discordAccount = new DiscordAccount (
                    dsId, discordId, imageUrl, discordNickname, discordName, discordDescription
            );
        }
        else {
            discordAccount = null;
        }


        return Account.createAccountWithPasswordHash(
                id, accountNickname, realName, description, email, discordAccount, passwordHash
        );
    }

    private Map<String, String> getMap(Account account) {
        Map<String, String> accountMap = new HashMap<String, String>();
        accountMap.put("id", Integer.toString(account.getId()));
        accountMap.put("nickname", account.getNickname());
        accountMap.put("real_name", account.getRealName() == null ? "null" : account.getRealName());
        accountMap.put("description", account.getDescription());
        accountMap.put("email", account.getEmail());
        accountMap.put("password_hash", account.getPasswordHash());

        if (account.getDiscordAccount() != null) {
            accountMap.put("has_discord_account", "true");
            accountMap.put("ds_id", Integer.toString(account.getDiscordAccount().getId()));
            accountMap.put("discord_id", account.getDiscordAccount().getUserId());
            accountMap.put("image_url", account.getDiscordAccount().getImageUrl());
            accountMap.put("discord_nickname", account.getDiscordAccount().getNickname());
            accountMap.put("discord_name", account.getDiscordAccount().getName());
            accountMap.put("discord_description", account.getDiscordAccount().getDescription());
        }
        else {
            accountMap.put("has_discord_account", "false");
        }

        return accountMap;
    }

    public void saveUser(Account account) {
        Objects.requireNonNull(account);
        try (Jedis jedis = pool.getResource()) {

            if (jedis.exists(account.getNickname())) {
                return;
            }

            jedis.hset(account.getNickname(), getMap(account));
        }
        logger.info("User {} has been cached", account.getNickname());
    }

    public Account getUser(String nickname) {
        try (Jedis jedis = pool.getResource()) {

            if (!jedis.exists(nickname)) {
                return null;
            }

            return getAccount(jedis.hgetAll(nickname));
        }
    }

    public void saveUserByDiscordId(Account account) {
        Objects.requireNonNull(account);
        try (Jedis jedis = pool.getResource()) {

            if (jedis.exists(account.getDiscordAccount().getUserId())) {
                return;
            }

            jedis.hset(account.getDiscordAccount().getUserId(), getMap(account));
        }
    }

    public Account getUserByDiscordAccount(String discordUserId) {
        try (Jedis jedis = pool.getResource()) {

            if (!jedis.exists(discordUserId)) {
                return null;
            }

            return getAccount(jedis.hgetAll(discordUserId));
        }
    }

    public void saveUserByEmail(Account account) {
        Objects.requireNonNull(account);
        try (Jedis jedis = pool.getResource()) {

            if (jedis.exists(account.getEmail())) {
                return;
            }

            jedis.hset(account.getEmail(), getMap(account));
        }
    }

    public Account getUserByEmail(String email) {
        try (Jedis jedis = pool.getResource()) {
            if (!jedis.exists(email)) {
                return null;
            }

            return getAccount(jedis.hgetAll(email));
        }
    }

    public void deleteCache(Account account) {
        try (Jedis jedis = pool.getResource()){
            jedis.del(account.getNickname());
            jedis.del(account.getEmail());

            if (account.getDiscordAccount() != null) {
                jedis.del(account.getDiscordAccount().getUserId());
            }
        }
        logger.info("User {} cache deleted", account.getNickname());
    }
}

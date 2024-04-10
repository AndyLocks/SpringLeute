package com.leute.spring_leute.repository;

import com.leute.spring_leute.entity.Account;
import com.leute.spring_leute.entity.DiscordAccount;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.JedisPool;

import static org.junit.jupiter.api.Assertions.*;


//I know this test is bad :<
class RedisCacheRepoTest {

    private static RedisCacheRepo repo = new RedisCacheRepo(); {
        repo.setPool(new JedisPool("localhost", 6379));
    }
    @Test
    public void addTest() {
        Account account = new Account(0, "test", "Test name", "Test desc", "test@test.com", new DiscordAccount(0, "3287375discord id", "https://discord.com/image", "testdiscordnickname", "discord name", ":D"), "lJEnd982H3l2dD8j2JDKD83dkDOIk3k83lKd98s3kJ0fadsSdf");
        repo.saveUser(account);
    }

    @Test
    public void getTest() {
        System.out.println(
                repo.getUser("test").toString()
        );
    }

    @Test
    public void existTest() {
        assertNull(repo.getUser("aa;lskdfjaskldj"));
    }

    @Test
    public void nullTest() {
        Account account = new Account(0, "test2", "Test name", "Test desc", "test@test.com", null, "lJEnd982H3l2dD8j2JDKD83dkDOIk3k83lKd98s3kJ0fadsSdf");
        repo.saveUser(account);
        System.out.println(
                repo.getUser("test2").toString()
        );
        assertNull(repo.getUser("test2").getDiscordAccount());
    }
}
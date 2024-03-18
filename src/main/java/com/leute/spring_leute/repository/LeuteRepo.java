package com.leute.spring_leute.repository;

import com.leute.spring_leute.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LeuteRepo extends JpaRepository<Account, Integer> {
    @Query(value = "select * from account where nickname = ?1", nativeQuery = true)
    Account getUserByNickname(String nickname);

    @Query(value = "select account.*, discord_account.user_id from account join discord_account on account.discord_account_id = discord_account.id where discord_account.user_id = ?1", nativeQuery = true)
    Account findAccountByDiscordId(String id);
}

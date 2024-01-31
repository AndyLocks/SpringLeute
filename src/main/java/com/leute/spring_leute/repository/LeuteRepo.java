package com.leute.spring_leute.repository;

import com.leute.spring_leute.entity.DiscordUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LeuteRepo extends JpaRepository<DiscordUser, Integer> {
    @Query(value = "select * from discord_user where discord_user_id = ?1", nativeQuery = true)
    DiscordUser getUserById(String id);

    @Query(value = "select * from discord_user where nickname = ?1", nativeQuery = true)
    DiscordUser getUserByNickname(String nickname);

    @Modifying
    @Query(value = "delete from discord_user where discord_user_id = ?1", nativeQuery = true)
    void deleteUserById(String id);

    @Modifying
    @Query(value = "delete from discord_user where nickname = ?1", nativeQuery = true)
    void deleteUserByNickname(String nickname);
}

package com.swu.umcmc.repository;

import com.swu.umcmc.domain.Follow;
import com.swu.umcmc.domain.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    @Query("SELECT f.follower.id FROM Follow f WHERE f.following.id = :myId AND  AND f.createdAt > :cursor ORDER BY f.createdAt DESC")
    List<Long> findFollowerIdsByFollowingId(@Param("nickName")String nickName, @Param("myId") Long myId, @Param("cursor") LocalDateTime cursor, Pageable pageable);

}

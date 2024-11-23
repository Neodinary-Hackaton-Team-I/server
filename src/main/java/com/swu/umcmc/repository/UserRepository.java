package com.swu.umcmc.repository;

import com.swu.umcmc.domain.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    boolean existsByNickname(String nickname);
    Optional<User> findByEmail(String email);
    Optional<User> findById(Long id);



    //(첫 검색용) nickname기준으로 user검색하는 메서드
    @Query("SELECT u FROM User u " +
            "WHERE u.nickname LIKE %:nickName% " +
            "ORDER BY u.createdAt DESC")
    Slice<User> findUsersByNameDesc(@Param("nickName") String nickName,
                                                              Pageable pageable);

    //nickname기준으로 user검색하는 메서드 (커서기반)
    @Query("SELECT u FROM User u " +
            "WHERE u.nickname LIKE %:nickName% " +
            "AND u.createdAt < :cursor " +
            "ORDER BY u.createdAt DESC")
    Slice<User> findUsersByNameDescWithCursor(@Param("nickName") String nickName,
                                                                        @Param("cursor") LocalDateTime cursor,
                                                                        Pageable pageable);
}
package com.swu.umcmc.repository;

import com.swu.umcmc.domain.Follow;
import com.swu.umcmc.domain.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

    import java.time.LocalDateTime;
    import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {

        //(첫 검색용) 내가 팔로우 하는 사람
        @Query("SELECT f.follower FROM Follow f " +
                "WHERE f.following.id = :myId " +
                "ORDER BY f.createdAt DESC")
        Slice<User> findFollowersByFollowingIdDesc(@Param("myId") Long myId,
                                                   Pageable pageable);


        //내가 팔로우 하는 사람 (커서 기반)

        @Query("SELECT f.follower FROM Follow f " +
                "WHERE f.following.id = :myId " +
                "AND f.createdAt < :cursor " +
                "ORDER BY f.createdAt DESC")
        Slice<User> findFollowersByFollowingIdDescWithCursor(@Param("myId") Long myId,
                                                             @Param("cursor") LocalDateTime cursor,
                                                             Pageable pageable);


        //(첫 검색용) 내가 팔로우 하는 사람 중에서 nickname기준으로 검색하는 메서드
        @Query("SELECT f.follower FROM Follow f " +
                "WHERE f.following.id = :myId " +
                "AND f.follower.nickname LIKE %:nickName% " +
                "ORDER BY f.createdAt DESC")
        Slice<User> findFollowersByFollowerNameAndFollowingIdDesc(@Param("nickName") String nickName,
                                                   @Param("myId") Long myId,
                                                   Pageable pageable);


        //내가 팔로우 하는 사람 중에서 nickname기준으로 검색하는 메서드 (커서기반)

        @Query("SELECT f.follower FROM Follow f " +
                "WHERE f.following.id = :myId " +
                "AND f.follower.nickname LIKE %:nickName% " +
                "AND f.createdAt < :cursor " +
                "ORDER BY f.createdAt DESC")
        Slice<User> findFollowersByFollowerNameAndFollowingIdDescWithCursor(@Param("nickName") String nickName,
                                               @Param("myId") Long myId,
                                               @Param("cursor") LocalDateTime cursor,
                                               Pageable pageable);

        boolean existsByFollowing_IdAndFollower_Id(Long myId, Long followerId);

        boolean existsByFollowerAndFollowing(User follower, User following);
        Optional<Follow> findByFollowerAndFollowing(User follower, User following);





    //(첫 검색용) 내가 팔로우 하는 사람
    @Query("SELECT f.following FROM Follow f " +
            "WHERE f.follower.id = :myId " +
            "ORDER BY f.createdAt DESC")
    Slice<User> findFollowingsByFollowingIdDesc(@Param("myId") Long myId,
                                               Pageable pageable);
    //내가 팔로우 하는 사람 (커서 기반)
    @Query("SELECT f.following FROM Follow f " +
            "WHERE f.follower.id = :myId " +
            "AND f.createdAt < :cursor " +
            "ORDER BY f.createdAt DESC")
    Slice<User> findFollowingsByFollowingIdDescWithCursor(@Param("myId") Long myId,
                                                         @Param("cursor") LocalDateTime cursor,
                                                         Pageable pageable);




}

package com.swu.umcmc.domain;

import com.swu.umcmc.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "follow")
public class Follow extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "follow_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "follower_id")
    private User follower;  // 팔로우 당하는 사용자

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "following_id")
    private User following;  // 팔로우를 하는 사용자

    // 연관관계 편의 메서드
    public void setFollowRelation(User follower, User following) {
        this.follower = follower;
        this.following = following;
        follower.getFollowings().add(this);
        following.getFollowers().add(this);
    }
}
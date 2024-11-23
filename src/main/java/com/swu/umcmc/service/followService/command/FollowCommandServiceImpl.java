package com.swu.umcmc.service.followService.command;

import com.swu.umcmc.config.exception.CustomException;
import com.swu.umcmc.config.exception.ErrorCode;
import com.swu.umcmc.domain.Follow;
import com.swu.umcmc.domain.User;
import com.swu.umcmc.repository.FollowRepository;
import com.swu.umcmc.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.swu.umcmc.dto.follow.FollowResponseDto;

@Service
@Transactional
@RequiredArgsConstructor
public class FollowCommandServiceImpl implements FollowCommandService {
    public final FollowRepository followRepository;
    public final UserRepository userRepository;

    // 팔로우
    @Transactional
    public FollowResponseDto.FollowResponse follow(Long followerId, Long followingId) {
        // 팔로우하는 사용자와 팔로우 당하는 사용자 조회
        User follower = userRepository.findById(followerId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        User following = userRepository.findById(followingId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        // 자기 자신을 팔로우하는지 확인
        if (follower.getId().equals(following.getId())) {
            throw new CustomException(ErrorCode.SELF_FOLLOW_NOT_ALLOWED);
        }

        // 이미 팔로우하고 있는지 확인
        if (followRepository.existsByFollowerAndFollowing(follower, following)) {
            throw new CustomException(ErrorCode.ALREADY_FOLLOWING);
        }

        // 팔로우 관계 생성
        Follow follow = Follow.builder()
                .follower(follower)
                .following(following)
                .build();

        followRepository.save(follow);

        return FollowResponseDto.FollowResponse.builder()
                .followUserId(following.getId())
                .followNickname(following.getNickname())
                .build();
    }

    // 팔로우 취소
    @Transactional
    public void unfollow(Long followerId, Long followingId) {
        User follower = userRepository.findById(followerId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        User following = userRepository.findById(followingId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        Follow follow = followRepository.findByFollowerAndFollowing(follower, following)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOLLOWING));

        followRepository.delete(follow);
    }
}

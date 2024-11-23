package com.swu.umcmc.service.followService.query;

import com.swu.umcmc.dto.follow.FollowResponseDto;

import java.time.LocalDateTime;

public interface FollowQueryService {
    public FollowResponseDto.FollowingSliceDTO searchByNickNameInFollowing(String name, Long userId, LocalDateTime cursor, int offset);
    public FollowResponseDto.FollowerSliceDTO getFollowers(Long userId, LocalDateTime cursor, int offset);
    public FollowResponseDto.FollowingSliceDTO getFollowings(Long userId, LocalDateTime cursor, int offset);
}

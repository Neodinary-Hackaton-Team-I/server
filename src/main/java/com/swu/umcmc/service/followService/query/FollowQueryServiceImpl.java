package com.swu.umcmc.service.followService.query;

import com.swu.umcmc.domain.User;
import com.swu.umcmc.dto.follow.FollowResponseDto;
import com.swu.umcmc.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FollowQueryServiceImpl implements FollowQueryService {
    public final FollowRepository followRepository;

    //내가 팔로우 하는 사람들 중에서 검색
    @Override
    public FollowResponseDto.FollowingSliceDTO searchByNickNameInFollowing(String name, Long userId, LocalDateTime cursor, int offset) {
        Pageable pageable = PageRequest.of(0, offset);
        if (cursor == LocalDateTime.MAX) {
            return FollowResponseDto.FollowingSliceDTO
                    .from(followRepository.findFollowersByFollowerNameAndFollowingIdDesc(name, userId, pageable));

        }else {
            return FollowResponseDto.FollowingSliceDTO
                    .from(followRepository.findFollowersByFollowerNameAndFollowingIdDescWithCursor(name, userId, cursor, pageable));
        }

    }


    //나를 팔로우하는 사람들
    @Override
    public FollowResponseDto.FollowerSliceDTO getFollowers(Long userId, LocalDateTime cursor, int offset) {
        Pageable pageable = PageRequest.of(0, offset);
        if (cursor == LocalDateTime.MAX){
            FollowResponseDto.FollowerSliceDTO followSliceDTO= FollowResponseDto.FollowerSliceDTO
                    .from(followRepository.findFollowersByFollowingIdDesc(userId, pageable));

            for (int i =0;i<followSliceDTO.getFollows().size();i++){
                if (followRepository.existsByFollowing_IdAndFollower_Id(followSliceDTO.getFollows().get(i).getUserId(), userId)){
                    followSliceDTO.getFollows().get(i).setIsFollowed(true);
                }
            }
            return followSliceDTO;
        }else {
            FollowResponseDto.FollowerSliceDTO followerSliceDTO= FollowResponseDto.FollowerSliceDTO
                    .from(followRepository.findFollowersByFollowingIdDescWithCursor(userId, cursor, pageable));

            for (int i =0;i<followerSliceDTO.getFollows().size();i++){
                if (followRepository.existsByFollowing_IdAndFollower_Id(followerSliceDTO.getFollows().get(i).getUserId(), userId)){
                    followerSliceDTO.getFollows().get(i).setIsFollowed(true);
                }
            }
            return followerSliceDTO;
        }
    }


    //내가 팔로우하는 사람들
    @Override
    public FollowResponseDto.FollowingSliceDTO getFollowings(Long userId, LocalDateTime cursor, int offset) {
        Pageable pageable = PageRequest.of(0, offset);
        if (cursor == LocalDateTime.MAX){
            return FollowResponseDto.FollowingSliceDTO
                    .from(followRepository.findFollowingsByFollowingIdDesc(userId, pageable));
        }else {
            return FollowResponseDto.FollowingSliceDTO
                    .from(followRepository.findFollowingsByFollowingIdDescWithCursor(userId, cursor, pageable));
        }
    }

}

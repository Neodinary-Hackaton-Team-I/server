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

    public FollowResponseDto.FollowSliceDTO searchByNickNameInFollowing(String name, Long userId, LocalDateTime cursor, int offset) {
        Pageable pageable = PageRequest.of(0, offset);
        if (cursor == null){
            FollowResponseDto.FollowSliceDTO followSliceDTO= FollowResponseDto.FollowSliceDTO
                    .from(followRepository.findFollowersByFollowerNameAndFollowingIdDesc(name, userId, pageable));

            for (int i =0;i<followSliceDTO.getFollows().size();i++){
                if (followRepository.existsByFollowing_IdAndFollower_Id(followSliceDTO.getFollows().get(i).getUserId(), userId)){
                    followSliceDTO.getFollows().get(i).setIsFollowed(true);
                }
            }
            return followSliceDTO;
        }else {
            FollowResponseDto.FollowSliceDTO followSliceDTO= FollowResponseDto.FollowSliceDTO
                    .from(followRepository.findFollowersByFollowerNameAndFollowingIdDescWithCursor(name, userId, cursor, pageable));

            for (int i =0;i<followSliceDTO.getFollows().size();i++){
                if (followRepository.existsByFollowing_IdAndFollower_Id(followSliceDTO.getFollows().get(i).getUserId(), userId)){
                    followSliceDTO.getFollows().get(i).setIsFollowed(true);
                }
            }
            return followSliceDTO;
        }

    }

    public FollowResponseDto.FollowSliceDTO getFollowers(Long userId, LocalDateTime cursor, int offset) {
        Pageable pageable = PageRequest.of(0, offset);
        if (cursor == null){
            FollowResponseDto.FollowSliceDTO followSliceDTO= FollowResponseDto.FollowSliceDTO
                    .from(followRepository.findFollowersByFollowingIdDesc(userId, pageable));

            for (int i =0;i<followSliceDTO.getFollows().size();i++){
                if (followRepository.existsByFollowing_IdAndFollower_Id(followSliceDTO.getFollows().get(i).getUserId(), userId)){
                    followSliceDTO.getFollows().get(i).setIsFollowed(true);
                }
            }
            return followSliceDTO;
        }else {
            FollowResponseDto.FollowSliceDTO followSliceDTO= FollowResponseDto.FollowSliceDTO
                    .from(followRepository.findFollowersByFollowingIdDescWithCursor(userId, cursor, pageable));

            for (int i =0;i<followSliceDTO.getFollows().size();i++){
                if (followRepository.existsByFollowing_IdAndFollower_Id(followSliceDTO.getFollows().get(i).getUserId(), userId)){
                    followSliceDTO.getFollows().get(i).setIsFollowed(true);
                }
            }
            return followSliceDTO;
        }
    }



}

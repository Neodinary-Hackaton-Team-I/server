package com.swu.umcmc.service.followService.query;

import com.swu.umcmc.dto.follow.FollowResponseDto;
import com.swu.umcmc.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FollowQueryServiceImpl implements FollowQueryService {
    public final FollowRepository followRepository;

//    public FollowResponseDto.FollowSliceDTO searchByNickNameInFollowing(String name, Long userId, Long cursor, int offset) {
//        Pageable pageable = PageRequest.of(0, offset);
//        followRepository.()
//    }

}

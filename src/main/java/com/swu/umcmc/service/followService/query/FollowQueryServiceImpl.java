package com.swu.umcmc.service.followService.query;

import com.swu.umcmc.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FollowQueryServiceImpl implements FollowQueryService {
    public final FollowRepository followRepository;


}

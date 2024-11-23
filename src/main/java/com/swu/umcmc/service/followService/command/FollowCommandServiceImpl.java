package com.swu.umcmc.service.followService.command;

import com.swu.umcmc.repository.FollowRepository;
import com.swu.umcmc.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class FollowCommandServiceImpl implements FollowCommandService {
    public final FollowRepository followRepository;
    public final UserRepository userRepository;


}

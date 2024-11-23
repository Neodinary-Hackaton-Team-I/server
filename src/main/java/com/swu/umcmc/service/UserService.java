package com.swu.umcmc.service;

import com.swu.umcmc.config.exception.CustomException;
import com.swu.umcmc.config.exception.ErrorCode;
import com.swu.umcmc.domain.User;
import com.swu.umcmc.dto.user.UserRequestDto;
import com.swu.umcmc.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 이메일 및 닉네임 중복 검사
    private void validateSignUpInfo(String email, String nickname) {
        if (userRepository.existsByEmail(email)) {
            throw new CustomException(ErrorCode.DUPLICATE_EMAIL);
        }

        if (userRepository.existsByNickname(nickname)) {
            throw new CustomException(ErrorCode.DUPLICATE_NICKNAME);
        }
    }

    // 회원가입
    @Transactional
    public void signUp(UserRequestDto.SignUpRequest request) {
        validateSignUpInfo(request.getEmail(), request.getNickname());

        User user = User.builder()
                .email(request.getEmail())
                .nickname(request.getNickname())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        userRepository.save(user);
    }
}
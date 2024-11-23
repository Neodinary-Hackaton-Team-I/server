package com.swu.umcmc.service;

import com.swu.umcmc.config.exception.CustomException;
import com.swu.umcmc.config.exception.ErrorCode;
import com.swu.umcmc.domain.User;
import com.swu.umcmc.dto.user.UserRequestDto;
import com.swu.umcmc.dto.user.UserResponseDto;
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
    public UserResponseDto.SignUpResponse signUp(UserRequestDto.SignUpRequest request) {
        validateSignUpInfo(request.getEmail(), request.getNickname());

        User user = User.builder()
                .email(request.getEmail())
                .nickname(request.getNickname())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        User savedUser = userRepository.save(user);  // 저장된 유저 객체 받기

        return UserResponseDto.SignUpResponse.builder()
                .message("회원가입이 완료되었습니다.")
                .userId(savedUser.getId())
                .build();
    }

    // 로그인
    public UserResponseDto.LoginResponse login(UserRequestDto.LoginRequest request) {
        // 이메일로 사용자 찾기
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        // 비밀번호 확인
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new CustomException(ErrorCode.INVALID_PASSWORD);
        }

        return UserResponseDto.LoginResponse.builder()
                .message("로그인이 완료되었습니다.")
                .userId(user.getId())
                .nickname(user.getNickname())
                .build();
    }

}
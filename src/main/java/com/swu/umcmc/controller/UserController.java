package com.swu.umcmc.controller;

import com.swu.umcmc.apiPayload.ApiResponse;
import com.swu.umcmc.dto.user.UserRequestDto;
import com.swu.umcmc.dto.user.UserResponseDto;
import com.swu.umcmc.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")  // 모든 origin 허용
@Tag(name = "UserController", description = "사용자 관련 API")
public class UserController {

    private final UserService userService;


    @Operation(summary = "회원가입", description = "새로운 사용자를 등록합니다.")
    @PostMapping(value = "/signup", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<UserResponseDto.SignUpResponse> signUp(
            @Valid @RequestBody(required = true) UserRequestDto.SignUpRequest request) {
        UserResponseDto.SignUpResponse response = userService.signUp(request);
        return ApiResponse.success("회원가입 성공", response);
    }

    @Operation(summary = "로그인", description = "사용자 로그인을 수행합니다.")
    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<UserResponseDto.LoginResponse> login(
            @Valid @RequestBody UserRequestDto.LoginRequest request) {
        // log.info("Received login request for email: {}", request.getEmail());
        UserResponseDto.LoginResponse response = userService.login(request);
        return ApiResponse.success("로그인 성공", response);
    }
}

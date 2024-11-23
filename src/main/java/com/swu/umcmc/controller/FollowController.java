package com.swu.umcmc.controller;


import com.swu.umcmc.apiPayload.ApiResponse;
import com.swu.umcmc.dto.follow.FollowRequestDto;
import com.swu.umcmc.dto.follow.FollowResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.swu.umcmc.service.followService.command.FollowCommandServiceImpl;

@RestController
@RequestMapping("/api/v1/follow")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")  // 모든 origin 허용
@Tag(name = "FollowController", description = "Follow 관련 기능 API")
public class FollowController {

    private final FollowCommandServiceImpl followCommandService;

    @Operation(summary = "팔로우", description = "다른 사용자를 팔로우합니다.")
    @PostMapping
    public ApiResponse<FollowResponseDto.FollowResponse> follow(
            @Valid @RequestBody FollowRequestDto.FollowRequest request,
            @RequestHeader("X-USER-ID") Long userId) {
        // log.info("Follow request from userId: {} to userId: {}", userId, request.getFollowUserId());
        FollowResponseDto.FollowResponse response =
                followCommandService.follow(userId, request.getFollowUserId());
        return ApiResponse.success("팔로우 성공", response);
    }

    @Operation(summary = "언팔로우", description = "팔로우를 취소합니다.")
    @DeleteMapping
    public ApiResponse<?> unfollow(
            @Valid @RequestBody FollowRequestDto.FollowRequest request,
            @RequestHeader("X-USER-ID") Long userId) {
        // log.info("Unfollow request from userId: {} to userId: {}", userId, request.getFollowUserId());
        followCommandService.unfollow(userId, request.getFollowUserId());
        return ApiResponse.success("언팔로우 성공");
    }
}

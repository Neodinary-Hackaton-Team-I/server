package com.swu.umcmc.controller;


import com.swu.umcmc.apiPayload.ApiResponse;
import com.swu.umcmc.dto.follow.FollowRequestDto;
import com.swu.umcmc.dto.follow.FollowResponseDto;
import com.swu.umcmc.service.followService.query.FollowQueryService;
import com.swu.umcmc.service.followService.query.FollowQueryServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import lombok.Getter;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.swu.umcmc.service.followService.command.FollowCommandServiceImpl;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/follow")
@RequiredArgsConstructor
@Tag(name = "FollowController", description = "Follow 관련 기능 API")
public class FollowController {

    private final FollowCommandServiceImpl followCommandService;
    private final FollowQueryService followQueryService;

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

    @Operation(summary = "팔로잉 검색", description = "팔로잉 중에서 닉네임 검색.  첫 검색 시 cuser 값에 9999-11-24T23:59:59 를 넣어주세요")
    @GetMapping("/{userId}/search")
    public ApiResponse<?> findInFollow(
            @PathVariable("userId") Long userId,
            @RequestParam(required = false) String name,
            @RequestParam(value = "cursor", defaultValue = "+999999999-12-31T23:59:59.999999999") LocalDateTime cursor,
            @RequestParam(value = "offset", defaultValue = "10") int offset){
        FollowResponseDto.FollowingSliceDTO followSliceDTO = followQueryService.searchByNickNameInFollowing(name, userId, cursor, offset);
        return ApiResponse.success("검색 성공", followSliceDTO);
    }

    @Operation(summary = "전체 팔로잉", description = "전체 팔로잉 커서기반 페이지네이션.  첫 검색 시 cuser 값에 9999-11-24T23:59:59 를 넣어주세요")
    @GetMapping("/{userId}/followings")
    public ApiResponse<?> getFollowingByMe(
            @PathVariable("userId") Long userId,
            @RequestParam(value = "cursor", defaultValue = "+999999999-12-31T23:59:59.999999999") LocalDateTime cursor,
            @RequestParam(value = "offset", defaultValue = "10") int offset
    ){
        FollowResponseDto.FollowingSliceDTO followSliceDTO = followQueryService.getFollowings(userId, cursor, offset);
        return ApiResponse.success("검색 성공", followSliceDTO);
    }

    @Operation(summary = "전체 팔로워", description = "전체 팔로워 커서기반 페이지네이션.  첫 검색 시 cuser 값에 9999-11-24T23:59:59 를 넣어주세요")
    @GetMapping("/{userId}/followers")
    public ApiResponse<?> getFollowerByMe(
            @PathVariable("userId") Long userId,
            @RequestParam(value = "cursor", defaultValue = "+999999999-12-31T23:59:59.999999999") LocalDateTime cursor,
            @RequestParam(value = "offset", defaultValue = "10") int offset
    ){
        FollowResponseDto.FollowerSliceDTO followSliceDTO = followQueryService.getFollowers(userId, cursor, offset);
        return ApiResponse.success("검색 성공", followSliceDTO);
    }




}

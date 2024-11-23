package com.swu.umcmc.dto.follow;

import com.swu.umcmc.domain.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.data.domain.Slice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FollowResponseDto {

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Builder
    public static class FollowDTO{
        String nickName;
        Long userId;
        boolean isFollowed;

        public static FollowDTO from(User follower) {
            return FollowDTO.builder()
                    .nickName(follower.getNickname())
                    .userId(follower.getId())
                    .isFollowed(false)
                    .build();
        }
        public void setIsFollowed(boolean isFollowed) {
            this.isFollowed = isFollowed;
        }
    }
    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Builder
    public static class FollowSliceDTO{
        List<FollowDTO> follows;
        LocalDateTime cursor;
        boolean hasNext;

        public static FollowSliceDTO from(Slice<User> follows) {
            return FollowSliceDTO.builder()
                    .follows(follows.getContent().isEmpty()?new ArrayList<>():follows.getContent().stream().map(FollowDTO::from).toList())
                    .cursor(follows.getContent().isEmpty()?null : follows.getContent().get(follows.getContent().size()-1).getCreatedAt())
                    .hasNext(follows.hasContent())
                    .build();
        }


    }

    // 팔로우 응답
    @Getter
    @Builder
    @Schema(title = "FOLLOW_RES_01 : 팔로우 응답 DTO")
    public static class FollowResponse {
        @Schema(description = "팔로우한 사용자 ID", example = "1")
        private Long followUserId;

        @Schema(description = "팔로우한 사용자 닉네임", example = "테스트유저")
        private String followNickname;
    }
}

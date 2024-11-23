package com.swu.umcmc.dto.follow;

import com.swu.umcmc.domain.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.data.domain.Slice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FollowResponseDto {



    //내가 팔로우 하는 사람들 리스트
    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Builder
    public static class FollowingDTO{
        String nickName;
        Long userId;

        public static FollowingDTO from(User follower) {
            return FollowingDTO.builder()
                    .nickName(follower.getNickname())
                    .userId(follower.getId())
                    .build();
        }

    }
    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Builder
    public static class FollowingSliceDTO{
        List<FollowingDTO> follows;
        LocalDateTime cursor;
        boolean hasNext;

        public static FollowingSliceDTO from(Slice<User> follows) {
            return FollowingSliceDTO.builder()
                    .follows(follows.getContent().isEmpty()?new ArrayList<>():follows.getContent().stream().map(FollowingDTO::from).toList())
                    .cursor(follows.getContent().isEmpty()? null : follows.getContent().get(follows.getContent().size()-1).getCreatedAt())
                    .hasNext(follows.hasNext())
                    .build();
        }
    }






    //나를 팔로우하는 사람들 리스트
    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Builder
    public static class FollowerDTO{
        String nickName;
        Long userId;
        boolean isFollowed;

        public static FollowerDTO from(User follower) {
            return FollowerDTO.builder()
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
    public static class FollowerSliceDTO{
        List<FollowerDTO> follows;
        LocalDateTime cursor;
        boolean hasNext;

        public static FollowerSliceDTO from(Slice<User> follows) {
            return FollowerSliceDTO.builder()
                    .follows(follows.getContent().isEmpty()?new ArrayList<>():follows.getContent().stream().map(FollowerDTO::from).toList())
                    .cursor(follows.getContent().isEmpty()?null : follows.getContent().get(follows.getContent().size()-1).getCreatedAt())
                    .hasNext(follows.hasNext())
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

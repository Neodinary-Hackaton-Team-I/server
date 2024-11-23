package com.swu.umcmc.dto.follow;

import com.swu.umcmc.domain.User;
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


}

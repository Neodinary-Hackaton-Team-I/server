package com.swu.umcmc.dto.user;

import com.swu.umcmc.domain.User;
import com.swu.umcmc.dto.follow.FollowResponseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.data.domain.Slice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
public class UserResponseDto {

    @Getter
    @Schema(title = "USER_RES_01 : 회원가입 응답 DTO")
    @Builder
    public static class SignUpResponse {
        @Schema(description = "응답 메시지", example = "회원가입이 완료되었습니다.")
        private String message;

        @Schema(description = "생성된 사용자 ID", example = "1")
        private Long userId;
    }

    @Getter
    @Builder
    @Schema(title = "USER_RES_02 : 로그인 응답 DTO")
    public static class LoginResponse {
        @Schema(description = "응답 메시지", example = "로그인이 완료되었습니다.")
        private String message;

        @Schema(description = "사용자 ID", example = "1")
        private Long userId;

        @Schema(description = "사용자 닉네임", example = "테스트유저")
        private String nickname;
    }

    //나를 팔로우하는 사람들 리스트
    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Builder
    public static class UserDTO{
        String nickName;
        Long userId;
        boolean isFollowed;

        public static UserResponseDto.UserDTO from(User user) {
            return UserResponseDto.UserDTO.builder()
                    .nickName(user.getNickname())
                    .userId(user.getId())
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
    public static class UserSliceDTO{
        List<UserResponseDto.UserDTO> users;
        LocalDateTime cursor;
        boolean hasNext;

        public static UserResponseDto.UserSliceDTO from(Slice<User> users) {
            return UserResponseDto.UserSliceDTO.builder()
                    .users(users.getContent().isEmpty()?new ArrayList<>():users.getContent().stream().map(UserResponseDto.UserDTO::from).toList())
                    .cursor(users.getContent().isEmpty()?null : users.getContent().get(users.getContent().size()-1).getCreatedAt())
                    .hasNext(users.hasNext())
                    .build();
        }
    }
}

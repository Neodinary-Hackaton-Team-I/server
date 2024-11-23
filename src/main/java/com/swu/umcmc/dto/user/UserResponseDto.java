package com.swu.umcmc.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserResponseDto {

    @Getter
    @Schema(title = "USER_RES_01 : 회원가입 응답 DTO")
    @Builder
    public static class SignUpResponse {
        @Schema(description = "응답 메시지", example = "회원가입이 완료되었습니다.")
        private String message;
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
}

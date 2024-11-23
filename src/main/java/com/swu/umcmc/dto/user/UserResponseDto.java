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

}

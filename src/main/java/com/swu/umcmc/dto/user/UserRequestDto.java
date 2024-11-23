package com.swu.umcmc.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
public class UserRequestDto {

    @Data
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Schema(title = "USER_REQ_01 : 회원가입 요청 DTO")
    public static class SignUpRequest {
        @Schema(description = "이메일", example = "test@example.com")
        @Email(message = "올바른 이메일 형식이 아닙니다")
        @NotBlank(message = "이메일은 필수 입력값입니다")
        private String email;

        @Schema(description = "닉네임", example = "테스트유저")
        @Pattern(regexp = "^[a-zA-Z0-9가-힣]{2,10}$", message = "닉네임은 특수문자를 제외한 2~10자리여야 합니다")
        @NotBlank(message = "닉네임은 필수 입력값입니다")
        private String nickname;

        @Schema(description = "비밀번호", example = "password123!")
        @Pattern(regexp = "^[a-zA-Z0-9!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]{8,16}$", message = "비밀번호는 8~16자리여야 합니다")
        @NotBlank(message = "비밀번호는 필수 입력값입니다")
        private String password;
    }
}
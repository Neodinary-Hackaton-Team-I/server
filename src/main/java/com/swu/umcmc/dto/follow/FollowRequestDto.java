package com.swu.umcmc.dto.follow;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Builder
public class FollowRequestDto {

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Schema(title = "FOLLOW_REQ_01 : 팔로우 요청 DTO")
    public static class FollowRequest {
        @Schema(description = "팔로우할 사용자의 ID", example = "1")
        @NotNull(message = "팔로우할 사용자의 ID는 필수 입력값입니다")
        private Long followUserId;
    }

}

package com.swu.umcmc.dto.letter;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LetterRequest {
    @NotBlank(message = "url은 필수입니다.")
    private String imageUrl;

    @NotBlank(message = "본문 작성은 필수입니다.")
    @Size(max = 500, message = "본문은 최대 500글자까지 입력할 수 있습니다.")
    private String body;

    @NotNull(message = "수신자는 필수입니다.")
    private Long senderId;

    @NotNull(message = "송신자는 필수입니다.")
    private Long receiverId;

}

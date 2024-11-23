package com.swu.umcmc.dto.letter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LetterResponse {
    private Long letterId;
    private Long senderId;
    private Long receiverId;
    private String imageUrl;
    private String body;
    private LocalDateTime createdAt;
    private boolean isOpened;
}

package com.swu.umcmc.controller;


import com.swu.umcmc.apiPayload.ApiResponse;
import com.swu.umcmc.dto.letter.LetterRequest;
import com.swu.umcmc.dto.letter.LetterResponse;
import com.swu.umcmc.service.FileService;
import com.swu.umcmc.service.LetterService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/letters")
@RequiredArgsConstructor
@Tag(name = "LetterController", description = "Letter 관련 기능 API")
public class LetterController {
    private final LetterService letterService;

    @PostMapping
    public ApiResponse<LetterResponse> createLetter(@Valid @RequestBody LetterRequest letterRequest) {
        LetterResponse createdLetter = letterService.createLetter(letterRequest);
        return ApiResponse.success("편지가 성공적으로 전송되었습니다.", createdLetter);
    }

    @GetMapping("/{letterId}")
    public ApiResponse<LetterResponse> getLetterById(@PathVariable("letterId") Long letterId) {
        LetterResponse letterResponse = letterService.getLetterById(letterId);
        return ApiResponse.success("편지 개별 조회 성공", letterResponse);
    }

    @GetMapping("/receiver/{userId}")
    public ApiResponse<List<LetterResponse>> getLettersByReceiverId(@PathVariable("userId") Long userId) {
        List<LetterResponse> letterResponses = letterService.getLettersByReceiverId(userId);
        return ApiResponse.success("수신자별 받은 모든 편지 조회 성공", letterResponses);
    }

    @GetMapping("/opened/{userId}")
    public ApiResponse<List<LetterResponse>> getOpenedLettersByReceiverId(@PathVariable("userId") Long userId) {
        List<LetterResponse> letterResponses = letterService.getOpenedLettersByReceiverId(userId);
        return ApiResponse.success("수신자별 열린 편지 조회 성공", letterResponses);
    }

    @PostMapping("/open-letters")
    public ApiResponse<Void> openLetters() {
        letterService.openLetter();
        return ApiResponse.success("모든 편지 열기 성공", null);
    }
}

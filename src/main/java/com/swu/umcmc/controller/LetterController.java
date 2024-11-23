package com.swu.umcmc.controller;


import com.swu.umcmc.apiPayload.ApiResponse;
import com.swu.umcmc.dto.letter.LetterRequest;
import com.swu.umcmc.dto.letter.LetterResponse;
import com.swu.umcmc.service.FileService;
import com.swu.umcmc.service.LetterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/letters")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")  // 모든 origin 허용
@Tag(name = "LetterController", description = "Letter 관련 기능 API")
public class LetterController {
    private final LetterService letterService;

    @Operation(summary = "편지 생성", description = "편지를 생성합니다.")
    @PostMapping
    public ApiResponse<LetterResponse> createLetter(@Valid @RequestBody LetterRequest letterRequest) {
        LetterResponse createdLetter = letterService.createLetter(letterRequest);
        return ApiResponse.success("편지가 성공적으로 전송되었습니다.", createdLetter);
    }

    @Operation(summary = "편지 개별 조회", description = "편지를 편지id로 개별 조회합니다.")
    @GetMapping("/{letterId}")
    public ApiResponse<LetterResponse> getLetterById(@PathVariable("letterId") Long letterId) {
        LetterResponse letterResponse = letterService.getLetterById(letterId);
        return ApiResponse.success("편지 개별 조회 성공", letterResponse);
    }

    @Operation(summary = "수신자별 받은 모든 편지 조회", description = "받은 편지를 수신자id로 조회합니다.")
    @GetMapping("/receiver/{userId}")
    public ApiResponse<List<LetterResponse>> getLettersByReceiverId(@PathVariable("userId") Long userId) {
        List<LetterResponse> letterResponses = letterService.getLettersByReceiverId(userId);
        return ApiResponse.success("수신자별 받은 모든 편지 조회 성공", letterResponses);
    }

    @Operation(summary = "수신자별 열린 편지 조회", description = "수신자의 열린 편지를 수신자id로 조회합니다.")
    @GetMapping("/opened/{userId}")
    public ApiResponse<List<LetterResponse>> getOpenedLettersByReceiverId(@PathVariable("userId") Long userId) {
        List<LetterResponse> letterResponses = letterService.getOpenedLettersByReceiverId(userId);
        return ApiResponse.success("수신자별 열린 편지 조회 성공", letterResponses);
    }

    @Operation(summary = "모든 편지 열기", description = "요청 시간이 11/23~31일이라면 모든 편지를 엽니다.")
    @PostMapping("/open-letters")
    public ApiResponse<Void> openLetters() {
        letterService.openLetter();
        return ApiResponse.success("모든 편지 열기 성공", null);
    }
}

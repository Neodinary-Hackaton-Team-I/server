package com.swu.umcmc.controller;

import com.swu.umcmc.apiPayload.ApiResponse;
import com.swu.umcmc.domain.Presigned;
import com.swu.umcmc.service.FileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/files")
@Tag(name = "FileController", description = "S3 presigned URL 관련 API")
public class FileController {
    private final FileService fileService;

    @Operation(summary = "URL 발급", description = "S3 presigned URL을 발급합니다.")
    @GetMapping("/presigned-url")
    public ApiResponse<Presigned> getPresignedUrl() {
        String url = fileService.getPreSignedUrl();
        Presigned presigned = new Presigned(url);
        return ApiResponse.success("S3 presigned URL 발급 성공", presigned);
    }
}

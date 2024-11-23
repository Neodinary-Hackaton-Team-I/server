package com.swu.umcmc.controller;

import com.swu.umcmc.apiPayload.ApiResponse;
import com.swu.umcmc.domain.Presigned;
import com.swu.umcmc.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;

    @GetMapping("/presigned-url")
    public ApiResponse<Presigned> getPresignedUrl() {
        String url = fileService.getPreSignedUrl();
        Presigned presigned = new Presigned(url);
        return ApiResponse.success("S3 presigned URL 발급 성공", presigned);
    }
}

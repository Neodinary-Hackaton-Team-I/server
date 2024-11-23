package com.swu.umcmc.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/letters")
@RequiredArgsConstructor
@Tag(name = "LetterController", description = "Letter 관련 기능 API")
public class LetterController {
}
package com.swu.umcmc.service;

import com.swu.umcmc.apiPayload.ApiResponse;
import com.swu.umcmc.config.exception.CustomException;
import com.swu.umcmc.config.exception.ErrorCode;
import com.swu.umcmc.domain.Letter;
import com.swu.umcmc.domain.User;
import com.swu.umcmc.dto.letter.LetterRequest;
import com.swu.umcmc.dto.letter.LetterResponse;
import com.swu.umcmc.repository.LetterRepository;
import com.swu.umcmc.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class LetterService {
    private final LetterRepository letterRepository;
    private final UserRepository userRepository;

    public LetterResponse createLetter(@RequestBody LetterRequest letterRequest) {
        // 송신자와 수신자 조회
        User sender = userRepository.findById(letterRequest.getSenderId())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
        User receiver = userRepository.findById(letterRequest.getReceiverId())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));


        // 편지 객체 생성 & 저장
        Letter letter = Letter.builder()
                .body(letterRequest.getBody())
                .imageUrl(letterRequest.getImageUrl())
                .isOpened(false)  // 기본적으로 편지는 열리지 않은 상태로 설정
                .sender(sender)
                .receiver(receiver)
                .build();
        letterRepository.save(letter);

        return mapToLetterResponse(letter);
    }

    public LetterResponse getLetterById(Long letterId) {
        Letter letter = letterRepository.findById(letterId)
                .orElseThrow(() -> new CustomException(ErrorCode.RESOURCE_NOT_FOUND));
        return mapToLetterResponse(letter);
    }

    public List<LetterResponse> getLettersByReceiverId(Long userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        List<Letter> letters = letterRepository.findByReceiverId(userId);

        return letters.stream()
                .map(this::mapToLetterResponse)
                .collect(Collectors.toList());
    }

    public List<LetterResponse> getOpenedLettersByReceiverId(Long userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        List<Letter> letters = letterRepository.findAllByReceiverIdAndIsOpened(userId, true);

        return letters.stream()
                .map(this::mapToLetterResponse)
                .collect(Collectors.toList());
    }

    public void openLetter() {
        LocalDate today = LocalDate.now();
        LocalDate start = LocalDate.of(today.getYear(), 11, 23);
        LocalDate end = LocalDate.of(today.getYear(), 11, 30);

        if (today.isAfter(start) && today.isBefore(end)) {
            List<Letter> letters = letterRepository.findAllByIsOpened(false);

            for (Letter letter : letters) {
                letter.setOpened(true);
            }

            letterRepository.saveAll(letters);
        } else {
            throw new CustomException(ErrorCode.INVALID_OPEN_TIME);
        }
    }

    private LetterResponse mapToLetterResponse(Letter letter){
        String senderNickname = letter.getSender().getNickname();

        return LetterResponse.builder()
                .letterId(letter.getId())
                .senderId(letter.getSender().getId())
                .receiverId(letter.getReceiver().getId())
                .imageUrl(letter.getImageUrl())
                .body(letter.getBody())
                .createdAt(letter.getCreatedAt())
                .isOpened(letter.isOpened())
                .nickname(senderNickname)
                .build();
    }
}

package com.swu.umcmc.repository;

import com.swu.umcmc.domain.Letter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LetterRepository extends JpaRepository<Letter, Long> {
    List<Letter> findAllByIsOpened(boolean isOpened);
    List<Letter> findAllByReceiverIdAndIsOpened(Long receiverId, boolean isOpened);
    List<Letter> findByReceiverId(Long receiverId);
}

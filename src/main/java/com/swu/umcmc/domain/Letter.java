package com.swu.umcmc.domain;

import com.swu.umcmc.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "letter")
public class Letter extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "timeCapsuleId")
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String body;

    @Column(length = 300)
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "senderId")
    private User sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiverId")
    private User receiver;

    // 연관관계 편의 메서드
    public void setSender(User sender) {
        this.sender = sender;
        sender.getSentLetters().add(this);
    }

    // 연관관계 편의 메서드
    public void setReceiver(User receiver) {
        this.receiver = receiver;
        receiver.getReceivedLetters().add(this);
    }
}

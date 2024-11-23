package com.swu.umcmc.domain;

import com.swu.umcmc.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "letter")
public class Letter extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "timeCapsuleId")
    private Long id;

    @Column(columnDefinition = "TEXT", length = 500)
    private String body;

    @Column(length = 300)
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "senderId")
    private User sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiverId")
    private User receiver;

    @Setter
    @Column(name = "isOpened")
    private boolean isOpened;

    // 연관관계 편의 메서드
    public void setReceiver(User receiver) {
        this.receiver = receiver;
        receiver.getReceivedLetters().add(this);
    }
}

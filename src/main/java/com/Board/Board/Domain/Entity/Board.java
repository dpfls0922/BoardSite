package com.Board.Board.Domain.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
public class Board {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer num;

    @Column(name = "name")
    private String name;

    @Column(name = "subject")
    private String subject;

    @Column(name = "content")
    private String content;

    @Column(nullable = false)
    private Integer hitcount = 0;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @CreatedDate
    @Column(name = "created", updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(name = "updated")
    private LocalDateTime updatedDate;

    @Builder
    public Board(Integer num, String name, String subject, String content) {
        this.num = num;
        this.name = name;
        this.subject = subject;
        this.content = content;
        this.hitcount = 0;
    }
    public Integer getHitCount() {
        return this.hitcount;
    }
    public void update(String subject, String content) {
        this.subject = subject;
        this.content = content;
    }
}

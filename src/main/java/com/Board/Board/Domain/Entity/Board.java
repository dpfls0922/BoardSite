package com.Board.Board.Domain.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@DynamicInsert
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
public class Board {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer num;
    private String name;
    private String email;
    private String subject;
    private String content;

    @Column(nullable = false)
    private Integer hitcount = 0;

    @CreatedDate
    @Column(name = "created", updatable = false)
    private LocalDateTime createdDate;
    @LastModifiedDate
    @Column(name = "updated")
    private LocalDateTime updatedDate;

    @Builder
    public Board(Integer num, String name, String email, String subject, String content) {
        this.num = num;
        this.name = name;
        this.email = email;
        this.subject = subject;
        this.content = content;
        this.hitcount = 0;
    }
    public Integer getHitCount() {
        return this.hitcount;
    }
}

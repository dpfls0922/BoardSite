package com.Board.Board.Domain.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
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

    @Column(name = "created")
    private LocalDateTime createdDate;

    @PrePersist
    public void createDate(){
        this.createdDate = LocalDateTime.now();
    }
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

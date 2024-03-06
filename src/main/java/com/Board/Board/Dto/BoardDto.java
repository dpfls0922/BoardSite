package com.Board.Board.Dto;

import com.Board.Board.Domain.Entity.Board;
import lombok.*;
import java.util.Optional;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardDto {
    private int num;
    private String name;
    private String email;
    private String subject;
    private String content;
    private int hitCount;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public Board toEntity() {
        Board build = Board.builder()
                .name(name)
                .email(email)
                .subject(subject)
                .content(content)
                .build();

        Optional.ofNullable(num).ifPresent(build::setNum);

        return build;
    }
    @Builder
    public BoardDto(int num, String name, String email, String subject, String content, int hitCount, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.num = num;
        this.name = name;
        this.email = email;
        this.subject = subject;
        this.content = content;
        this.hitCount = hitCount;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}

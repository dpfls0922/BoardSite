package com.Board.Board.Dto;

import com.Board.Board.Domain.Entity.Board;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardDto {
    private Long id;
    private String writer;
    private String email;
    private String subject;
    private String content;
    private String category;
    private int hitCount;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public Board toEntity() {
        Board build = Board.builder()
                .writer(writer)
                .subject(subject)
                .content(content)
                .build();

        return build;
    }
    @Builder
    public BoardDto(Long id, String writer, String email, String subject, String content,
                    int hitCount, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.id = id;
        this.writer = writer;
        this.email = email;
        this.subject = subject;
        this.content = content;
        this.hitCount = hitCount;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

}

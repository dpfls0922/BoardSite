package com.Board.Board.Domain.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "board_category")
@Entity
public class BoardCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @CreatedDate
    private LocalDate created;

    public BoardCategory(Board board, Category category) {
        this.board = board;
        this.category = category;
    }
}

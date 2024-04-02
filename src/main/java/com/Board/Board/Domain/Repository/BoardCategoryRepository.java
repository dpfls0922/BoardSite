package com.Board.Board.Domain.Repository;

import com.Board.Board.Domain.Entity.Board;
import com.Board.Board.Domain.Entity.BoardCategory;
import com.Board.Board.Domain.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardCategoryRepository extends JpaRepository<BoardCategory, Long> {
    void deleteByBoard(Board board);
}

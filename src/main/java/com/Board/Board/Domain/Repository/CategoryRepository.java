package com.Board.Board.Domain.Repository;

import com.Board.Board.Domain.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByType(String type);
}
package com.Board.Board.Domain.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.ArrayList;


@ToString
@Getter
@NoArgsConstructor
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "category")
    private List<BoardCategory> boardCategories = new ArrayList<>();
}

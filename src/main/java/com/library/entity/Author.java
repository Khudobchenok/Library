package com.library.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * Создание сущности Author
 */

@Data
@Entity
@Table(name = "AUTHORS")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    private String biography;
    @OneToMany
    private List<Book> books;

    /**
     * Конструкторы
     */

    public Author () {}

    public Author (String name, String biography) {
        this.name = name;
        this.biography = biography;
    }
}

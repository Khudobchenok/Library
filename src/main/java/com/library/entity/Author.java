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
    @Column(length = 1500)
    private String biography;
    @OneToMany
    private List<Book> books;
    String imageFileName;

    /**
     * Конструкторы
     */

    public Author () {}

    public Author (String name, String biography, String imageFileName) {
        this.name = name;
        this.biography = biography;
        this.imageFileName = imageFileName;
    }

    public boolean isValid() {
        return id != null && name != null && biography != null;
    }
}

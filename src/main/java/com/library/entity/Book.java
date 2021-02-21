package com.library.entity;


import lombok.Data;

import javax.persistence.*;
import java.awt.image.BufferedImage;

/**
 * Создание сущности Book
 */

@Data
@Entity
@Table(name = "BOOKS")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int rating;
    @Column(length = 1500)
    private String description;
    @Column(length = 1500)
    private String demoVersion;
    @OneToOne
    private Author author;
    private BufferedImage image;

    /**
     * Конструкторы
     */

    public Book () {}

    public Book(String name, String description, String demoVersion, Author author) {
        this.name = name;
        this.description = description;
        this.demoVersion = demoVersion;
        this.author = author;
    }
}

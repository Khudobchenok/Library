package com.library.entity;


import lombok.Data;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.EntityGraph;

import javax.persistence.*;
import javax.print.attribute.standard.Media;
import java.awt.*;
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
    private String imageFileName;


    /**
     * Конструкторы
     */

    public Book () {}

    public Book(String name, String description, String demoVersion, Author author, String imageFileName) {
        this.name = name;
        this.description = description;
        this.demoVersion = demoVersion;
        this.author = author;
        this.imageFileName = imageFileName;
    }
}

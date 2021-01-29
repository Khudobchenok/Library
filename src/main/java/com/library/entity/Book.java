package com.library.entity;


import lombok.Data;

import javax.persistence.*;

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
    private String description;
    @OneToOne
    private Author author;

    /**
     * Конструкторы
     */

    public Book () {}
}

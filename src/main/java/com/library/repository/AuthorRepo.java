package com.library.repository;

import com.library.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Создание репозитория для доступа к БД
 */

public interface AuthorRepo extends JpaRepository<Author, Long> {
    Author findAuthorByName (String name);
}

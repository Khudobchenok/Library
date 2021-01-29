package com.library.repository;

import com.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Создание репозитория для доступа к БД
 */

public interface BookRepo extends JpaRepository<Book, Long> {
}

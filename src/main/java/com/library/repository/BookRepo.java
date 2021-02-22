package com.library.repository;

import com.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Создание репозитория для доступа к БД
 */

public interface BookRepo extends JpaRepository<Book, Long> {
    List<Book> findBooksByAuthor_Id (Long authorId);
    Book findBookById (Long id);
}

package com.library.service;

import com.library.entity.Author;
import com.library.entity.Book;
import com.library.repository.AuthorRepo;
import com.library.repository.BookRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BookService {

    @Autowired
    BookRepo bookRepo;

    public boolean addBook(Book book) {
        for (Book bookFromDB : bookRepo.findAll()) {
            if (bookFromDB.getName().equalsIgnoreCase(book.getName()))
                return false;
        }
        try {
            bookRepo.save(book);
        }
        catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        log.info("Add new book");
        return true;
    }

    public List<Book> allBooks() {
        return bookRepo.findAll();
    }

    public boolean deleteBook(Long bookId) {
        if (bookRepo.findById(bookId).isPresent()) {
            bookRepo.deleteById(bookId);
            return true;
        }
        return false;
    }
}

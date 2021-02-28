package com.library.service;

import com.library.entity.Author;
import com.library.entity.Book;
import com.library.error.Error;
import com.library.repository.AuthorRepo;
import com.library.repository.BookRepo;
import com.library.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.security.Principal;
import java.util.List;

@Slf4j
@Service
public class BookService {

    @Autowired
    BookRepo bookRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    UserService userService;

    public boolean addBook(Book book, Model model) {
        if (book.getImageFileName().isEmpty() || !book.getImageFileName().startsWith("http")) {
            book.setImageFileName("https://upload.wikimedia.org/wikipedia/commons/9/9a/%D0%9D%D0%B5%D1%82_%D1%84%D0%BE%D1%82%D0%BE.png");
        }
        if (book.getAuthor() == null) {
            return false;
        }
        if (book.getName().isEmpty()) {
            Error.setMessage(userRepo.findByUsername(userService.getCurrentUsername()), "Enter name");
            return false;
        }
        if (book.getDescription().isEmpty()) {
            Error.setMessage(userRepo.findByUsername(userService.getCurrentUsername()), "Enter description");
            return false;
        }
        if (book.getDescription().length() > 1500) {
            Error.setMessage(userRepo.findByUsername(userService.getCurrentUsername()), "Description more than 1500 symbols");
            return false;
        }
        if (book.getDemoVersion().isEmpty()) {
            Error.setMessage(userRepo.findByUsername(userService.getCurrentUsername()), "Enter demo version of book");
            return false;
        }
        if (book.getDemoVersion().length() > 1500) {
            Error.setMessage(userRepo.findByUsername(userService.getCurrentUsername()), "Demo version more than 1500 symbols");
            return false;
        }
        for (Book bookFromDB : bookRepo.findAll()) {
            if (bookFromDB.getName().equalsIgnoreCase(book.getName())) {
                model.addAttribute("nameError", "This book already add");
                return false;
            }
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

    public boolean addImageForBook(Book book) {
        return false;
    }
}

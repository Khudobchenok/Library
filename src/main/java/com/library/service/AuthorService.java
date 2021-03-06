package com.library.service;

import com.library.entity.Author;
import com.library.error.Error;
import com.library.repository.AuthorRepo;
import com.library.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Slf4j
@Service
public class AuthorService {

    @Autowired
    AuthorRepo authorRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    UserService userService;

    public boolean addAuthor (Author author, Model model) {
        if (author.getImageFileName().isEmpty() || !author.getImageFileName().startsWith("http")) {
            author.setImageFileName("https://upload.wikimedia.org/wikipedia/commons/9/9a/%D0%9D%D0%B5%D1%82_%D1%84%D0%BE%D1%82%D0%BE.png");
        }
        if (author.getName().isEmpty()) {
            Error.setMessage(userRepo.findByUsername(userService.getCurrentUsername()), "Enter name");
            return false;
        }
        if (author.getBiography().isEmpty()) {
            Error.setMessage(userRepo.findByUsername(userService.getCurrentUsername()), "Enter biography");
            return false;
        }
        if (author.getBiography().length() > 1500) {
            Error.setMessage(userRepo.findByUsername(userService.getCurrentUsername()), "Biography more than 1500 symbols");
            return false;
        }
        for (Author authorFromDB : authorRepo.findAll()) {
            if (authorFromDB.getName().equalsIgnoreCase(author.getName()))
                return false;
        }
        try {
            authorRepo.save(author);
        }
        catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        log.info("Add new author");
        return true;
    }

    public List<Author> allAuthors() {
        return authorRepo.findAll();
    }

    public boolean deleteAuthor(Long authorId) {
        if (authorRepo.findById(authorId).isPresent()) {
            authorRepo.deleteById(authorId);
            return true;
        }
        return false;
    }
}

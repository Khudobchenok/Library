package com.library.service;

import com.library.entity.Author;
import com.library.repository.AuthorRepo;
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

    public boolean addAuthor (Author author, Model model) {
        if (author.getName().isEmpty()) {
            model.addAttribute("nameError", "Enter name");
            return false;
        }
        if (author.getBiography().isEmpty()) {
            model.addAttribute("biographyError", "Enter biography");
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

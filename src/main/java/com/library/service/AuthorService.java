package com.library.service;

import com.library.entity.Author;
import com.library.repository.AuthorRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthorService {
    @Autowired
    AuthorRepo authorRepo;

    public boolean addAuthor (String authorName, String biography) {
        Author authorFromDB = authorRepo.findByName(authorName);

        if (authorFromDB != null) {
            log.info("Author already add");
            return false;
        }
        authorRepo.save(new Author(authorName, biography));
        log.info("Add new author");
        return true;
    }
}

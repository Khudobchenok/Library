package com.library.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/welcome").setViewName("welcome");
        registry.addViewController("/books").setViewName("books");
        registry.addViewController("/addAuthor").setViewName("newAuthor");
        registry.addViewController("/addBook").setViewName("newBook");
        registry.addViewController("/authorPage").setViewName("authorPage");
        registry.addViewController("/bookPage").setViewName("bookPage");
        registry.addViewController("/startProject").setViewName("startProject");
    }
}
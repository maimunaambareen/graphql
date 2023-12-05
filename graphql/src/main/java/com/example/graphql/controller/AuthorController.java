package com.example.graphql.controller;

import com.example.graphql.model.Author;
import com.example.graphql.repository.AuthorRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class AuthorController {

    private final AuthorRepository authorRepository;


    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }


    @QueryMapping
    public List<Author> allAuthors(){
        return authorRepository.findAll();
    }

    @QueryMapping
    public Author findAuthorByName(@Argument String name)
    {
        return authorRepository.findByName(name);
    }

    @QueryMapping
    public Author findAuthorById(@Argument Integer id)
    {
        return authorRepository.findById(id);
    }
}

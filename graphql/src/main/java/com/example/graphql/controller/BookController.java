package com.example.graphql.controller;

import com.example.graphql.model.Author;
import com.example.graphql.model.Book;
import com.example.graphql.model.BookInput;
import com.example.graphql.model.Rating;
import com.example.graphql.repository.BookRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookRepository bookRepository;


    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }



   //@SchemaMapping(typeName = "Query", value = "allBooks")
    @QueryMapping
    public List<Book> allBooks(){
        return bookRepository.findAll();
    }
   //TODO: org.springframework.dao.InvalidDataAccessResourceUsageException: Named parameter not bound : pages
    //TODO: Caused by: org.hibernate.QueryException: Named parameter not bound : pages
    //TODO: Can we do this?
    @QueryMapping
    public List<Book> findBooksWithPagesGreaterThan(@Argument Integer pages){
        return bookRepository.findBooksWithPagesGreaterThan(pages);
    }

    @QueryMapping
    public Book findOne(@Argument Integer id) {
        return bookRepository.findAll().stream()
                .filter(book -> book.getId()== id)
                .findFirst().orElseThrow(() -> new RuntimeException("Book with id not found"));
    }
    @QueryMapping
    public Book findByTitle(@Argument String title)
    {
        return bookRepository.findAll().stream().filter(book -> book.getTitle().equals(title))
                .findFirst().orElseThrow(() -> new RuntimeException("Book with title not found"));
    }

    @MutationMapping
    public Book createBook(@Argument String title, @Argument Integer pages,@Argument String author){
        Book book=new Book(title,pages,author);
        return bookRepository.save(book);
    }

    @MutationMapping
    public Book addBook(@Argument BookInput book){
        return bookRepository.save(new Book(book.title(), book.pages(), book.author()));
    }

    @MutationMapping
    public List<Book> batchCreate(@Argument List<BookInput> books){
        return bookRepository.saveAll(books.stream().map(book -> new Book(book.title(), book.pages(), book.author())).collect(Collectors.toList())) ;
    }

}

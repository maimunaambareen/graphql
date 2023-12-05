package com.example.graphql.repository;

import com.example.graphql.model.Author;
import com.example.graphql.model.Book;
import com.example.graphql.model.Rating;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {

    //custom query
    @Query("SELECT b FROM Book b WHERE b.pages > :pages")
    List<Book> findBooksWithPagesGreaterThan(@Param("pages") Integer pages);

    /*private final AuthorRepository authorRepository;

    private List<Book> books = new ArrayList<>();

    public BookRepository(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Book> findAll() {
        return books;
    }

    public Book findOne(Integer id) {
        return books.stream()
                .filter(book -> book.id() == id)
                .findFirst().orElseThrow(() -> new RuntimeException("Book with id not found"));
    }

    public Book findByTitle(String title)
    {
        return books.stream().filter(book -> book.title().equals(title))
                .findFirst().orElseThrow(() -> new RuntimeException("Book with title not found"));
    }


    @PostConstruct
    private void init() {
        books.add(new Book(1,
                "Reactive Spring",
                484,
                Rating.FIVE_STARS,
                authorRepository.findByName("MA-Josh Long-SA")));
        books.add(new Book(2,
                "Spring Boot Up & Running",
                328,
                Rating.FIVE_STARS,
                authorRepository.findByName("SA-Mark Heckler-SS")));
        books.add(new Book(3,
                "Hacking with Spring Boot 2.3",
                392,
                Rating.FIVE_STARS,
                authorRepository.findByName("SS-Greg Turnquist-MA")));
    }*/
//TODO: Query multiple objects:
 /*   query AccountsAndCases {
        uiapi {
            query {
                Account {
                    edges {
                        node {
                            Name { value }
                            Industry { value }
                        }
                    }
                }
                Case(where: { AccountId: { inq: { Account: {
                    and: [
                    { Name: { like: "United%" } },
                    { Industry: { eq: "Energy" } }
        ]},
                    ApiName:"Id" } } }) {
                    edges {
                        node {
                            AccountId { value }
                            Priority { value }
                            Subject { value }
                        }
                    }
                }
            }
        }
    }*/
}
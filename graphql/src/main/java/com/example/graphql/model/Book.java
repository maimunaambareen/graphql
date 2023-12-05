package com.example.graphql.model;

/*public record Book(Integer id, String title, Integer pages, Rating rating, Author author) {
}*/

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Book {
    @Id
    @GeneratedValue
    private Integer id;
    private String title;
    private Integer pages;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id")
    private List<Review> reviews;
    private String author;

    //@Enumerated(EnumType.ORDINAL) gives the index of the enum example: 5 star is index 0; 1 star is index 4
    @Enumerated(EnumType.STRING)
    private Rating rating;

    public Book() {
    }

    public Book(String title, Integer pages,String author) {
        this.title = title;
        this.pages = pages;
        this.author = author;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", pages=" + pages +
                ", reviews=" + reviews +
                ", author=" + author +
                ", rating=" + rating +
                '}';
    }
}

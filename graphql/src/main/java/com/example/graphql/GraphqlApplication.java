package com.example.graphql;

import com.example.graphql.model.Book;
import com.example.graphql.model.Rating;
import com.example.graphql.model.Review;
import com.example.graphql.repository.BookRepository;
import graphql.com.google.common.collect.ImmutableList;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class GraphqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(GraphqlApplication.class, args);
	}
	@Bean
	public CommandLineRunner initData(BookRepository bookRepository) {
		return args -> {
			Book book = new Book("Reactive Spring", 484, "Josh Long");
			Review review = new Review("Great book!", "I really enjoyed this book!");
			Review review1 = new Review("Nice!", "I learned from this book!");
			book.setReviews(ImmutableList.of(review, review1));
			book.setRating(Rating.FIVE_STARS);
			bookRepository.save(book);
		};
	}

}

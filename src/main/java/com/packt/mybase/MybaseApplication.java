package com.packt.mybase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.util.Arrays;
import org.springframework.transaction.annotation.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.packt.mybase.domain.Book;
import com.packt.mybase.domain.BookRepository;
import com.packt.mybase.domain.Author;
import com.packt.mybase.domain.AuthorRepository;

@SpringBootApplication
public class MybaseApplication implements CommandLineRunner {	
	private static final Logger logger = 
			LoggerFactory.getLogger(MybaseApplication.class);


	@Autowired
    private BookRepository repository;
    

    @Autowired
    private AuthorRepository arepository;

	public static void main(String[] args) {
		SpringApplication.run(MybaseApplication.class, args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		// Add owner objects and save these to db 
		Author author1 = new Author("Michelle" , "Zauner", 1989);
		Author author2 = new Author("Maggie" , "Shipstead", 1983);
		Author author3 = new Author("Claire" , "Keegan", 1968);
		Author author4 = new Author("Gabrielle" , "Zevin", 1977);
		arepository.saveAll(Arrays.asList(author1, author2, author3, author4));

		// Add car object and link to owners and save these to db
		Book book1 = new Book("Crying in H mart", "Memoir", 2021, 4.26f, author1);
		Book book2 = new Book("Great Circle", "Historical Fiction", 2021, 4.11f, author2);
		Book book3 = new Book("Small Things Like These", "Fiction", 2021, 4.18f, author3);
		Book book4 = new Book("Tomorrow, and Tomorrow, and Tomorrow", "Fiction", 2022, 4.15f, author4);
		repository.saveAll(Arrays.asList(book1, book2, book3, book4));
                  
		for (Book book : repository.findAll()) {
			Author a = book.getAuthor();
			logger.info(book.getTitle() + " " + a.getLastname());
		}
	}
}

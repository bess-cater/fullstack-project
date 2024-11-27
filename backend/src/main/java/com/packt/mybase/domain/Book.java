package com.packt.mybase.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity
public class Book {

    @Id
	@GeneratedValue(strategy=GenerationType.AUTO) //? for automatic ID Key generation
	private long id;
	private String title, genre;
	@Column(name="`pubYear`", length=512)
	private int pubYear;
	@Column(precision = 4, scale = 2)
	private BigDecimal rating;

    //? getter, setter
    public Book() {}

    public Book(String title, String genre, int pubYear, BigDecimal rating) {
		super();
		this.title = title;
		this.genre = genre;
		this.pubYear = pubYear;
		this.rating = rating;
        // this.author = author;

	}
    // @ManyToOne(fetch = FetchType.LAZY)
	// @JoinColumn(name = "author")
	// private Author author;

	//Getter  and  setter
	// public Author getAuthor()  {
	//     return author;
	// }

	// public void setAuthor(Author author)  {
	//     this.author = author;
	// }
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGenre() {
		return genre;
	}

	public void setModel(String genre) {
		this.genre = genre;
	}

	public int getPubYear() {
		return pubYear;
	}

	public void setPubYear(int pubYear) {
		this.pubYear = pubYear;
	}

	public BigDecimal getRating() {
		return rating;
	}

	public void setRating(BigDecimal rating) {
		this.rating = rating;
	}


}

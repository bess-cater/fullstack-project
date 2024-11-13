package com.packt.mybase.domain;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Author {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long authorid;
	private String firstname, lastname;
	private int birthYear;
	
	public Author() {}

	public Author(String firstname, String lastname, int birthYear) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.birthYear = birthYear;
	}
	
	@JsonIgnore
	@OneToMany(cascade=CascadeType.ALL, mappedBy="author")
	private List<Book> books;
	
	public List<Book> getBooks()  {
		return books;
	}
	
	public void setBooks(List<Book> books)  {
		this.books = books;
	}

	public long getAuthorid() {
		return authorid;
	}

	public void setAuthorid(long authorid) {
		this.authorid = authorid;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public int getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}
}
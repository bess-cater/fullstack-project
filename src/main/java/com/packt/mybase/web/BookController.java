package com.packt.mybase.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.packt.mybase.domain.Book;
import com.packt.mybase.domain.BookRepository;

@RestController
public class BookController {

    @Autowired
    private BookRepository bookrepo;

    @RequestMapping("/allbooks")
    public Iterable<Book> getBooks(){
        return bookrepo.findAll();

    }

}

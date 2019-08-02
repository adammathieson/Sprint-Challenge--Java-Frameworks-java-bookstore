package com.lambdaschool.bookstore.controllers;

import com.lambdaschool.bookstore.services.AuthorService;
import com.lambdaschool.bookstore.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/data")
public class DataController
{
    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    
}

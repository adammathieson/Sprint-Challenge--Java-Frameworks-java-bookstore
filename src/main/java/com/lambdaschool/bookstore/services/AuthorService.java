package com.lambdaschool.bookstore.services;

import com.lambdaschool.bookstore.models.Author;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AuthorService
{
    List<Author> findAll(Pageable pageable);

    List<Author> findAll();

    Author findAuthorById(long id);
}

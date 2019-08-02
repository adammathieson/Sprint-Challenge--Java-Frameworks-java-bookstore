package com.lambdaschool.bookstore.services;

import com.lambdaschool.bookstore.exceptions.ResourceNotFoundException;
import com.lambdaschool.bookstore.models.Author;
import com.lambdaschool.bookstore.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

public class AuthorServiceImpl implements AuthorService
{
    @Autowired
    private AuthorRepository authorrepo;

    @Override
    public List<Author> findAll(Pageable pageable)
    {
        List<Author> list = new ArrayList<>();
        authorrepo.findAll(pageable).iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public List<Author> findAll()
    {
        List<Author> list = new ArrayList<>();
        authorrepo.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Author findAuthorById(long id)
    {
        return authorrepo.findById(id).orElseThrow(() -> new ResourceNotFoundException(Long.toString(id)));
    }
}

package com.lambdaschool.bookstore.services;

import com.lambdaschool.bookstore.exceptions.ResourceNotFoundException;
import com.lambdaschool.bookstore.models.Author;
import com.lambdaschool.bookstore.models.Book;
import com.lambdaschool.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

public class BookServiceImpl implements BookService
{
    @Autowired
    private BookRepository bookrepo;

    @Override
    public List<Book> findAll(Pageable pageable)
    {
        List<Book> list = new ArrayList<>();
        bookrepo.findAll(pageable).iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public List<Book> findAll()
    {
        List<Book> list = new ArrayList<>();
        bookrepo.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Book findBookById(long id)
    {
        return bookrepo.findById(id).orElseThrow(() -> new ResourceNotFoundException(Long.toString(id)));
    }

    @Override
    public void delete(long id)
    {
        if (bookrepo.findById(id).isPresent())
        {
            bookrepo.deleteById(id);
        }
        else
            throw new ResourceNotFoundException(Long.toString(id));
    }

    @Override
    public Book update(Book book, long id)
    {
        Book uBook = bookrepo.findById(id).orElseThrow(() -> new ResourceNotFoundException(Long.toString(id)));
        if (book.getTitle() != null)
        {
            uBook.setTitle(book.getTitle());
        }
        if (book.getISBN() != null)
        {
            uBook.setISBN(book.getISBN());
        }
        uBook.setCopy(book.getCopy());

        return bookrepo.save(uBook);
    }

    @Override
    public Book save(Book book)
    {
        Book newBook = new Book();

        if (book.getTitle() != null)
        {
            newBook.setTitle(book.getTitle());
        }
        if (book.getISBN() != null)
        {
            newBook.setISBN(book.getISBN());
        }

        newBook.setCopy(book.getCopy());

        for (Author a : book.getAuthors())
        {
            newBook.getAuthors().add(a);
        }

        return bookrepo.save(newBook);
    }
}

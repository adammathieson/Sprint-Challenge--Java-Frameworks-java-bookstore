package com.lambdaschool.bookstore.controllers;

import com.lambdaschool.bookstore.models.Author;
import com.lambdaschool.bookstore.models.Book;
import com.lambdaschool.bookstore.models.ErrorDetail;
import com.lambdaschool.bookstore.services.AuthorService;
import com.lambdaschool.bookstore.services.BookService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/data")
public class DataController
{
    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    // UpdateBook =====================================
    @ApiOperation(value = "Udates a books info", consumes = "Book", response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Book Successfully Updated", response = void.class),
            @ApiResponse(code = 404, message = "Book Not Found", response = ErrorDetail.class),
            @ApiResponse(code = 500, message = "Error Updating Book", response = ErrorDetail.class),
    })
    @PutMapping(value = "/books/{id}", produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<?> updateBook(@ApiParam(name = "Book Id", required = true) @PathVariable long id, @RequestBody @Valid Book book)
    {
        bookService.update(book, id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // assignBookAuthor =======================================
    @PostMapping(value = "/books/{bookid}/authors/{authorid}")
    public ResponseEntity<?> assignBookAuthor(@PathVariable long bookid, @PathVariable long authorid)
    {
        Book book = bookService.findBookById(bookid);
        Author author = authorService.findAuthorById(authorid);

        book.getAuthors().add(author);
        Book b = bookService.save(book);

        return new ResponseEntity<>(b, HttpStatus.CREATED);
    }
}

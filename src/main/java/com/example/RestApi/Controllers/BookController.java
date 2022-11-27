package com.example.RestApi.Controllers;

import com.example.RestApi.Models.Book;
import com.example.RestApi.Services.BookService;
import com.example.RestApi.dao.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController()
public class BookController {

    @Autowired(required=true)
    private BookRepository bookRepository;
    @Autowired
    private BookService bookService;

    @GetMapping("api/books")
    public ResponseEntity<List<Book>> getBooks()
    {
        List<Book> books=bookRepository.findAll();
        if(books.size()<=0)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        else
            return ResponseEntity.of(Optional.of(books));
    }

    @GetMapping("api/books/{id}")
    public ResponseEntity<Book>getBookById(@PathVariable("id") int id)
    {
        Book book= bookService.getBook(id);
        if(book==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        else
            return ResponseEntity.of(Optional.of(book));

    }

    @GetMapping("api/book/{name}")
    public ResponseEntity<List<Book>> getBookByName(@PathVariable("name") String name)
    {
        List<Book> books= bookService.findbyname(name);
        if(books.size()<=0)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        else
            return ResponseEntity.of(Optional.of(books));
    }

    @PostMapping("api/books")
    public ResponseEntity<Book> addBook(@RequestBody Book book)
    {
        try
        {
            Book book1=bookRepository.save(book);
            return ResponseEntity.of(Optional.of(book1));

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    @PutMapping("api/books/{id}")
    public ResponseEntity<Book> updateBook(@RequestBody Book book, @PathVariable int id)
    {
        try {
            Book book1=bookService.updateBook(book,id);
            return ResponseEntity.of(Optional.of(book1));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @DeleteMapping("api/books/{id}")

    public  ResponseEntity<Book> deleteBook(@PathVariable int id)
    {
        try {
            Book book=bookService.getBook(id);
            bookRepository.delete(book);
            return ResponseEntity.of(Optional.of(book));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }


    }

}

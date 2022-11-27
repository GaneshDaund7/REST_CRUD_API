package com.example.RestApi.Services;

import com.example.RestApi.Models.Book;
import com.example.RestApi.dao.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;
    public List<Book> getBooks()
    {
        List<Book> mybooks=bookRepository.findAll();
        return mybooks;
    }
    public Book getBook(int id)
    {
        List<Book> mybooks=bookRepository.findAll();
        Book requiredbook=null;
        for(Book b:mybooks)
        {
            if(b.getBookId()==id)
            {
                requiredbook=b;
                break;
            }

        }
        if(requiredbook==null)
            return null;
        else
            return requiredbook;

    }
    public List<Book> findbyname(String name)
    {
        List<Book>resultList=new ArrayList<>();
        List<Book> books=bookRepository.findAll();
        for(Book book:books)
        {
            if(book.getName().equals(name))
                resultList.add(book);
        }
        return resultList;
    }

    public Book updateBook(Book book,int id)
    {
        Book book1=getBook(id);
        book1.setName(book.getName());
        book1.setAuthor(book.getAuthor());
        bookRepository.save(book1);
        return  book1;
    }
}

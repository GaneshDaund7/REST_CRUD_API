package com.example.RestApi.dao;

import com.example.RestApi.Models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
    public List<Book> findByName(String name);
}

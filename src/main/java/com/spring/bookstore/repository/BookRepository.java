package com.spring.bookstore.repository;

import com.spring.bookstore.entity.BookEntity;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;
@Repository
public interface BookRepository extends CrudRepository<BookEntity, Integer>{
        List<BookEntity> findByNameContainingOrAuthorContaining(String searchInput, String searchInput1);
        List<BookEntity> findAll();
}

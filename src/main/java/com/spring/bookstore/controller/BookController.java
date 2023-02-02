package com.spring.bookstore.controller;

import com.spring.bookstore.entity.BookEntity;
import com.spring.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = "/book")
public class BookController {

        @Autowired
        BookRepository bookRepository;

    @GetMapping(produces = "application/json")
        public List<BookEntity> showBooks(Model model) {
            List<BookEntity> bookList = (List<BookEntity>) bookRepository.findAll();
            model.addAttribute("bookList", bookList);
            return bookList;
        }

        @RequestMapping(value = "/index")
        public String index(){
            return "home";
        }

        @GetMapping(value = "/search")
        public List<BookEntity> search(@RequestParam("searchInput") String searchInput, Model model) {
            List<BookEntity> resultList;
            if (searchInput.isEmpty()) {
                resultList = (List<BookEntity>) bookRepository.findAll();
            } else {
                resultList = bookRepository.findByNameContainingOrAuthorContaining(searchInput, searchInput);
            }
            model.addAttribute("bookList",resultList);
            return resultList;
        }

        @PostMapping()
        public Object addBook(@RequestBody BookEntity newBook) {
        BookEntity result = bookRepository.save(newBook);
        return result;
        }
        @PutMapping()
        public Object updateBook(@RequestBody BookEntity updateBook) {
        BookEntity result = bookRepository.findById(updateBook.getId()).get();
        if (result == null) {
            Map<String, String> error = new HashMap<String, String>() {
                {
                    put("Error", updateBook.getId() + " not exist");
                }};
            return error;
            }
        result = updateBook;
        bookRepository.save(result);
        return result;
        }

    @DeleteMapping(value = "/{id}")
    public Object updateBook(@PathVariable("id") int id) {
        BookEntity result = bookRepository.findById(id).get();
        if (result == null) {
            Map<String, String> error = new HashMap<String, String>() {
                {
                    put("Error",+ id + " not exist");
                }};
            return error;
        }
        bookRepository.deleteById(id);
        Map<String, String> success = new HashMap<String, String>() {
            {
                put("success","A Book Which ID ="+ id + " has been deleted successfully");
            }};
        return success;
    }
    }

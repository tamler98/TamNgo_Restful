package com.spring.bookstore.controller;

import com.spring.bookstore.entity.BookEntity;
import com.spring.bookstore.repository.BookRepository;
import com.spring.bookstore.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping(value = "/")
public class BookController {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @RequestMapping(method = GET)
    public String showBooks(Model model) {
        List<BookEntity> bookList = (List<BookEntity>) bookRepository.findAll();
        model.addAttribute("bookList", bookList);
        return "home";
    }

    @RequestMapping(value = "/index")
    public String index(){
        return "book";
    }

    @RequestMapping(value = "/search", method = GET)
    public String search(@RequestParam("searchInput") String searchInput, Model model) {
        List<BookEntity> resultList;
        if (searchInput.isEmpty()) {
            resultList = (List<BookEntity>) bookRepository.findAll();
        } else {
            resultList = bookRepository.findByNameContainingOrAuthorContaining(searchInput, searchInput);
        }
        model.addAttribute("bookList",resultList);
        return "home";
    }

}

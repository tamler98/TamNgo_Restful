package com.spring.bookstore.controller;

import com.spring.bookstore.entity.BookEntity;
import com.spring.bookstore.entity.CategoryEntity;
import com.spring.bookstore.repository.BookRepository;
import com.spring.bookstore.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping(value = "/")
public class CategoryController {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    CategoryRepository categoryRepository;

    @RequestMapping(value = "/newCategory", method = GET)
    public String showNewBook(Model model) {
        model.addAttribute("category", new CategoryEntity());
        model.addAttribute("msg", "Add a new category");
        model.addAttribute("action", "newCategory");

        return "category";
    }

    @RequestMapping(value = "/newCategory", method = POST, produces = "text/plain;charset=UTF-8")
    public String saveBook(CategoryEntity category) {
        categoryRepository.save(category);

        return "redirect:/";
    }
}

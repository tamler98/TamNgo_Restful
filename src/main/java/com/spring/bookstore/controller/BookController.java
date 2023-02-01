package com.spring.bookstore.controller;

import com.spring.bookstore.entity.BookEntity;
import com.spring.bookstore.entity.CategoryEntity;
import com.spring.bookstore.repository.BookRepository;
import com.spring.bookstore.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

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
            return "home";
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

        @RequestMapping(value = "/newBook", method = GET)
        public String showNewBook(Model model) {
            model.addAttribute("page", "getPage");
            model.addAttribute("type", "Add New Book");
            model.addAttribute("book", new BookEntity());
            model.addAttribute("msg", "Add a new book");
            model.addAttribute("action", "newBook");

            setCategoryDropDownList(model);
            return "book";
        }

        @RequestMapping(value = "/newBook", method = POST, produces = "text/plain;charset=UTF-8")
        public String saveBook(@Valid @ModelAttribute("book") BookEntity book, BindingResult result, Model model) {
            model.addAttribute("page", "getPage");
            // bookRepository.save(book);
            if(result.hasErrors() || book.getCategory().getId() == 0) {
                setCategoryDropDownList(model);
                if (book.getCategory().getId() == 0) {
                    model.addAttribute("message", "Please choose category");
                }
                return "book";
        }
            bookRepository.save(book);
            return "redirect:/";
        }

        @GetMapping(value = "/edit/{id}")
        public String showEditBook(Model model, @PathVariable int id) {
            model.addAttribute("msg", "Update book information");
            model.addAttribute("type", "Update Book");
            model.addAttribute("action", "updateBook");
            model.addAttribute("book", bookRepository.getByID(id));
            setCategoryDropDownList(model);
            if (bookRepository.getByID(id) != null) {
                return "book";
            }else {
                model.addAttribute("id", id);
            }
            return "notfound";
        }

        @PostMapping(value = "/edit/updateBook")
        public String updateBook(@Valid @ModelAttribute("book") BookEntity book, BindingResult result, Model model) {
            if(result.hasErrors() || book.getCategory().getId() == 0) {
                setCategoryDropDownList(model);
                if (book.getCategory().getId() == 0) {
                    model.addAttribute("message", "Please choose category");
                }
                return "book";
            }
            bookRepository.save(book);
            return "redirect:/";
        }

        @RequestMapping(value = "/delete/{id}", method = GET)
        public String deleteBook(@PathVariable int id) {

            bookRepository.deleteById(id);
            return "redirect:/";
        }

        @InitBinder
        public void initBinder(WebDataBinder binder) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(true);
            binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
        }
        private void setCategoryDropDownList(Model model) {
            List<CategoryEntity> cateList = (List<CategoryEntity>) categoryRepository.findAll();
            if (!cateList.isEmpty()) {
                Map<Integer, String> cateMap = new LinkedHashMap<>();
                for (CategoryEntity categoryEntity: cateList) {
                    cateMap.put(categoryEntity.getId(), categoryEntity.getName());
                }
                model.addAttribute("categoryList", cateMap);
            }
        }
    }

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
    public String index() {
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
        model.addAttribute("bookList", resultList);
        return resultList;
    }


    @RequestMapping(method = RequestMethod.POST)
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
                }
            };
            return error;
        }
        result = updateBook;
        bookRepository.save(result);
        return result;
    }

    @DeleteMapping(value = "/{id}")
    public Object deleteBook(@PathVariable(value = "id") int id) {
        BookEntity foundBook = null;
        for(BookEntity bookEntity: bookRepository.findAll()) {
            if (bookEntity.getId() == id) {
                foundBook = bookEntity;
                break;
            }
        }
        if (foundBook != null) {
            bookRepository.deleteById(foundBook.getId());
            Map<String, String> success = new HashMap<String, String>() {{
                put("success", "A Book Which ID =" + id + " has been deleted successfully");
            }};
            return success;
        } else {
            Map<String, String> error = new HashMap<String, String>() {{
                put("error", "A Book which ID = " + id + " does not exist");
            }};
            return error;
        }
    }
}

package com.example.buoi5.controller;


import com.example.buoi5.model.Book;
import com.example.buoi5.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("books", service.getAll());
        return "book-list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("book", new Book());
        return "book-form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Book book) {
        service.save(book);
        return "redirect:/books";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("book", service.getById(id));
        return "book-form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/books";
    }
}
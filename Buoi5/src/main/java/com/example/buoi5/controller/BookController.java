package com.example.buoi5.controller;
import com.example.buoi5.repository.CategoryRepository;
import com.example.buoi5.model.Category;
import com.example.buoi5.model.Book;
import com.example.buoi5.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService service;
    private final CategoryRepository categoryRepo;
    public BookController(BookService service, CategoryRepository categoryRepo) {
        this.service = service;
        this.categoryRepo = categoryRepo;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("books", service.getAll());
        return "book-list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("categories", categoryRepo.findAll());
        return "book-form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Book book) {

        if (book.getCategory() != null && book.getCategory().getId() != null) {
            Category category = categoryRepo
                    .findById(book.getCategory().getId())
                    .orElse(null);
            book.setCategory(category);
        }

        service.save(book);
        return "redirect:/books";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("book", service.getById(id));
        model.addAttribute("categories", categoryRepo.findAll());
        return "book-form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/books";
    }
}
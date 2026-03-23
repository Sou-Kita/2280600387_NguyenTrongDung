package com.example.buoi5.controller;
import com.example.buoi5.repository.CategoryRepository;
import com.example.buoi5.model.Category;
import com.example.buoi5.model.Book;
import com.example.buoi5.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.Comparator;
import java.util.List;

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
    public String list(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) String sort,
            @RequestParam(defaultValue = "0") int page,
            Model model) {

        List<Book> books = service.getAll();

        // 🔍 SEARCH
        if (keyword != null && !keyword.isEmpty()) {
            books = books.stream()
                    .filter(b -> b.getTitle().toLowerCase().contains(keyword.toLowerCase()))
                    .toList();
        }

        // 🔽 FILTER
        if (categoryId != null) {
            books = books.stream()
                    .filter(b -> b.getCategory() != null && b.getCategory().getId().equals(categoryId))
                    .toList();
        }

        // 🔼 SORT
        if ("asc".equals(sort)) {
            books = books.stream()
                    .sorted(Comparator.comparing(Book::getPrice))
                    .toList();
        } else if ("desc".equals(sort)) {
            books = books.stream()
                    .sorted(Comparator.comparing(Book::getPrice).reversed())
                    .toList();
        }

        // 📄 PAGINATION (manual)
        int pageSize = 5;
        int start = page * pageSize;
        int end = Math.min(start + pageSize, books.size());

        List<Book> pageList = books.subList(start, end);

        model.addAttribute("books", pageList);
        model.addAttribute("categories", categoryRepo.findAll());

        model.addAttribute("keyword", keyword);
        model.addAttribute("categoryId", categoryId);
        model.addAttribute("sort", sort);

        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", (int) Math.ceil((double) books.size() / pageSize));

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
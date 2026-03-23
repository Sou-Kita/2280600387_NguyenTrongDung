package com.example.buoi5.service;

import com.example.buoi5.model.Book;
import com.example.buoi5.repository.BookRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import org.springframework.data.domain.*;

@Service
public class BookService {

    private final BookRepository repo;

    public BookService(BookRepository repo) {
        this.repo = repo;
    }

    public List<Book> getAll() {
        return repo.findAll();
    }

    public List<Book> search(String keyword) {
        return repo.findByTitleContainingIgnoreCase(keyword);
    }

    public List<Book> filter(Long categoryId) {
        return repo.findByCategoryId(categoryId);
    }

    public Page<Book> getPage(int page) {
        return repo.findAll(PageRequest.of(page, 5));
    }

    public Book getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public void save(Book b) {
        repo.save(b);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
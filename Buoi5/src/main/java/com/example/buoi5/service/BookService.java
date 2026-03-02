package com.example.buoi5.service;

import com.example.buoi5.model.Book;
import com.example.buoi5.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public Book getById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
}
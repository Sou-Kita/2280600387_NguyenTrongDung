package com.example.buoi5.repository;

import com.example.buoi5.model.Book;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByTitleContainingIgnoreCase(String keyword);

    List<Book> findByCategoryId(Long categoryId);
}
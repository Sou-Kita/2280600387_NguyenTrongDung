package com.example.buoi5.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "book")
@Getter
@Setter
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    private Double price;
    private LocalDate publishedDate;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Book(String title, String author, Double price, LocalDate publishedDate, Category category) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.publishedDate = publishedDate;
        this.category = category;
    }
}
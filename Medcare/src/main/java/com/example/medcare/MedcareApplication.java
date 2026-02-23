package com.example.medcare;

import com.example.medcare.model.Product;
import com.example.medcare.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MedcareApplication {

    public static void main(String[] args) {
        SpringApplication.run(MedcareApplication.class, args);
    }

    @Bean
    CommandLineRunner initProducts(ProductRepository repo) {
        return args -> {

            if (repo.count() == 0) {

                repo.save(Product.builder()
                        .name("Omega 3")
                        .description("Hỗ trợ tim mạch và giảm cholesterol")
                        .price(250000)
                        .imageUrl("/images/products/omega3.jpg")
                        .stock(50)
                        .build());

                repo.save(Product.builder()
                        .name("Vitamin D3")
                        .description("Tăng cường miễn dịch và xương chắc khỏe")
                        .price(180000)
                        .imageUrl("/images/products/vitamin-d3.jpg")
                        .stock(40)
                        .build());

                repo.save(Product.builder()
                        .name("Whey Protein")
                        .description("Hỗ trợ tăng cơ và phục hồi sau tập luyện")
                        .price(850000)
                        .imageUrl("/images/products/whey.jpg")
                        .stock(30)
                        .build());
            }
        };
    }
}
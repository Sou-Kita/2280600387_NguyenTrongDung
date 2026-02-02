package com.example.buoi4.controller;

import com.example.buoi4.model.Product;
import com.example.buoi4.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("products", service.getAll());
        return "products";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("product", new Product());
        return "add-product";
    }

    @PostMapping("/add")
    public String add(
            @Valid @ModelAttribute("product") Product product,
            BindingResult result,
            @RequestParam("file") MultipartFile file
    ) throws IOException {

        if (result.hasErrors()) return "add-product";

        if (!file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            Path path = Paths.get(uploadPath);

            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }

            Files.copy(
                    file.getInputStream(),
                    path.resolve(fileName),
                    StandardCopyOption.REPLACE_EXISTING
            );

            product.setImage(fileName);
        }

        service.add(product);
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("product", service.findById(id));
        return "edit-product";
    }

    @PostMapping("/edit")
    public String edit(
            @Valid @ModelAttribute("product") Product product,
            BindingResult result,
            @RequestParam("file") MultipartFile file
    ) throws IOException {

        if (result.hasErrors()) return "edit-product";

        if (!file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            Path path = Paths.get(uploadPath);

            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }

            Files.copy(
                    file.getInputStream(),
                    path.resolve(fileName),
                    StandardCopyOption.REPLACE_EXISTING
            );

            product.setImage(fileName);
        }

        service.update(product);
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/products";
    }
}

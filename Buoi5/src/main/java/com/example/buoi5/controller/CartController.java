package com.example.buoi5.controller;

import com.example.buoi5.model.Book;
import com.example.buoi5.model.Cart;
import com.example.buoi5.model.OrderDetail;
import com.example.buoi5.model.Orders;
import com.example.buoi5.repository.OrderDetailRepository;
import com.example.buoi5.repository.OrderRepository;
import com.example.buoi5.service.BookService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class CartController {

    private final BookService service;
    private final OrderRepository orderRepo;
    private final OrderDetailRepository detailRepo;

    public CartController(BookService service, OrderRepository orderRepo, OrderDetailRepository detailRepo) {
        this.service = service;
        this.orderRepo = orderRepo;
        this.detailRepo = detailRepo;
    }

    @GetMapping("/cart/add/{id}")
    public String add(@PathVariable Long id, HttpSession session) {

        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) cart = new Cart();

        cart.add(id);
        session.setAttribute("cart", cart);

        return "redirect:/books";
    }

    @GetMapping("/cart")
    public String view(HttpSession session, Model model) {

        Cart cart = (Cart) session.getAttribute("cart");
        Map<Book, Integer> data = new HashMap<>();
        double total = 0;

        if (cart != null) {
            for (var e : cart.getItems().entrySet()) {
                Book b = service.getById(e.getKey());

                if (b != null) {
                    int qty = e.getValue();
                    data.put(b, qty);
                    total += b.getPrice() * qty;
                }
            }
        }

        model.addAttribute("cart", data);
        model.addAttribute("total", total);

        return "cart";
    }

    @PostMapping("/checkout")
    public String checkout(HttpSession session) {

        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) return "redirect:/cart";

        Orders order = new Orders();
        double total = 0;

        order = orderRepo.save(order);

        for (var e : cart.getItems().entrySet()) {
            Book b = service.getById(e.getKey());

            if (b != null) {
                OrderDetail d = new OrderDetail();
                d.setOrder(order);
                d.setBook(b);
                d.setQuantity(e.getValue());

                detailRepo.save(d);
                total += b.getPrice() * e.getValue();
            }
        }

        order.setTotal(total);
        orderRepo.save(order);

        session.removeAttribute("cart");

        return "redirect:/books";
    }
    @GetMapping("/cart/increase/{id}")
    public String increase(@PathVariable Long id, HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart != null) cart.increase(id);
        return "redirect:/cart";
    }

    @GetMapping("/cart/decrease/{id}")
    public String decrease(@PathVariable Long id, HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart != null) cart.decrease(id);
        return "redirect:/cart";
    }

    @GetMapping("/cart/remove/{id}")
    public String remove(@PathVariable Long id, HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart != null) cart.remove(id);
        return "redirect:/cart";
    }
    @GetMapping("/checkout")
    public String checkoutPage() {
        return "checkout"; // mở trang checkout.html
    }
}
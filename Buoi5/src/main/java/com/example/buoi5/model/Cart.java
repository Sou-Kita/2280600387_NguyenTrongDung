package com.example.buoi5.model;

import java.util.*;

public class Cart {

    private Map<Long, Integer> items = new HashMap<>();

    public void add(Long id) {
        items.put(id, items.getOrDefault(id, 0) + 1);
    }

    public void increase(Long id) {
        items.put(id, items.get(id) + 1);
    }

    public void decrease(Long id) {
        int qty = items.get(id);
        if (qty <= 1) {
            items.remove(id);
        } else {
            items.put(id, qty - 1);
        }
    }

    public void remove(Long id) {
        items.remove(id);
    }

    public Map<Long, Integer> getItems() {
        return items;
    }
}
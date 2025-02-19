package com.example.grocerystore.service;


import com.example.grocerystore.data.GroceryItem;
import com.example.grocerystore.dto.GroceryItemRequest;
import com.example.grocerystore.repository.GroceryItemRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    private final GroceryItemRepository groceryItemRepository;

    public AdminService(GroceryItemRepository groceryItemRepository) {
        this.groceryItemRepository = groceryItemRepository;
    }

    public GroceryItem addGroceryItem(GroceryItemRequest request) {
        GroceryItem item = new GroceryItem();
        item.setName(request.getName());
        item.setPrice(request.getPrice());
        item.setQuantity(request.getQuantity());
        return groceryItemRepository.save(item);
    }

    public List<GroceryItem> getAllGroceryItems() {
        return groceryItemRepository.findAll();
    }

    public void deleteGroceryItem(String id) {
        groceryItemRepository.deleteById(id);
    }

    public GroceryItem updateGroceryItem(String id, GroceryItemRequest request) {
        return groceryItemRepository.findById(id)
                .map(item -> {
                    item.setName(request.getName());
                    item.setPrice(request.getPrice());
                    return groceryItemRepository.save(item);
                })
                .orElseThrow(() -> new RuntimeException("Grocery item not found"));
    }

    public GroceryItem updateInventory(String id, int quantity) {
        return groceryItemRepository.findById(id)
                .map(item -> {
                    item.setQuantity(quantity);
                    return groceryItemRepository.save(item);
                })
                .orElseThrow(() -> new RuntimeException("Grocery item not found"));
    }
}

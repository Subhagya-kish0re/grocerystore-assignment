package com.example.grocerystore.controller;

import com.example.grocerystore.data.GroceryItem;
import com.example.grocerystore.data.Order;
import com.example.grocerystore.dto.OrderRequest;
import com.example.grocerystore.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/grocery-items")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("getAllAvailableItems")
    public ResponseEntity<List<GroceryItem>> getAvailableGroceryItems() {
        return ResponseEntity.ok(userService.getAvailableGroceryItems());
    }

    @PostMapping("/orders")
    public ResponseEntity<Order> placeOrder(@RequestBody OrderRequest request) {
        return ResponseEntity.ok(userService.placeOrder(request));
    }
}
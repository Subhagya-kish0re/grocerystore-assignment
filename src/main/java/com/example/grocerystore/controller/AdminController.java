package com.example.grocerystore.controller;

import com.example.grocerystore.data.GroceryItem;
import com.example.grocerystore.dto.GroceryItemRequest;
import com.example.grocerystore.service.AdminService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/grocery-items")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("add-products")
    public ResponseEntity<GroceryItem> addGroceryItem(@RequestBody GroceryItemRequest request) {
        return ResponseEntity.ok(adminService.addGroceryItem(request));
    }

    @GetMapping("getAllItems")
    public ResponseEntity<List<GroceryItem>> getAllGroceryItems() {
        return ResponseEntity.ok(adminService.getAllGroceryItems());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGroceryItem(@PathVariable String id) {
        adminService.deleteGroceryItem(id);
        return ResponseEntity.ok("GroceryItem deleted successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<GroceryItem> updateGroceryItem(@PathVariable String id, @RequestBody GroceryItemRequest request) {
        return ResponseEntity.ok(adminService.updateGroceryItem(id, request));
    }

    @PatchMapping("/{id}/inventory")
    public ResponseEntity<GroceryItem> updateInventory(@PathVariable String id, @RequestBody int quantity) {
        return ResponseEntity.ok(adminService.updateInventory(id, quantity));
    }
}

package com.example.grocerystore.repository;

import com.example.grocerystore.data.GroceryItem;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroceryItemRepository extends MongoRepository<GroceryItem, String> {
    @Query("{ 'quantity' : { $gt: ?0 } }")
    List<GroceryItem> findByQuantityGreaterThan(int quantity);
}

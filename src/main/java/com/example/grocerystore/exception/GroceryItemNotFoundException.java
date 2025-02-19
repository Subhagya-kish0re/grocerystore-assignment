package com.example.grocerystore.exception;

public class GroceryItemNotFoundException extends RuntimeException {
    public GroceryItemNotFoundException(String message) {
        super(message);
    }
}

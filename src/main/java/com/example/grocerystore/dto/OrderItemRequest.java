package com.example.grocerystore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderItemRequest {
    private String groceryItemId;
    private int quantity;

    public String getGroceryItemId() {
        return groceryItemId;
    }

    public void setGroceryItemId(String groceryItemId) {
        this.groceryItemId = groceryItemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
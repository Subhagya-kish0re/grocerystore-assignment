package com.example.grocerystore.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Data
public class OrderItem {
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

    public OrderItem(String groceryItemId, int quantity) {
        this.groceryItemId = groceryItemId;
        this.quantity = quantity;
    }
}
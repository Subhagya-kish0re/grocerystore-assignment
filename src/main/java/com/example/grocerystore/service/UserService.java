package com.example.grocerystore.service;

import com.example.grocerystore.data.GroceryItem;
import com.example.grocerystore.data.Order;
import com.example.grocerystore.data.OrderItem;
import com.example.grocerystore.dto.OrderItemRequest;
import com.example.grocerystore.dto.OrderRequest;
import com.example.grocerystore.repository.GroceryItemRepository;
import com.example.grocerystore.repository.OrderRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final GroceryItemRepository groceryItemRepository;
    private final OrderRepository orderRepository;

    public UserService(GroceryItemRepository groceryItemRepository, OrderRepository orderRepository) {
        this.groceryItemRepository = groceryItemRepository;
        this.orderRepository = orderRepository;
    }

    public List<GroceryItem> getAvailableGroceryItems() {
        return groceryItemRepository.findByQuantityGreaterThan(0);
    }

    @Transactional
    public Order placeOrder(OrderRequest request) {
        List<OrderItem> orderItems = new ArrayList<>();
        List<GroceryItem> updatedItems = new ArrayList<>();
        double totalPrice = 0;

        for (OrderItemRequest itemRequest : request.getItems()) {
            GroceryItem item = groceryItemRepository.findById(itemRequest.getGroceryItemId())
                    .orElseThrow(() -> new RuntimeException("Item not found: " + itemRequest.getGroceryItemId()));
            if (item.getQuantity() < itemRequest.getQuantity()) {
                throw new RuntimeException("Insufficient stock for item: " + item.getId());
            }
            item.setQuantity(item.getQuantity() - itemRequest.getQuantity());
            updatedItems.add(item);
            orderItems.add(new OrderItem(item.getId(), itemRequest.getQuantity()));
            totalPrice += item.getPrice() * itemRequest.getQuantity();
        }

        groceryItemRepository.saveAll(updatedItems);

        Order order = new Order();
        order.setItems(orderItems);
        order.setTotalPrice(totalPrice);
        order.setCreatedAt(LocalDateTime.now());
        return orderRepository.save(order);
    }
}
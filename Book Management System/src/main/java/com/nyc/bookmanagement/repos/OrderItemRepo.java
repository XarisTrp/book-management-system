package com.nyc.bookmanagement.repos;

import com.nyc.bookmanagement.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepo extends JpaRepository<OrderItem, Integer> {
}

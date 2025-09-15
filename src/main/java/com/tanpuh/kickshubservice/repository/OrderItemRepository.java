package com.tanpuh.kickshubservice.repository;

import com.tanpuh.kickshubservice.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
}

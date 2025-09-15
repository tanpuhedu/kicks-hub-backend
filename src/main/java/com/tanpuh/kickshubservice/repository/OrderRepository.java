package com.tanpuh.kickshubservice.repository;

import com.tanpuh.kickshubservice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}

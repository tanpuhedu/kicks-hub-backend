package com.tanpuh.kickshubservice.repository;

import com.tanpuh.kickshubservice.entity.Cart;
import com.tanpuh.kickshubservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    Cart findByUser(User user);
}

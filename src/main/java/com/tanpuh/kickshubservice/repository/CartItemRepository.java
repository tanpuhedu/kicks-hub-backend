package com.tanpuh.kickshubservice.repository;

import com.tanpuh.kickshubservice.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    Optional<CartItem> findByCartIdAndProductDetailId(Integer cartId, Integer productDetailId);

    List<CartItem> findAllByCartId(Integer id);
}

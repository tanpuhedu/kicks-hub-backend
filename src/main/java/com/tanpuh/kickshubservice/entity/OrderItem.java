package com.tanpuh.kickshubservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@Builder
@AllArgsConstructor @NoArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    Integer productDetailId;
    String productDetailName;
    Long price;
    Integer quantity;
    Long subtotal;

    @ManyToOne
    Order order;
}

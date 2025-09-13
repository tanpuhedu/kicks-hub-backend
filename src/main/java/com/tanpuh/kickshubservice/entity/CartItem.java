package com.tanpuh.kickshubservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    Integer quantity;

    @ManyToOne
    ProductDetail productDetail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    Cart cart;
}

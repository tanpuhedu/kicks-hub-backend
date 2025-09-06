package com.tanpuh.kickshubservice.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDetail {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String code;
    String name;
    Long price;
    Integer stockQuantity;
    Integer soldQuantity;
    Integer status;

    @ManyToOne
    Product product;

    @ManyToOne
    Color color;

    @ManyToOne
    Size size;
}

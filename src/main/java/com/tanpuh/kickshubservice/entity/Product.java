package com.tanpuh.kickshubservice.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String code;
    String name;
    Long price;
    Integer stockQuantity;
    Integer soldQuantity;
    String description;
    Integer status;

    @ManyToOne
    Category category;
}

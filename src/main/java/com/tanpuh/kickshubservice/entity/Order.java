package com.tanpuh.kickshubservice.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Check;

import java.time.LocalDateTime;

@Entity
@Table(name = "`order`")
@Getter @Setter
@Builder(toBuilder = true)
@AllArgsConstructor @NoArgsConstructor
@Check(constraints = "status IN ('PENDING', 'ACCEPTED', 'PAID', 'SHIPPING','COMPLETED', 'CANCELLED')")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    Integer userId;
    String phone;
    String fullName;
    String status;
    Long totalAmount;
    String note;
    Long deliveryFee;
    String deliveryAddress;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}

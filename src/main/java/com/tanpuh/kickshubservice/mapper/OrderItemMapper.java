package com.tanpuh.kickshubservice.mapper;

import com.tanpuh.kickshubservice.dto.response.OrderItemResponse;
import com.tanpuh.kickshubservice.entity.OrderItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    OrderItemResponse toResponse(OrderItem entity);
}

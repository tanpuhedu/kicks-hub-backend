package com.tanpuh.kickshubservice.mapper;

import com.tanpuh.kickshubservice.dto.request.OrderRequest;
import com.tanpuh.kickshubservice.dto.response.OrderResponse;
import com.tanpuh.kickshubservice.entity.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    Order toEntity(OrderRequest dto);
    OrderResponse toResponse(Order entity);
}

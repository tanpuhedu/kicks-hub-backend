package com.tanpuh.kickshubservice.mapper;

import com.tanpuh.kickshubservice.dto.response.CartItemResponse;
import com.tanpuh.kickshubservice.entity.CartItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = ProductDetailMapper.class)
public interface CartItemMapper {
    CartItemResponse toResponse(CartItem entity);
}

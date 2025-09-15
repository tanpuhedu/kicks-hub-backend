package com.tanpuh.kickshubservice.mapper;

import com.tanpuh.kickshubservice.dto.response.CartResponse;
import com.tanpuh.kickshubservice.entity.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface CartMapper {
    @Mapping(target = "cartItems", ignore = true)
    CartResponse toResponse(Cart entity);
}

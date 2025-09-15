package com.tanpuh.kickshubservice.mapper;

import com.tanpuh.kickshubservice.dto.response.CartResponse;
import com.tanpuh.kickshubservice.entity.Cart;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface CartMapper {
    CartResponse toResponse(Cart entity);
}

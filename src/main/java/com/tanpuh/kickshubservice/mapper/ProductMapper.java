package com.tanpuh.kickshubservice.mapper;

import com.tanpuh.kickshubservice.dto.request.ProductCreationRequest;
import com.tanpuh.kickshubservice.dto.request.ProductUpdateRequest;
import com.tanpuh.kickshubservice.dto.response.ProductResponse;
import com.tanpuh.kickshubservice.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = CategoryMapper.class)
public interface ProductMapper {
    Product toEntity(ProductCreationRequest dto);
    ProductResponse toResponse(Product entity);

    @Mapping(target = "id", ignore = true)
    void update(@MappingTarget Product entity, ProductUpdateRequest dto);
}


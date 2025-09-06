package com.tanpuh.kickshubservice.mapper;

import com.tanpuh.kickshubservice.dto.request.ProductDetailCreationRequest;
import com.tanpuh.kickshubservice.dto.request.ProductDetailUpdateRequest;
import com.tanpuh.kickshubservice.dto.response.ProductDetailResponse;
import com.tanpuh.kickshubservice.entity.ProductDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {SizeMapper.class, ColorMapper.class, ProductMapper.class})
public interface ProductDetailMapper {
    ProductDetail toEntity(ProductDetailCreationRequest dto);
    ProductDetailResponse toResponse(ProductDetail entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "stockQuantity", ignore = true)
    void update(@MappingTarget ProductDetail entity, ProductDetailUpdateRequest dto);
}

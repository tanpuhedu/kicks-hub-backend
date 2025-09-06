package com.tanpuh.kickshubservice.mapper;

import com.tanpuh.kickshubservice.dto.request.CategoryRequest;
import com.tanpuh.kickshubservice.dto.response.CategoryResponse;
import com.tanpuh.kickshubservice.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
//    @Mapping(target = "id", ignore = true)
    Category toEntity(CategoryRequest dto);

    CategoryResponse toResponse(Category entity);

    @Mapping(target = "id", ignore = true)
    void update(@MappingTarget Category entity, CategoryRequest dto);
}

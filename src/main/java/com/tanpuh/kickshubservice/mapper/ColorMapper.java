package com.tanpuh.kickshubservice.mapper;

import com.tanpuh.kickshubservice.dto.request.ColorRequest;
import com.tanpuh.kickshubservice.dto.response.ColorResponse;
import com.tanpuh.kickshubservice.entity.Color;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ColorMapper {
    Color toEntity(ColorRequest dto);
    ColorResponse toResponse(Color entity);
    void update(@MappingTarget Color entity, ColorRequest dto);
}

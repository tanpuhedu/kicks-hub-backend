package com.tanpuh.kickshubservice.mapper;

import com.tanpuh.kickshubservice.dto.request.SizeRequest;
import com.tanpuh.kickshubservice.dto.response.SizeResponse;
import com.tanpuh.kickshubservice.entity.Size;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SizeMapper {
    Size toEntity(SizeRequest dto);

    SizeResponse toResponse(Size entity);

    @Mapping(target = "id", ignore = true)
    void update(@MappingTarget Size entity, SizeRequest dto);
}

package com.tanpuh.kickshubservice.service.color;

import com.tanpuh.kickshubservice.dto.request.ColorRequest;
import com.tanpuh.kickshubservice.dto.response.ColorResponse;

import java.util.List;

public interface ColorService {
    List<ColorResponse> getAll();
    ColorResponse getById(Integer id);
    ColorResponse create(ColorRequest request);
    ColorResponse update(Integer id, ColorRequest request);
    void delete(Integer id);
}

package com.tanpuh.kickshubservice.service.category;

import com.tanpuh.kickshubservice.dto.request.CategoryRequest;
import com.tanpuh.kickshubservice.dto.response.CategoryResponse;

import java.util.List;

public interface CategoryService {
    List<CategoryResponse> getAll();
    CategoryResponse getById(Integer id);
    CategoryResponse create(CategoryRequest request);
    CategoryResponse update(Integer id, CategoryRequest request);
    void delete(Integer id);
}

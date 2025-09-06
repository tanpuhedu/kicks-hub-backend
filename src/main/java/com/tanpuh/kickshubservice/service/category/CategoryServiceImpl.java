package com.tanpuh.kickshubservice.service.category;

import com.tanpuh.kickshubservice.dto.request.CategoryRequest;
import com.tanpuh.kickshubservice.dto.response.CategoryResponse;
import com.tanpuh.kickshubservice.entity.Category;
import com.tanpuh.kickshubservice.exception.AppException;
import com.tanpuh.kickshubservice.exception.ErrorCode;
import com.tanpuh.kickshubservice.mapper.CategoryMapper;
import com.tanpuh.kickshubservice.repository.CategoryRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryServiceImpl implements CategoryService {
    CategoryRepository categoryRepository;
    CategoryMapper mapper;

    @Override
    public List<CategoryResponse> getAll() {
        return categoryRepository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public CategoryResponse getById(Integer id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));

        return mapper.toResponse(category);
    }

    @Override
    public CategoryResponse create(CategoryRequest request) {
        if (categoryRepository.existsByName(request.getName()))
            throw new AppException(ErrorCode.CATEGORY_EXISTED);

        Category category = mapper.toEntity(request);

        return mapper.toResponse(categoryRepository.save(category));
    }

    @Override
    public CategoryResponse update(Integer id, CategoryRequest request) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));

        mapper.update(category, request);

        return mapper.toResponse(categoryRepository.save(category));
    }

    @Override
    public void delete(Integer id) {
        categoryRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));

        categoryRepository.deleteById(id);
    }
}

package com.tanpuh.kickshubservice.service.product;

import com.tanpuh.kickshubservice.dto.request.ProductCreationRequest;
import com.tanpuh.kickshubservice.dto.request.ProductUpdateRequest;
import com.tanpuh.kickshubservice.dto.response.PageResponse;
import com.tanpuh.kickshubservice.dto.response.ProductResponse;

import java.util.List;

public interface ProductService {
    List<ProductResponse> getAll();
    PageResponse<ProductResponse> getAllByCriteria(String keyword, List<Integer> categoryIds, List<Integer> colorIds,
                                                   Long minPrice, Long maxPrice, String sortBy, String sortDir,
                                                   int pageIdx, int pageLimit);
    List<ProductResponse> search(String name, String code, Integer categoryId, Integer pageIdx, Integer pageSize);
    ProductResponse getById(Integer id);
    ProductResponse create(ProductCreationRequest request);
    ProductResponse update(ProductUpdateRequest request, Integer id);
    void delete(Integer id);
}

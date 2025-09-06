package com.tanpuh.kickshubservice.service.product_detail;

import com.tanpuh.kickshubservice.dto.request.ProductDetailCreationRequest;
import com.tanpuh.kickshubservice.dto.request.ProductDetailUpdateRequest;
import com.tanpuh.kickshubservice.dto.response.ProductDetailResponse;

import java.util.List;

public interface ProductDetailService {
    List<ProductDetailResponse> getAll();
    ProductDetailResponse getById(Integer id);
    ProductDetailResponse create(ProductDetailCreationRequest request);
    ProductDetailResponse update(ProductDetailUpdateRequest request, Integer id);
    void delete(Integer id);

}

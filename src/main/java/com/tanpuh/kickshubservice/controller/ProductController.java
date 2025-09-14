package com.tanpuh.kickshubservice.controller;

import com.tanpuh.kickshubservice.dto.request.ProductCreationRequest;
import com.tanpuh.kickshubservice.dto.request.ProductUpdateRequest;
import com.tanpuh.kickshubservice.dto.response.ApiResponse;
import com.tanpuh.kickshubservice.dto.response.PageResponse;
import com.tanpuh.kickshubservice.dto.response.ProductResponse;
import com.tanpuh.kickshubservice.service.product.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping()
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Tag(name = "Product Controller")
@CrossOrigin(origins = "http://localhost:5173")
@Slf4j
public class ProductController {
    ProductService productService;

    @GetMapping("/backoffice/products")
    public ApiResponse<List<ProductResponse>> getAll(){
        return ApiResponse.<List<ProductResponse>>builder()
                .data(productService.getAll())
                .message("Get list of products successfully")
                .build();
    }

    @GetMapping("/storefront/products")
    @Operation(summary = "get list of products by criteria")
    public ApiResponse<PageResponse<ProductResponse>> getAllByCriteria(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) List<Integer> categoryIds,
            @RequestParam(required = false) List<Integer> colorIds,
            @RequestParam(required = false) Long minPrice,
            @RequestParam(required = false) Long maxPrice,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "ASC") String sortDir,
            @RequestParam(defaultValue = "0") int pageIdx,
            @RequestParam(defaultValue = "10") int pageLimit
    ) {
        return ApiResponse.<PageResponse<ProductResponse>>builder()
                .data(productService.getAllByCriteria(
                        keyword, categoryIds, colorIds, minPrice, maxPrice, sortBy, sortDir, pageIdx, pageLimit))
                .message("Get list of products by criteria successfully")
                .build();
    }

    @GetMapping(path = {"/backoffice/products/{id}", "/storefront/products/{id}"})
    @Operation(summary = "get product by id")
    public ApiResponse<ProductResponse> getById(@PathVariable Integer id) {
        return ApiResponse.<ProductResponse>builder()
                .data(productService.getById(id))
                .message("Get product successfully")
                .build();
    }

    @GetMapping("/storefront/search")
    @Operation(summary = "search product with keyword")
    public ApiResponse<List<ProductResponse>> search(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "categoryId", required = false) Integer categoryId,
            @RequestParam(value = "pageIdx", required = false) Integer pageIdx,
            @RequestParam(value = "pageSize", required = false) Integer pageSize
    ) {
        return ApiResponse.<List<ProductResponse>>builder()
                .data(productService.search(name, code, categoryId, pageIdx, pageSize))
                .message("Get list of products successfully")
                .build();
    }

    @PostMapping(path = "/backoffice/products", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "create new product")
    public ApiResponse<ProductResponse> create(@ModelAttribute @Valid ProductCreationRequest request) {
        return ApiResponse.<ProductResponse>builder()
                .message("Create product successfully")
                .data(productService.create(request))
                .build();
    }

    @PutMapping(path = "/backoffice/products/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "update product by id")
    public ApiResponse<ProductResponse> update(
            @PathVariable Integer id,
            @ModelAttribute @Valid ProductUpdateRequest request
    ) {
        return ApiResponse.<ProductResponse>builder()
                .message("Update product successfully")
                .data(productService.update(request, id))
                .build();
    }

    @DeleteMapping("/backoffice/products/{id}")
    @Operation(summary = "delete product by id")
    public ApiResponse<Void> delete(@PathVariable Integer id) {
        productService.delete(id);
        return ApiResponse.<Void>builder()
                .message("Delete product successfully")
                .build();
    }
}

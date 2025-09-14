package com.tanpuh.kickshubservice.service.product;

import com.tanpuh.kickshubservice.dto.request.ProductCreationRequest;
import com.tanpuh.kickshubservice.dto.request.ProductUpdateRequest;
import com.tanpuh.kickshubservice.dto.response.ProductResponse;
import com.tanpuh.kickshubservice.entity.Category;
import com.tanpuh.kickshubservice.entity.Product;
import com.tanpuh.kickshubservice.exception.AppException;
import com.tanpuh.kickshubservice.exception.ErrorCode;
import com.tanpuh.kickshubservice.mapper.ProductMapper;
import com.tanpuh.kickshubservice.repository.CategoryRepository;
import com.tanpuh.kickshubservice.repository.ProductDetailRepository;
import com.tanpuh.kickshubservice.repository.ProductRepository;
import com.tanpuh.kickshubservice.service.cloudinary.CloudinaryService;
import com.tanpuh.kickshubservice.utils.enums.EntityStatus;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductServiceImpl implements ProductService {
    ProductRepository productRepository;
    CategoryRepository categoryRepository;
    ProductDetailRepository productDetailRepository;
    ProductMapper mapper;
    CloudinaryService cloudinaryService;

    @Override
    public List<ProductResponse> getAll() {
        return productRepository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public Page<ProductResponse> getAllByCriteria (
            String keyword,
            List<Integer> categoryIds, List<Integer> colorIds,
            Long minPrice, Long maxPrice,
            String sortBy, String sortDir,
            int pageIdx, int pageLimit
    ) {
        Sort sort = sortDir.equalsIgnoreCase("asc") ?
                Sort.by(sortBy).ascending() :
                Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageIdx, pageLimit, sort);

        Page<Product> products = productRepository.getAllByCriteria(
                keyword,
                categoryIds == null || categoryIds.isEmpty() ? null : categoryIds,
                colorIds == null || colorIds.isEmpty() ? null : colorIds,
                minPrice,
                maxPrice,
                pageable
        );

        return products.map(mapper::toResponse);
    }

    @Override
    public List<ProductResponse> search(String name, String code, Integer categoryId,
                                        Integer pageIdx, Integer pageSize) {
        pageIdx = (pageIdx == null) ? 0 : pageIdx;
        pageSize = (pageSize == null) ? 5 : pageSize;

        Page<Product> pageEntity = productRepository
                .search(name, code, categoryId, PageRequest.of(pageIdx, pageSize));

//        return pageEntity.getContent() // pageEntity.getContent() return List<T> T là sản phẩm được lấy ra
//                .stream()
//                .map(mapper::toResponse)
//                .toList();

        return pageEntity.map(mapper::toResponse).toList();
    }

    @Override
    public ProductResponse getById(Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));

        return mapper.toResponse(product);
    }

    @Override
    public ProductResponse create(ProductCreationRequest request) {
        if (productRepository.existsByCode(request.getCode()))
            throw new AppException(ErrorCode.PRODUCT_EXISTED);

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));

        Product product = mapper.toEntity(request);
        product.setPrice(0L);
        product.setStockQuantity(0);
        product.setSoldQuantity(0);
        product.setStatus(EntityStatus.INACTIVE.getCode());
        product.setCategory(category);
        product.setImgURLs(uploadImagesToCloudinary(request.getImgFiles()));

        return mapper.toResponse(productRepository.save(product));
    }

    @Override
    public ProductResponse update(ProductUpdateRequest request, Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));

        mapper.update(product, request);
        product.setCategory(category);
        product.setImgURLs(uploadImagesToCloudinary(request.getImgFiles()));

        return mapper.toResponse(productRepository.save(product));
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));

        productDetailRepository.deleteAllByProductId(product.getId());

        productRepository.deleteById(id);
    }

    private List<String> uploadImagesToCloudinary(List<MultipartFile> files) {
        List<String> imgURLs = new ArrayList<>();
        for (MultipartFile file : files) {
            String url = cloudinaryService.upload(file, "images");

            if (url == null)
                throw new RuntimeException("Uploading fail");

            imgURLs.add(url);
        }
        return imgURLs;
    }
}

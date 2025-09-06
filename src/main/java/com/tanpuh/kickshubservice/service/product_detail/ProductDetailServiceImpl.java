package com.tanpuh.kickshubservice.service.product_detail;

import com.tanpuh.kickshubservice.dto.request.ProductDetailCreationRequest;
import com.tanpuh.kickshubservice.dto.request.ProductDetailUpdateRequest;
import com.tanpuh.kickshubservice.dto.response.ProductDetailResponse;
import com.tanpuh.kickshubservice.entity.Color;
import com.tanpuh.kickshubservice.entity.Product;
import com.tanpuh.kickshubservice.entity.ProductDetail;
import com.tanpuh.kickshubservice.entity.Size;
import com.tanpuh.kickshubservice.exception.AppException;
import com.tanpuh.kickshubservice.exception.ErrorCode;
import com.tanpuh.kickshubservice.mapper.ProductDetailMapper;
import com.tanpuh.kickshubservice.repository.ColorRepository;
import com.tanpuh.kickshubservice.repository.ProductDetailRepository;
import com.tanpuh.kickshubservice.repository.ProductRepository;
import com.tanpuh.kickshubservice.repository.SizeRepository;
import com.tanpuh.kickshubservice.utils.enums.EntityStatus;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductDetailServiceImpl implements ProductDetailService {
    ProductDetailRepository productDetailRepository;
    ProductRepository productRepository;
    ColorRepository colorRepository;
    SizeRepository sizeRepository;
    ProductDetailMapper mapper;

    @Override
    public List<ProductDetailResponse> getAll() {
        return productDetailRepository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public ProductDetailResponse getById(Integer id) {
        ProductDetail productDetail = productDetailRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_DETAIL_NOT_FOUND));

        return mapper.toResponse(productDetail);
    }

    @Override
    @Transactional
    public ProductDetailResponse create(ProductDetailCreationRequest request) {
        if (productDetailRepository.existsByCode(request.getCode()))
            throw new AppException(ErrorCode.PRODUCT_DETAIL_EXISTED);

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));

        Size size = sizeRepository.findById(request.getSizeId())
                .orElseThrow(() -> new AppException(ErrorCode.SIZE_NOT_FOUND));

        Color color = colorRepository.findById(request.getColorId())
                .orElseThrow(() -> new AppException(ErrorCode.COLOR_NOT_FOUND));

        ProductDetail productDetail = mapper.toEntity(request);
        productDetail.setStatus(EntityStatus.ACTIVE.getCode());
        productDetail.setSoldQuantity(0);
        productDetail.setProduct(product);
        productDetail.setSize(size);
        productDetail.setColor(color);
        productDetailRepository.save(productDetail);

        // xử lí stockQty của product
        product.setStockQuantity(product.getStockQuantity() + request.getStockQuantity());

        // xử lí status của product
        boolean hasActiveDetail = productDetailRepository
                .existsByProductIdAndStatus(product.getId(), EntityStatus.ACTIVE.getCode());
        product.setStatus(hasActiveDetail ? EntityStatus.ACTIVE.getCode() : EntityStatus.INACTIVE.getCode());

        // xử lí price của product
        Long minPrice = updateProductPrice(product);
        product.setPrice(minPrice);

        productRepository.save(product);

        return mapper.toResponse(productDetail);
    }

    @Override
    @Transactional
    public ProductDetailResponse update(ProductDetailUpdateRequest request, Integer id) {
        ProductDetail productDetail = productDetailRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_DETAIL_NOT_FOUND));

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));

        Size size = sizeRepository.findById(request.getSizeId())
                .orElseThrow(() -> new AppException(ErrorCode.SIZE_NOT_FOUND));

        Color color = colorRepository.findById(request.getColorId())
                .orElseThrow(() -> new AppException(ErrorCode.COLOR_NOT_FOUND));

        mapper.update(productDetail, request);
        productDetail.setProduct(product);
        productDetail.setSize(size);
        productDetail.setColor(color);
        // xử lí stockQty của product detail
        productDetail.setStockQuantity(productDetail.getStockQuantity() + request.getStockQuantity());
        productDetailRepository.save(productDetail);

        // xử lí stockQty của product
        product.setStockQuantity(product.getStockQuantity() + request.getStockQuantity());

        // xử lí status của product
        boolean hasActiveDetail = productDetailRepository
                .existsByProductIdAndStatus(product.getId(), EntityStatus.ACTIVE.getCode());
        product.setStatus(hasActiveDetail ? EntityStatus.ACTIVE.getCode() : EntityStatus.INACTIVE.getCode());

        // xử lí price của product
        Long minPrice = updateProductPrice(product);
        product.setPrice(minPrice);

        productRepository.save(product);

        return mapper.toResponse(productDetail);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        ProductDetail productDetail = productDetailRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_DETAIL_NOT_FOUND));

        Product product = productRepository.findById(productDetail.getProduct().getId())
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));

        // xử lí stockQty của product
        product.setStockQuantity(product.getStockQuantity() - productDetail.getStockQuantity());

        // xử lí status của product
        boolean hasActiveDetail = productDetailRepository
                .existsByProductIdAndStatus(product.getId(), EntityStatus.ACTIVE.getCode());
        product.setStatus(hasActiveDetail ? EntityStatus.ACTIVE.getCode() : EntityStatus.INACTIVE.getCode());

        // xử lí price của product
        Long minPrice = updateProductPrice(product);
        product.setPrice(minPrice);

        productRepository.save(product);

        productDetailRepository.deleteById(id);
    }

    private Long updateProductPrice(Product product) {
        return productDetailRepository.findByProductId(product.getId())
                .stream()
                .map(ProductDetail::getPrice)
                .min(Long::compareTo)
                .orElse(0L); // nếu xóa hết detail thì để 0
    }
}

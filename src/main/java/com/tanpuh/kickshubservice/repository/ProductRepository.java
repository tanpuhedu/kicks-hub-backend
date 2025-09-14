package com.tanpuh.kickshubservice.repository;

import com.tanpuh.kickshubservice.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    //    dùng concat của HQL để nối 2 chuỗi bằng dấu phẩy, vì native query ko làm đc
    @Query("""
            select p from Product p
            where (:name is null or upper(p.name) like upper(concat('%',:name,'%')))
            and (:code is null or upper(p.code) like upper(concat('%',:code,'%')))
            and (:categoryId is null or p.category.id = :categoryId)
            """)
    Page<Product> search(
            @Param("name") String name,
            @Param("code") String code,
            @Param("categoryId") Integer categoryId,
            PageRequest pageable
    );

    @Query(value = """
        SELECT DISTINCT p
        FROM Product p
        LEFT JOIN ProductDetail pd ON pd.product = p
        WHERE (:keyword IS NULL OR UPPER(p.name) LIKE UPPER(CONCAT('%', :keyword, '%')))
          AND (:categoryIds IS NULL OR p.category.id IN :categoryIds)
          AND (:colorIds IS NULL OR pd.color.id IN :colorIds)
          AND (:minPrice IS NULL OR p.price >= :minPrice)
          AND (:maxPrice IS NULL OR p.price <= :maxPrice)
        """)
    Page<Product> getAllByCriteria(
            @Param("keyword") String keyword,
            @Param("categoryIds") List<Integer> categoryIds,
            @Param("colorIds") List<Integer> colorIds,
            @Param("minPrice") Long minPrice,
            @Param("maxPrice") Long maxPrice,
            Pageable pageable
    );

    boolean existsByCode(String code);
}

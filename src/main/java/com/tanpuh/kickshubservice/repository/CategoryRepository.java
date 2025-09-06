package com.tanpuh.kickshubservice.repository;

import com.tanpuh.kickshubservice.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    boolean existsByName(String name);
}

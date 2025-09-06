package com.tanpuh.kickshubservice.repository;

import com.tanpuh.kickshubservice.entity.Size;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SizeRepository extends JpaRepository<Size, Integer> {
    boolean existsByName(String name);
}

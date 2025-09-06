package com.tanpuh.kickshubservice.repository;

import com.tanpuh.kickshubservice.entity.Color;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColorRepository extends JpaRepository<Color, Integer> {
    boolean existsByName(String name);
}

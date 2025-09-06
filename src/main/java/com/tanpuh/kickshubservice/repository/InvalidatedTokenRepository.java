package com.tanpuh.kickshubservice.repository;

import com.tanpuh.kickshubservice.entity.InvalidatedToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvalidatedTokenRepository extends JpaRepository<InvalidatedToken, String> {
}

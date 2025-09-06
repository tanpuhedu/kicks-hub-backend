package com.tanpuh.kickshubservice.repository;

import com.tanpuh.kickshubservice.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Integer>{
}

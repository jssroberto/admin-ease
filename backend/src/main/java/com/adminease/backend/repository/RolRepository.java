package com.adminease.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.adminease.backend.model.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {
}

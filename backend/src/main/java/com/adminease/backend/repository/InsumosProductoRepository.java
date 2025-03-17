package com.adminease.backend.repository;

import com.adminease.backend.model.InsumosProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsumosProductoRepository extends JpaRepository<InsumosProducto, Long> {
}

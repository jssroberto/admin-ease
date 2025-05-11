package com.adminease.backend.repository;

import com.adminease.backend.model.Salida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalidaRepository extends JpaRepository<Salida, Long> {
    
}

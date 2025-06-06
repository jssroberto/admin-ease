package com.adminease.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.adminease.backend.model.Permiso;

@Repository
public interface PermisoRepository extends JpaRepository<Permiso, Long> {
}

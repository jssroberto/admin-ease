package com.adminease.backend.repository;

import com.adminease.backend.model.Permiso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PermisoRepository extends JpaRepository<Permiso, Long> {
}

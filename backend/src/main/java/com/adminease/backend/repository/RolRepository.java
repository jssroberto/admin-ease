package com.adminease.backend.repository;

import com.adminease.backend.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {
}

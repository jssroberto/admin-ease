package com.adminease.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.adminease.backend.model.MermaInsumo;

@Repository
public interface MermaInsumoRepository extends JpaRepository<MermaInsumo, Long> {
}

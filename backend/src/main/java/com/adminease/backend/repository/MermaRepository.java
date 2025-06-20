package com.adminease.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.adminease.backend.model.Merma;

@Repository
public interface MermaRepository extends JpaRepository<Merma, Long> {
}

package com.adminease.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MovimientoInsumo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movimientoInsumoId;

    private Timestamp fecha;
    private BigDecimal cantidad;
    private String tipoMovimiento;

    @ManyToOne
    @JoinColumn(name = "insumo_id")
    private Insumo insumo;
}

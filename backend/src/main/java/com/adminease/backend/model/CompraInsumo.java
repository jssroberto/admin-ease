package com.adminease.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CompraInsumo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long compraInsumoId;

    @OneToOne
    @JoinColumn(name = "movimiento_insumo_id")
    private MovimientoInsumo movimientoInsumo;

    @ManyToOne
    @JoinColumn(name = "compra_id")
    private Compra compra;

    private BigDecimal precio;

}

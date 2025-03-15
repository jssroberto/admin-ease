package com.adminease.backend.model;

import jakarta.persistence.*;

@Entity
public class MermaInsumo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mermaInsumoId;

    @OneToOne
    @JoinColumn(name = "movimiento_insumo_id")
    private MovimientoInsumo movimientoInsumo;

    @ManyToOne
    @JoinColumn(name = "merma_id")
    private Merma merma;

    private String razon;
}

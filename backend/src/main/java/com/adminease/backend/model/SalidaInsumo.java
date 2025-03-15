package com.adminease.backend.model;

import jakarta.persistence.*;

@Entity
public class SalidaInsumo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long salidaInsumoId;

    @OneToOne
    @JoinColumn(name = "movimiento_insumo_id")
    private MovimientoInsumo movimientoInsumo;

    @ManyToOne
    @JoinColumn(name = "salida_id")
    private Salida salida;
}

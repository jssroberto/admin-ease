package com.adminease.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
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

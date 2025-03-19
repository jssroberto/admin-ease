package com.adminease.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
@DiscriminatorValue("COMPRA")
public class CompraInsumo extends MovimientoInsumo {

    @NotNull
    @Positive
    @Column(nullable = false)
    private Double precioUnitario;

    @ManyToOne
    @JoinColumn(name = "compra_id")
    private Compra compra;

}

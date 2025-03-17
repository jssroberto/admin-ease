package com.adminease.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue("SALIDA")
public class SalidaInsumo extends MovimientoInsumo{

    @NotNull
    @ManyToOne
    @JoinColumn(name = "salida_id")
    private Salida salida;

}

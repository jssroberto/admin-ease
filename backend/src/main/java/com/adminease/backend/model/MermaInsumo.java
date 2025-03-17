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
public class MermaInsumo extends MovimientoInsumo{

    @NotNull
    @ManyToOne
    private RazonMerma razon;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "merma_id")
    private Merma merma;

}

package com.adminease.backend.mapper;

import com.adminease.backend.dtos.CompraInsumoDTO;
import com.adminease.backend.dtos.MovimientoInsumoDTO;
import com.adminease.backend.dtos.CompraDTO;
import com.adminease.backend.model.CompraInsumo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import com.adminease.backend.model.CompraInsumo;


@Mapper(uses = {MovimientoInsumoMapper.class, CompraMapper.class})
public interface CompraInsumoMapper{
    CompraInsumoMapper INSTANCE = Mappers.getMapper(CompraInsumoMapper.class);


    // Mapeo de CompraInsumo a CompraInsumoDTO
    @Mapping(source = "compraInsumoId", target = "compraInsumoId")
    @Mapping(source = "movimientoInsumo.movimientoInsumoId", target = "movimientoInsumo.movimientoInsumoId")
    @Mapping(source = "compra.compraId", target = "compra.compraId")
    @Mapping(source = "precio", target = "precio")
    CompraInsumoDTO toDTO(CompraInsumo compraInsumo);

    // Mapeo de CompraInsumoDTO a CompraInsumo
    @Mapping(source = "compraInsumoId", target = "compraInsumoId")
    @Mapping(source = "movimientoInsumo.movimientoInsumoId", target = "movimientoInsumo.movimientoInsumoId")
    @Mapping(source = "compra.compraId", target = "compra.compraId")
    @Mapping(source = "precio", target = "precio")
    CompraInsumo toEntity(CompraInsumoDTO compraInsumoDTO);
    }

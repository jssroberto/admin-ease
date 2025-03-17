package com.adminease.backend.mapper;

import com.adminease.backend.dto.MovimientoInsumoDTO;
import org.mapstruct.Mapper;
import com.adminease.backend.model.MovimientoInsumo;

@Mapper(componentModel = "spring")
public interface MovimientoInsumoMapper {
    MovimientoInsumoDTO toDTO(MovimientoInsumo movimientoInsumo);
    MovimientoInsumo toEntity(MovimientoInsumoDTO movimientoInsumoDTO);
}

//package com.adminease.backend.mapper;
//
//import com.adminease.backend.service.dto.MovimientoInsumoDto;
//import com.adminease.backend.model.MovimientoInsumo;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//
//@Mapper(componentModel = "spring", uses = {InsumoMapper.class})
//public interface MovimientoInsumoMapper {
//
//    @Mapping(source = "insumo.id", target = "insumoId")
//    MovimientoInsumoDto toDto(MovimientoInsumo entity);
//
//    @Mapping(source = "insumoId", target = "insumo", qualifiedByName = "idToInsumo")
//    MovimientoInsumo toEntity(MovimientoInsumoDto dto);
//
//
//}
//

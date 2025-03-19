//package com.adminease.backend.mapper;
//
//import com.adminease.backend.dto.CompraInsumoDTO;
//import com.adminease.backend.model.CompraInsumo;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.mapstruct.factory.Mappers;
//
//@Mapper(uses = {CompraMapper.class})
//public interface CompraInsumoMapper {
//
//    CompraInsumoMapper INSTANCE = Mappers.getMapper(CompraInsumoMapper.class);
//
//    @Mapping(source = "compra", target = "compraDTO")
//    CompraInsumoDTO toDTO(CompraInsumo compraInsumo);
//
//    @Mapping(source = "compraDTO", target = "compra")
//    CompraInsumo toEntity(CompraInsumoDTO compraInsumoDTO);
//}

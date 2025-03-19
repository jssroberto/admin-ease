//package com.adminease.backend.mapper;
//
//import com.adminease.backend.dto.InsumoDTO;
//import com.adminease.backend.model.Insumo;
//import com.adminease.backend.repository.InsumoRepository;
//import org.mapstruct.Context;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.mapstruct.Named;
//import org.mapstruct.factory.Mappers;
//
//@Mapper(uses = {UnidadMedidaMapper.class, CategoriaInsumoMapper.class, InsumosProductoMapper.class})
//public interface InsumoMapper {
//
//    InsumoMapper INSTANCE = Mappers.getMapper(InsumoMapper.class);
//
//    @Mapping(source = "unidadMedida", target = "unidadMedidaDTO")
//    @Mapping(source = "categoriaInsumo", target = "categoriaInsumoDTO")
//    @Mapping(source = "insumosProductos", target = "insumosProductoDTOS")
//    InsumoDTO toDTO(Insumo insumo);
//
//    @Mapping(source = "unidadMedidaDTO", target = "unidadMedida")
//    @Mapping(source = "categoriaInsumoDTO", target = "categoriaInsumo")
//    @Mapping(source = "insumosProductoDTOS", target = "insumosProductos")
//    Insumo toEntity(InsumoDTO insumoDTO);
//
//    @Named("idToInsumo")
//    default Insumo idToInsumo(Long insumoId, @Context InsumoRepository insumoRepository) {
//        if (insumoId == null) {
//            return null;
//        }
//        return insumoRepository.findById(insumoId)
//                .orElseThrow(() -> new IllegalArgumentException("Insumo not found with ID: " + insumoId));
//    }
//}

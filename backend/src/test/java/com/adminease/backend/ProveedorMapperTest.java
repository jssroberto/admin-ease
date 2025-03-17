//package com.adminease.backend;
//
//import com.adminease.backend.dto.ProveedorDTO;
//import com.adminease.backend.model.Proveedor;
//import com.adminease.backend.mapper.ProveedorMapper;
//import org.junit.jupiter.api.Test;
//import org.mapstruct.factory.Mappers;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class ProveedorMapperTest {
//
//    ProveedorMapper proveedorMapper = Mappers.getMapper(ProveedorMapper.class);
//
//    @Test
//    void shouldMapToDTO() {
//        // Arrange
//        Proveedor proveedor = new Proveedor();
//        proveedor.setId(1L);
//        proveedor.setNombre("Test");
//
//        // Act
//        ProveedorDTO proveedorDTO = proveedorMapper.toDTO(proveedor);
//
//        // Assert
//        assertEquals(proveedor.getId(), proveedorDTO.getId());
//        assertEquals(proveedor.getNombre(), proveedorDTO.getNombre());
//    }
//
//    @Test
//    void shouldMapToEntity() {
//        // Arrange
//        ProveedorDTO proveedorDTO = new ProveedorDTO(1L, "Proveedor1");
//
//        // Act
//        Proveedor proveedor = proveedorMapper.toEntity(proveedorDTO);
//
//        // Assert
//        assertEquals(proveedorDTO.getId(), proveedor.getId());
//        assertEquals(proveedorDTO.getNombre(), proveedor.getNombre());
//    }
//}

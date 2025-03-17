//package com.adminease.backend;
//
//import com.adminease.backend.dto.CompraDTO;
//import com.adminease.backend.dto.CompraInsumoDTO;
//import com.adminease.backend.dto.MovimientoInsumoDTO;
//import com.adminease.backend.model.Compra;
//import com.adminease.backend.model.CompraInsumo;
//import com.adminease.backend.model.MovimientoInsumo;
//import com.adminease.backend.mapper.CompraInsumoMapper;
//import org.junit.jupiter.api.Test;
//import java.math.BigDecimal;
//import static org.junit.jupiter.api.Assertions.*;
//
//class CompraInsumoMapperTest {
//
//    @Test
//    void testToDTO() {
//        // Crear entidad de prueba
//        MovimientoInsumo movimiento = new CompraInsumo();
//        movimiento.setId(1L);
//
//        Compra compra = new Compra();
//        compra.setId(2L);
//
//        CompraInsumo compraInsumo = new CompraInsumo();
//        compraInsumo.setId(3L);
//        compraInsumo.setCompra(movimiento);
//        compraInsumo.setCompra(compra);
//        compraInsumo.setPrecio(new BigDecimal("150.75"));
//
//        // Convertir a DTO
//        CompraInsumoDTO dto = CompraInsumoMapper.INSTANCE.toDTO(compraInsumo);
//
//        // Verificar que los valores sean correctos
//        assertNotNull(dto);
//        assertEquals(3L, dto.getCompraInsumoId());
//        assertEquals(1L, dto.getMovimientoInsumo().getMovimientoInsumoId());
//        assertEquals(2L, dto.getCompra().getCompraId());
//        assertEquals(new BigDecimal("150.75"), dto.getPrecio());
//    }
//
//    @Test
//    void testToEntity() {
//        // Crear DTO de prueba
//        MovimientoInsumoDTO movimientoDTO = new MovimientoInsumoDTO(1L, null, null, null, null); // Ajustar los valores según corresponda
//        CompraDTO compraDTO = new CompraDTO();
//        compraDTO.setCompraId(2L);
//
//        CompraInsumoDTO dto = new CompraInsumoDTO(3L, movimientoDTO, compraDTO, new BigDecimal("150.75"));
//
//        // Convertir a entidad
//        CompraInsumo entity = CompraInsumoMapper.INSTANCE.toEntity(dto);
//
//        // Verificar que los valores sean correctos
//        assertNotNull(entity);
//        assertEquals(3L, entity.getCompraInsumoId());
//        assertNotNull(entity.getMovimientoInsumo());
//        assertEquals(1L, entity.getMovimientoInsumo().getMovimientoInsumoId());
//        assertNotNull(entity.getCompra());
//        assertEquals(2L, entity.getCompra().getCompraId());
//        assertEquals(new BigDecimal("150.75"), entity.getPrecio());
//    }
//}
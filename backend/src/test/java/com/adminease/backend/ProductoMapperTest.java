//package com.adminease.backend;
//
//import com.adminease.backend.dto.ProductoDTO;
//import com.adminease.backend.model.InsumoProducto;
//import com.adminease.backend.model.Producto;
//import com.adminease.backend.mapper.ProductoMapper;
//import org.junit.jupiter.api.Test;
//import org.mapstruct.factory.Mappers;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class ProductoMapperTest {
//
//    ProductoMapper productoMapper = Mappers.getMapper(ProductoMapper.class);
//
//    @Test
//    void shouldMapToDTO() {
//        // Arrange
//        Producto producto = new Producto(1L, "Producto1", "Categoria1", List.of(new InsumoProducto(), new InsumoProducto()));
//
//        // Act
//        ProductoDTO productoDTO = productoMapper.toDTO(producto);
//
//        // Assert
//        assertEquals(producto.getId(), productoDTO.getProductoId());
//        assertEquals(producto.getNombre(), productoDTO.getNombre());
//        assertEquals(producto.getCategoria(), productoDTO.getCategoria());
//        assertEquals(producto.getInsumoProductos().size(), productoDTO.getInsumosProductoIds().size());
//    }
//
//    @Test
//    void shouldMapToEntity() {
//        // Arrange
//        ProductoDTO productoDTO = new ProductoDTO(1L, "Producto1", "Categoria1", List.of(1L, 2L));
//
//        // Act
//        Producto producto = productoMapper.toEntity(productoDTO);
//
//        // Assert
//        assertEquals(productoDTO.getProductoId(), producto.getId());
//        assertEquals(productoDTO.getNombre(), producto.getNombre());
//        assertEquals(productoDTO.getCategoria(), producto.getCategoria());
//        assertEquals(productoDTO.getInsumosProductoIds().size(), producto.getInsumoProductos().size());
//    }
//}

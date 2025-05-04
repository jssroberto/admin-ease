package com.adminease.backend.service;

import com.adminease.backend.api.dto.request.CompraInsumoRequest;
import com.adminease.backend.api.dto.request.CompraRequest;
import com.adminease.backend.api.dto.response.CompraInsumoResponse;
import com.adminease.backend.api.dto.response.CompraResponse;
import com.adminease.backend.mapper.CompraInsumoMapper;
import com.adminease.backend.mapper.CompraMapper;
import com.adminease.backend.model.Compra;
import com.adminease.backend.model.CompraInsumo;
import com.adminease.backend.model.Proveedor;
import com.adminease.backend.model.Usuario;
import com.adminease.backend.repository.CompraInsumoRepository;
import com.adminease.backend.repository.CompraRepository;
import com.adminease.backend.repository.ProveedorRepository;
import com.adminease.backend.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompraService {

    private final CompraRepository compraRepository;
    private final ProveedorRepository proveedorRepository;
    private final UsuarioRepository usuarioRepository;
    private final CompraMapper compraMapper;
    private final CompraInsumoRepository compraInsumoRepository;
    private final CompraInsumoService compraInsumoService;

    public List<CompraResponse> getAllCompras() {
        List<Compra> compras = compraRepository.findAll();
        if (compras.isEmpty()) {
            throw new EntityNotFoundException("No se encontraron compras");
        }
        return compras.stream()
                .map(compraMapper::toResponse)
                .toList();
    }

    public CompraResponse findById(Long id) {
        Compra compra = compraRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Compra con id " + id + " no encontrada"));

        return compraMapper.toResponse(compra);
    }

    public List<CompraResponse> findByProveedorId(Long proveedorId) {
        List<Compra> compras = compraRepository.findByProveedorId(proveedorId);

        if (compras.isEmpty()) {
            throw new EntityNotFoundException("No se encontraron compras para el proveedor con ID: " + proveedorId);
        }

        return compras.stream()
                .map(compraMapper::toResponse)
                .toList();
    }

    public List<CompraResponse> findByFechaBetween(ZonedDateTime fechaInicio, ZonedDateTime fechaFin) {
        List<Compra> compras = compraRepository.findByFechaBetween(fechaInicio, fechaFin);

        if (compras.isEmpty()) {
            throw new EntityNotFoundException("No se encontraron compras en ese rango de fechas");
        }

        return compras.stream()
                .map(compraMapper::toResponse)
                .toList();
    }

    public List<CompraResponse> findByProveedorIdAndFechaBetween(Long proveedorId, ZonedDateTime fechaInicio, ZonedDateTime fechaFin) {
        List<Compra> compras = compraRepository.findByProveedorIdAndFechaBetween(proveedorId, fechaInicio, fechaFin);

        if (compras.isEmpty()) {
            throw new EntityNotFoundException("No se encontraron compras del proveedor con ID " + proveedorId + " en ese rango de fechas");
        }

        return compras.stream()
                .map(compraMapper::toResponse)
                .toList();
    }

    public List<CompraResponse> findByTotalGreaterThan(Double minimo) {
        List<Compra> compras = compraRepository.findByTotalGreaterThan(minimo);

        if (compras.isEmpty()) {
            throw new EntityNotFoundException("No se encontraron compras con total mayor a " + minimo);
        }

        return compras.stream()
                .map(compraMapper::toResponse)
                .toList();
    }

    public List<CompraResponse> findByTotalLessThan(Double maximo) {
        List<Compra> compras = compraRepository.findByTotalLessThan(maximo);

        if (compras.isEmpty()) {
            throw new EntityNotFoundException("No se encontraron compras con total menor a " + maximo);
        }

        return compras.stream()
                .map(compraMapper::toResponse)
                .toList();
    }

    public CompraResponse createCompra(CompraRequest request) {
        Proveedor proveedor = proveedorRepository.findById(request.getProveedorId())
                .orElseThrow(() -> new EntityNotFoundException("Proveedor con ID " + request.getProveedorId() + " no encontrado"));

        Usuario usuario = usuarioRepository.findById(request.getUsuarioId())
                .orElseThrow(() -> new EntityNotFoundException("Usuario con ID " + request.getUsuarioId() + " no encontrado"));

        Compra compra = compraMapper.toEntity(request);
        compra.setProveedor(proveedor);
        compra.setUsuario(usuario);
        compra.setFecha(ZonedDateTime.now());

        compra = compraRepository.save(compra); // Guarda la compra sin insumos

        // Obtener entidades reales, no DTOs
        List<CompraInsumo> compraInsumos = compraInsumoService.createCompraInsumos(request.getCompraInsumos(), compra.getId());

        double total = compraInsumos.stream()
                .mapToDouble(ci -> ci.getCantidad() * ci.getPrecioUnitario())
                .sum();

        compra.setTotal(total);
        compra.setCompraInsumos(compraInsumos); // Ahora sí es correcto

        compra = compraRepository.save(compra);

        return compraMapper.toResponse(compra);
    }


    public CompraResponse updateCompra(Long id, CompraRequest request) {
        compraRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Compra con ID " + id + " no encontrado"));

        Compra compra = compraMapper.toEntity(request);
        compra = compraRepository.save(compra);

        return compraMapper.toResponse(compra);
    }

    public CompraResponse deleteCompra(Long id) {
       Compra compra = compraRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Compra no encontrado"));

        compraRepository.deleteById(id);

        return compraMapper.toResponse(compra);

    }

    public void updateTotalCompra(Long compraId) {
        Compra compra = compraRepository.findById(compraId)
                .orElseThrow(() -> new EntityNotFoundException("Compra no encontrada"));

        List<CompraInsumo> insumos = compraInsumoRepository.findByCompraId(compraId);

        double total = insumos.stream()
                .mapToDouble(ci -> ci.getCantidad() * ci.getPrecioUnitario())
                .sum();

        compra.setTotal(total);
        compraRepository.save(compra);
    }


}

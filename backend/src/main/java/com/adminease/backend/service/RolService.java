package com.adminease.backend.service;


import com.adminease.backend.api.dto.response.RolResponse;
import com.adminease.backend.mapper.RolMapper;
import com.adminease.backend.model.Rol;
import com.adminease.backend.repository.RolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RolService {

    private final RolRepository rolRepository;

    private final RolMapper rolMapper;

    public RolResponse findById(Long id) {
        Rol rol = rolRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Rol not found with ID: " + id));
        return rolMapper.toResponse(rol);
    }


}

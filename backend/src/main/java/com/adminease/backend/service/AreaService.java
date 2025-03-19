package com.adminease.backend.service;

import com.adminease.backend.api.dto.response.AreaResponse;
import com.adminease.backend.model.Area;
import com.adminease.backend.repository.AreaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AreaService {

    private final AreaRepository areaRepository;

    public AreaResponse getAreaById(Long id) {

        Optional<Area> area = areaRepository.findById(id);

        if (area.isEmpty()) {
            throw new RuntimeException("Area not found with ID: " + id);
        }

        return new AreaResponse(
                area.get().getId(),
                area.get().getNombre());
    }

}

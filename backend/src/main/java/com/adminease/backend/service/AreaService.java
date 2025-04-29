package com.adminease.backend.service;

import com.adminease.backend.api.dto.response.AreaResponse;
import com.adminease.backend.mapper.AreaMapper;
import com.adminease.backend.model.Area;
import com.adminease.backend.repository.AreaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AreaService {

    private final AreaRepository areaRepository;

    private final AreaMapper areaMapper;

    public AreaResponse getAreaById(Long id) {

        Optional<Area> area = areaRepository.findById(id);

        if (area.isEmpty()) {
            throw new RuntimeException("Area not found with ID: " + id);
        }

        return new AreaResponse(
                area.get().getId(),
                area.get().getNombre());
    }

    public List<AreaResponse> getAllAreas() {
        List<Area> areas = areaRepository.findAll();

        if (areas.isEmpty()) {
            throw new RuntimeException("No areas found");
        }

        return areas.stream()
                .map(areaMapper::toResponse)
                .toList();
    }

}

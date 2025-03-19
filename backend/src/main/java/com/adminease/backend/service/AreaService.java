package com.adminease.backend.service;

import com.adminease.backend.repository.AreaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AreaService {

    private final AreaRepository areaRepository;

}

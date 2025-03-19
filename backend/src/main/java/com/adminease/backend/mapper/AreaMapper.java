package com.adminease.backend.mapper;

import com.adminease.backend.api.dto.request.AreaRequest;
import com.adminease.backend.api.dto.response.AreaResponse;
import com.adminease.backend.model.Area;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AreaMapper {

    AreaResponse toResponse(Area area);

    Area toEntity(AreaRequest areaRequest);
}

package com.adminease.backend.controller;

import com.adminease.backend.api.dto.response.AreaResponse;
import com.adminease.backend.service.AreaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/area")
@RequiredArgsConstructor
@Tag(name = "Area", description = "Operations related to Area")
@CrossOrigin(origins = "*")
public class AreaController {

    private final AreaService areaService;

    @GetMapping
    @Operation(
            summary = "Get all Areas",
            description = "Retrieves a list of all Areas in the system."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Areas retrieved successfully",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<List<AreaResponse>> getAllAreas() {
        List<AreaResponse> responses = areaService.getAllAreas();
        return ResponseEntity.ok(responses);
    }

}

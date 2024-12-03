package com.example.api_weather.Controllers;

import com.example.api_weather.ApiResponse.ApiResponse;
import com.example.api_weather.DTOs.ClimaDTO;
import com.example.api_weather.Services.ClimaService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/clima")
public class ClimaController {

    private final ClimaService climaService;

    public ClimaController(ClimaService climaService) {
        this.climaService = climaService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ClimaDTO>>> getAll() {
        try {
            List<ClimaDTO> climas = climaService.findAll();
            return ResponseEntity.ok(new ApiResponse<>(climas, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(null, "Erro ao buscar climas"));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ClimaDTO>> getById(@PathVariable UUID id) {
        try {
            ClimaDTO clima = climaService.findById(id);
            return ResponseEntity.ok(new ApiResponse<>(clima, null));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(null, "Clima não encontrado"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(null, "Erro interno no servidor"));
        }
    }

    @GetMapping("/cidade/{id}")
    public ResponseEntity<ApiResponse<ClimaDTO>> getByCity(@PathVariable UUID id) {
        try {
            ClimaDTO clima = climaService.findByCityId(id);
            return ResponseEntity.ok(new ApiResponse<>(clima, null));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(null, "Clima não encontrado para a cidade especificada"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(null, "Erro interno no servidor"));
        }
    }
}
package com.example.api_weather.Controllers;

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
    public List<ClimaDTO> getAll() {
        return climaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClimaDTO> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(climaService.findById(id));
    }

    @GetMapping("/cidade/{id}")
    public ResponseEntity<ClimaDTO> getByCity(@PathVariable UUID id) {
        return ResponseEntity.ok(climaService.findByCityId(id));
    }

    //@PostMapping
    // public ResponseEntity<ClimaDTO> create(@RequestBody ClimaDTO climaDTO) {
    //    return ResponseEntity.status(HttpStatus.CREATED).body(climaService.create(climaDTO));
    //}

    //@PutMapping("/{id}")
    //public ResponseEntity<ClimaDTO> update(@PathVariable UUID id, @RequestBody ClimaDTO climaDTO) {
    //    return ResponseEntity.status(HttpStatus.OK).body(climaService.update(climaDTO));
    //}

    //@DeleteMapping("/{id}")
    //public ResponseEntity<Void> delete(@PathVariable UUID id) {
    //    climaService.delete(id);
    //    return ResponseEntity.noContent().build();
    //}
}
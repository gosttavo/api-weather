package com.example.api_weather.Controllers;

import com.example.api_weather.DTOs.RegiaoDTO;
import com.example.api_weather.Services.RegiaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/regiao")
public class RegiaoController {
    private final RegiaoService regiaoService;

    public RegiaoController(RegiaoService regiaoService) {
        this.regiaoService = regiaoService;
    }

    @GetMapping
    public List<RegiaoDTO> getAll() {
        return regiaoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegiaoDTO> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(regiaoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<RegiaoDTO> create(@RequestBody RegiaoDTO regiaoDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(regiaoService.create(regiaoDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RegiaoDTO> update(@PathVariable UUID id, @RequestBody RegiaoDTO regiaoDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(regiaoService.update(regiaoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        regiaoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
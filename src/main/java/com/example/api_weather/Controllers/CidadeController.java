package com.example.api_weather.Controllers;

import com.example.api_weather.DTOs.CidadeDTO;
import com.example.api_weather.Services.CidadeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cidade")
public class CidadeController {
    private CidadeService cidadeService;

    public CidadeController(CidadeService cidadeService) {
        this.cidadeService = cidadeService;
    }

    @GetMapping
    public List<CidadeDTO> getAll() {
        return cidadeService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CidadeDTO> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(cidadeService.findById(id));
    }

    @PostMapping
    public ResponseEntity<CidadeDTO> create(@RequestBody CidadeDTO cidadeDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cidadeService.create(cidadeDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CidadeDTO> update(@PathVariable UUID id, @RequestBody CidadeDTO cidadeDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(cidadeService.update(cidadeDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        cidadeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
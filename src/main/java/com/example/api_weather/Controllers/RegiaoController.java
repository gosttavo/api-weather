package com.example.api_weather.Controllers;

import com.example.api_weather.ApiResponse.ApiResponse;
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
    public ResponseEntity<ApiResponse<List<RegiaoDTO>>> getAll() {
        try {
            return ResponseEntity.ok(new ApiResponse<>(regiaoService.findAll(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<RegiaoDTO>> getById(@PathVariable UUID id) {
        try {
            RegiaoDTO regiao = regiaoService.findById(id);
            return ResponseEntity.ok(new ApiResponse<>(regiao, null));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(null, "Recurso não encontrado"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(null, "Erro interno no servidor"));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<RegiaoDTO>> create(@RequestBody RegiaoDTO regiaoDTO) {
        try {
            RegiaoDTO novaRegiao = regiaoService.create(regiaoDTO);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse<>(novaRegiao, "Região criada com sucesso"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>(null, "Erro ao criar a região: dados inválidos"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(null, "Erro interno ao criar a região"));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<RegiaoDTO>> update(@PathVariable UUID id, @RequestBody RegiaoDTO regiaoDTO) {
        try {
            RegiaoDTO regiaoAtualizada = regiaoService.update(regiaoDTO);
            return ResponseEntity.ok(new ApiResponse<>(regiaoAtualizada, "Região atualizada com sucesso"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(null, "Região não encontrada"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(null, "Erro interno ao atualizar a região"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable UUID id) {
        try {
            regiaoService.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new ApiResponse<>(null, "Região excluída com sucesso"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(null, "Região não encontrada"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(null, "Erro interno ao excluir a região"));
        }
    }
}
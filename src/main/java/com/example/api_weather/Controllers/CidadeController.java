package com.example.api_weather.Controllers;

import com.example.api_weather.ApiResponse.ApiResponse;
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
    public ResponseEntity<ApiResponse<List<CidadeDTO>>> getAll() {
        try {
            List<CidadeDTO> cidades = cidadeService.findAll();
            return ResponseEntity.ok(new ApiResponse<>(cidades, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(null, "Erro ao buscar as cidades"));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CidadeDTO>> getById(@PathVariable UUID id) {
        try {
            CidadeDTO cidade = cidadeService.findById(id);
            return ResponseEntity.ok(new ApiResponse<>(cidade, null));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(null, "Cidade não encontrada"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(null, "Erro interno no servidor"));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CidadeDTO>> create(@RequestBody CidadeDTO cidadeDTO) {
        try {
            CidadeDTO novaCidade = cidadeService.create(cidadeDTO);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse<>(novaCidade, "Cidade criada com sucesso"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>(null, "Erro ao criar a cidade: dados inválidos"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(null, "Erro interno ao criar a cidade"));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CidadeDTO>> update(@PathVariable UUID id, @RequestBody CidadeDTO cidadeDTO) {
        try {
            CidadeDTO cidadeAtualizada = cidadeService.update(cidadeDTO);
            return ResponseEntity.ok(new ApiResponse<>(cidadeAtualizada, "Cidade atualizada com sucesso"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(null, "Cidade não encontrada"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(null, "Erro interno ao atualizar a cidade"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable UUID id) {
        try {
            cidadeService.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new ApiResponse<>(null, "Cidade excluída com sucesso"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(null, "Cidade não encontrada"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(null, "Erro interno ao excluir a cidade"));
        }
    }
}
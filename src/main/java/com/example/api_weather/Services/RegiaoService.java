package com.example.api_weather.Services;

import com.example.api_weather.DTOs.RegiaoDTO;
import com.example.api_weather.Mappers.RegiaoMapper;
import com.example.api_weather.Models.Regiao;
import com.example.api_weather.Repositories.RegiaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RegiaoService {

    private final RegiaoRepository regiaoRepository;

    public RegiaoService(RegiaoRepository regiaoRepository) {
        this.regiaoRepository = regiaoRepository;
    }

    public List<RegiaoDTO> findAll() {
        return regiaoRepository.findAll()
                .stream()
                .map(RegiaoMapper::toDTO)
                .toList();
    }

    public RegiaoDTO findById(UUID id) {
        Regiao regiao = regiaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Região não encontrada"));
        return RegiaoMapper.toDTO(regiao);
    }

    public RegiaoDTO create(RegiaoDTO regiaoDTO) {
        Regiao regiao = RegiaoMapper.toEntity(regiaoDTO);
        Regiao regiaoCriada = regiaoRepository.save(regiao);
        return RegiaoMapper.toDTO(regiaoCriada);
    }

    public RegiaoDTO update(RegiaoDTO regiaoDTO) {
        Regiao regiaoExistente = regiaoRepository.findById(regiaoDTO.getId())
                .orElseThrow(() -> new RuntimeException("Região não encontrada"));

        regiaoExistente.setNome(regiaoDTO.getNome());
        Regiao regiaoAtualizada = regiaoRepository.save(regiaoExistente);
        return RegiaoMapper.toDTO(regiaoAtualizada);
    }


    public void delete(UUID id) {
        Regiao regiao = regiaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Região não encontrada"));
        regiaoRepository.delete(regiao);
    }
}
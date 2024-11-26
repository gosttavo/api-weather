package com.example.api_weather.Services;

import com.example.api_weather.DTOs.ClimaDTO;
import com.example.api_weather.Mappers.ClimaMapper;
import com.example.api_weather.Models.Cidade;
import com.example.api_weather.Models.Clima;
import com.example.api_weather.Repositories.CidadeRepository;
import com.example.api_weather.Repositories.ClimaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClimaService {

    private final ClimaRepository climaRepository;
    private final CidadeRepository cidadeRepository;

    public ClimaService(ClimaRepository climaRepository, CidadeRepository cidadeRepository) {
        this.climaRepository = climaRepository;
        this.cidadeRepository = cidadeRepository;
    }

    public List<ClimaDTO> findAll() {
        return climaRepository.findAll()
                .stream()
                .map(ClimaMapper::toDTO)
                .toList();
    }

    public ClimaDTO findById(UUID id) {
        Clima clima = climaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Clima não encontrado"));
        return ClimaMapper.toDTO(clima);
    }

    public ClimaDTO create(ClimaDTO climaDTO) {
        Cidade cidade = cidadeRepository.findById(climaDTO.getCidade().getId())
                .orElseThrow(() -> new RuntimeException("Cidade não encontrada"));

        Clima clima = ClimaMapper.toEntity(climaDTO);
        clima.setCidade(cidade);

        Clima climaCriado = climaRepository.save(clima);
        return ClimaMapper.toDTO(climaCriado);
    }

    public ClimaDTO update(ClimaDTO climaDTO) {
        Clima climaExistente = climaRepository.findById(climaDTO.getId())
                .orElseThrow(() -> new RuntimeException("Clima não encontrado"));

        climaExistente.setTemperatura(climaDTO.getTemperatura());
        climaExistente.setHumidade(climaDTO.getHumidade());
        climaExistente.setDateTime(climaDTO.getDateTime());

        if (climaDTO.getCidade().getId() != null) {
            Cidade cidade = cidadeRepository.findById(climaDTO.getCidade().getId())
                    .orElseThrow(() -> new RuntimeException("Cidade não encontrada"));
            climaExistente.setCidade(cidade);
        }

        Clima climaAtualizado = climaRepository.save(climaExistente);
        return ClimaMapper.toDTO(climaAtualizado);
    }

    public void delete(UUID id) {
        Clima clima = climaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Clima não encontrado"));
        climaRepository.delete(clima);
    }
}
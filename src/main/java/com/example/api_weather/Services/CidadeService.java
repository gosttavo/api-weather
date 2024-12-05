package com.example.api_weather.Services;

import com.example.api_weather.DTOs.CidadeDTO;
import com.example.api_weather.Mappers.CidadeMapper;
import com.example.api_weather.Mappers.ClimaMapper;
import com.example.api_weather.Models.Cidade;
import com.example.api_weather.Models.Clima;
import com.example.api_weather.Models.Regiao;
import com.example.api_weather.Repositories.CidadeRepository;
import com.example.api_weather.Repositories.ClimaRepository;
import com.example.api_weather.Repositories.RegiaoRepository;
import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CidadeService {
    private final CidadeRepository cidadeRepository;
    private final RegiaoRepository regiaoRepository;
    private final ClimaRepository climaRepository;
    private final ClimaService climaService;

    public CidadeService(CidadeRepository cidadeRepository, RegiaoRepository regiaoRepository, ClimaRepository climaRepository, ClimaService climaService) {
        this.cidadeRepository = cidadeRepository;
        this.regiaoRepository = regiaoRepository;
        this.climaRepository = climaRepository;
        this.climaService = climaService;
    }

    private boolean isDuplicateCity(String nome, String pais, UUID excludeId) {
        return cidadeRepository.findAll().stream()
                .anyMatch(cidade -> cidade.getNome().equalsIgnoreCase(nome) &&
                        cidade.getPais().equalsIgnoreCase(pais) &&
                        (excludeId == null || !cidade.getId().equals(excludeId)));
    }

    public List<CidadeDTO> findAll() {
        return cidadeRepository.findAll().stream()
                .map(CidadeMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CidadeDTO findById(UUID id) {
        Cidade cidade = cidadeRepository.findById(id).orElseThrow(() -> new RuntimeException("Cidade não encontrada"));
        return CidadeMapper.toDTO(cidade);
    }

    public CidadeDTO findByCidadeId(UUID id) {
        Cidade cidade = cidadeRepository.findById(id).orElseThrow(() -> new RuntimeException("Cidade não encontrada"));
        return CidadeMapper.toDTO(cidade);
    }

    public CidadeDTO create(CidadeDTO cidadeDTO) {
        Regiao regiao = regiaoRepository.findById(cidadeDTO.getRegiaoId())
                .orElseThrow(() -> new RuntimeException("Região não encontrada"));

        Cidade cidade = CidadeMapper.toEntity(cidadeDTO);
        cidade.setRegiao(regiao);

        JsonNode openWeatherCity = climaService.findByCity(cidade.getNome());

        cidade.setLatitude(openWeatherCity.get("city").get("coord").get("lat").asDouble());
        cidade.setLongitude(openWeatherCity.get("city").get("coord").get("lon").asDouble());

        Cidade cidadeCriada = cidadeRepository.save(cidade);

        JsonNode currentWeather = openWeatherCity.get("list").get(0);
        Clima clima = new Clima();
        clima.setTemperatura(currentWeather.get("main").get("temp").asDouble()); // Temperatura
        clima.setHumidade(currentWeather.get("main").get("humidity").asInt()); // Humidade
        clima.setDateTime(LocalDateTime.now());
        clima.setCidade(cidadeCriada);
        climaRepository.save(clima);

        return CidadeMapper.toDTO(cidadeCriada);
    }

    public CidadeDTO update(CidadeDTO cidadeDTO) {
        Cidade cidadeExistente = cidadeRepository.findById(cidadeDTO.getId())
                .orElseThrow(() -> new RuntimeException("Cidade não encontrada"));

        if (!cidadeExistente.getPais().equals(cidadeDTO.getPais())) {
            throw new RuntimeException("Não é permitido alterar o país da cidade.");
        }

        if (cidadeDTO.getRegiaoId() != null && !cidadeExistente.getRegiao().getId().equals(cidadeDTO.getRegiaoId())) {
            throw new RuntimeException("Não é permitido alterar a região da cidade.");
        }

        cidadeExistente.setNome(cidadeDTO.getNome());
        cidadeExistente.setPais(cidadeDTO.getPais());
        cidadeExistente.setLatitude(cidadeDTO.getLatitude());
        cidadeExistente.setLongitude(cidadeDTO.getLongitude());

        if (cidadeDTO.getRegiaoId() != null) {
            Regiao regiao = regiaoRepository.findById(cidadeDTO.getRegiaoId())
                    .orElseThrow(() -> new RuntimeException("Região não encontrada"));
            cidadeExistente.setRegiao(regiao);
        }

        Cidade cidadeAtualizada = cidadeRepository.save(cidadeExistente);

        return CidadeMapper.toDTO(cidadeAtualizada);
    }

    public void delete(UUID id) {
        cidadeRepository.deleteById(id);
    }
}

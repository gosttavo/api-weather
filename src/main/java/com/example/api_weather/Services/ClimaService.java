package com.example.api_weather.Services;

import com.example.api_weather.DTOs.ClimaDTO;
import com.example.api_weather.Mappers.ClimaMapper;
import com.example.api_weather.Models.Cidade;
import com.example.api_weather.Models.Clima;
import com.example.api_weather.Repositories.CidadeRepository;
import com.example.api_weather.Repositories.ClimaRepository;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

@Service
public class ClimaService {

    @Value("${openweather.api.url}")
    private String apiUrl;

    @Value("${openweather.api.key}")
    private String apiKey;

    private final ClimaRepository climaRepository;
    private final CidadeRepository cidadeRepository;
    private final RestClient restClient;

    public ClimaService(ClimaRepository climaRepository, CidadeRepository cidadeRepository, RestClient.Builder builder) {
        this.climaRepository = climaRepository;
        this.cidadeRepository = cidadeRepository;
        this.restClient = builder.build();
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

    public ClimaDTO findByCityId(UUID id) {
        Clima clima = climaRepository.findByCidadeId(id)
                .orElseThrow(() -> new RuntimeException("Clima não encontrado"));
        return ClimaMapper.toDTO(clima);
    }

    public JsonNode findByCity(String city) {
        String encodedCity = URLEncoder.encode(city, StandardCharsets.UTF_8);

        System.out.println("encodedCity" + encodedCity);

        String url = UriComponentsBuilder
                .fromHttpUrl(apiUrl)
                .queryParam("q", encodedCity)
                .queryParam("appid", apiKey)
                .queryParam("units", "metric")
                .queryParam("lang", "pt_br")
                .encode()
                .toUriString();

        System.out.println("url" + url);

        return restClient.get().uri(url).retrieve().body(JsonNode.class);
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
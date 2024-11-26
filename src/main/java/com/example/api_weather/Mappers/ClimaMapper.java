package com.example.api_weather.Mappers;

import com.example.api_weather.DTOs.ClimaDTO;
import com.example.api_weather.Models.Clima;
import org.springframework.stereotype.Component;

@Component
public class ClimaMapper {
    public static ClimaDTO toDTO(Clima clima) {
        ClimaDTO dto = new ClimaDTO();
        dto.setId(clima.getId());
        dto.setTemperatura(clima.getTemperatura());
        dto.setHumidade(clima.getHumidade());
        dto.setDateTime(clima.getDateTime());
        dto.setCidade(clima.getCidade());
        return dto;
    }

    public static Clima toEntity(ClimaDTO dto) {
        Clima clima = new Clima();
        clima.setId(dto.getId());
        clima.setTemperatura(dto.getTemperatura());
        clima.setHumidade(dto.getHumidade());
        clima.setDateTime(dto.getDateTime());
        clima.setCidade(dto.getCidade());
        return clima;
    }
}

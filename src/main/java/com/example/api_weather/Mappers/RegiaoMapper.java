package com.example.api_weather.Mappers;

import com.example.api_weather.DTOs.RegiaoDTO;
import com.example.api_weather.Models.Regiao;
import org.springframework.stereotype.Component;

@Component
public class RegiaoMapper {
    public static RegiaoDTO toDTO(Regiao regiao) {
        RegiaoDTO dto = new RegiaoDTO();
        dto.setId(regiao.getId());
        dto.setNome(regiao.getNome());
        dto.setCidades(regiao.getCidades());
        return dto;
    }

    public static Regiao toEntity(RegiaoDTO dto) {
        Regiao regiao = new Regiao();
        regiao.setId(dto.getId());
        regiao.setNome(dto.getNome());
        regiao.setCidades(dto.getCidades());
        return regiao;
    }
}

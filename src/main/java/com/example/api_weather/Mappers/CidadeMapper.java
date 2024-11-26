package com.example.api_weather.Mappers;

import com.example.api_weather.DTOs.CidadeDTO;
import com.example.api_weather.Models.Cidade;
import org.springframework.stereotype.Component;

@Component
public class CidadeMapper {
    public static CidadeDTO toDTO(Cidade cidade) {
        CidadeDTO dto = new CidadeDTO();
        dto.setId(cidade.getId());
        dto.setNome(cidade.getNome());
        dto.setPais(cidade.getPais());
        dto.setLatitude(cidade.getLatitude());
        dto.setLongitude(cidade.getLongitude());
        dto.setRegiaoId(cidade.getRegiao().getId());
        return dto;
    }

    public static Cidade toEntity(CidadeDTO dto) {
        Cidade cidade = new Cidade();
        cidade.setId(dto.getId());
        cidade.setNome(dto.getNome());
        cidade.setPais(dto.getPais());
        cidade.setLatitude(dto.getLatitude());
        cidade.setLongitude(dto.getLongitude());
        return cidade;
    }
}

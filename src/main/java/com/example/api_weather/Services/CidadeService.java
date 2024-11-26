package com.example.api_weather.Services;

import com.example.api_weather.DTOs.CidadeDTO;
import com.example.api_weather.Mappers.CidadeMapper;
import com.example.api_weather.Models.Cidade;
import com.example.api_weather.Models.Regiao;
import com.example.api_weather.Repositories.CidadeRepository;
import com.example.api_weather.Repositories.RegiaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CidadeService {
    private final CidadeRepository cidadeRepository;
    private final RegiaoRepository regiaoRepository;

    public CidadeService(CidadeRepository cidadeRepository, RegiaoRepository regiaoRepository) {
        this.cidadeRepository = cidadeRepository;
        this.regiaoRepository = regiaoRepository;
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

    public CidadeDTO create(CidadeDTO cidadeDTO) {
        Regiao regiao = regiaoRepository.findById(cidadeDTO.getRegiaoId())
                .orElseThrow(() -> new RuntimeException("Região não encontrada"));

        Cidade cidade = CidadeMapper.toEntity(cidadeDTO);
        cidade.setRegiao(regiao);

        Cidade cidadeCriada = cidadeRepository.save(cidade);

        return CidadeMapper.toDTO(cidadeCriada);
    }

    public CidadeDTO update(CidadeDTO cidadeDTO) {
        Cidade cidadeExistente = cidadeRepository.findById(cidadeDTO.getId())
                .orElseThrow(() -> new RuntimeException("Cidade não encontrada"));

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

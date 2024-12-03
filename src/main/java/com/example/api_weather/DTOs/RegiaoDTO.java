package com.example.api_weather.DTOs;

import com.example.api_weather.Models.Cidade;

import java.util.List;
import java.util.UUID;

public class RegiaoDTO {
    private UUID id;
    private String nome;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
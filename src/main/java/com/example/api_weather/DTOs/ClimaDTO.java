package com.example.api_weather.DTOs;

import com.example.api_weather.Models.Cidade;

import java.time.LocalDateTime;
import java.util.UUID;

public class ClimaDTO {
    private UUID id;
    private Double temperatura;
    private Integer humidade;
    private LocalDateTime dateTime;
    private Cidade cidade;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Double temperatura) {
        this.temperatura = temperatura;
    }

    public Integer getHumidade() {
        return humidade;
    }

    public void setHumidade(Integer humidade) {
        this.humidade = humidade;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }
}
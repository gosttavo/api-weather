package com.example.api_weather.DTOs;

import java.util.UUID;

public class CidadeDTO {
    private UUID id;
    private String nome;
    private String pais;
    private Double latitude;
    private Double longitude;
    private UUID regiaoId;

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

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public UUID getRegiaoId() {
        return regiaoId;
    }

    public void setRegiaoId(UUID regiaoId) {
        this.regiaoId = regiaoId;
    }
}

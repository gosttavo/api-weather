package com.example.api_weather.Models;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
public class Regiao {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @OneToMany(mappedBy = "regiao", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cidade> cidades;

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

    public List<Cidade> getCidades() {
        return cidades;
    }

    public void setCidades(List<Cidade> cidades) {
        this.cidades = cidades;
    }
}
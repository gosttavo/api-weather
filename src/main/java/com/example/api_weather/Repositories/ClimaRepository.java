package com.example.api_weather.Repositories;

import com.example.api_weather.Models.Cidade;
import com.example.api_weather.Models.Clima;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ClimaRepository extends JpaRepository<Clima, UUID> {
    Optional<Clima> findByCidadeId(UUID id);
}

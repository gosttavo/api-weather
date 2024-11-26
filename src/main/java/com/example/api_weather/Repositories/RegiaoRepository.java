package com.example.api_weather.Repositories;

import com.example.api_weather.Models.Regiao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RegiaoRepository extends JpaRepository<Regiao, UUID> {
}

package com.example.inventario.repository;

import com.example.inventario.model.Salida;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalidaRepository extends JpaRepository<Salida, Integer> {
}
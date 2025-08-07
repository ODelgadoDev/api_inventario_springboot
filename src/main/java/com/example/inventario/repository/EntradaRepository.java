package com.example.inventario.repository;

import com.example.inventario.model.Entrada;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface EntradaRepository extends JpaRepository<Entrada, Integer> {

    List<Entrada> findByProducto_IdProductoAndFechaEntradaBetween(Integer productoId, LocalDateTime desde, LocalDateTime hasta);

    List<Entrada> findByFechaEntradaBetween(LocalDateTime desde, LocalDateTime hasta);
}
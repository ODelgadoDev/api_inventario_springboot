package com.example.inventario.repository;

import com.example.inventario.model.Salida;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface SalidaRepository extends JpaRepository<Salida, Integer> {

    List<Salida> findByProducto_IdProductoAndFechaSalidaBetween(Integer productoId, LocalDateTime desde, LocalDateTime hasta);

    List<Salida> findByFechaSalidaBetween(LocalDateTime desde, LocalDateTime hasta);
}
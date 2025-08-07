package com.example.inventario.service;

import com.example.inventario.model.Entrada;
import com.example.inventario.model.Salida;
import com.example.inventario.repository.EntradaRepository;
import com.example.inventario.repository.SalidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class CostoPromedioService {

    @Autowired
    private EntradaRepository entradaRepository;

    @Autowired
    private SalidaRepository salidaRepository;

    public List<Map<String, Object>> generarReporteCostoPromedio(Integer productoId, LocalDateTime desde, LocalDateTime hasta) {
        List<Entrada> entradas = entradaRepository.findByProducto_IdProductoAndFechaEntradaBetween(productoId, desde, hasta);
        List<Salida> salidas = salidaRepository.findByProducto_IdProductoAndFechaSalidaBetween(productoId, desde, hasta);

        double totalCantidadEntradas = entradas.stream().mapToDouble(Entrada::getCantidad).sum();
        double totalCostoEntradas = entradas.stream().mapToDouble(e -> e.getCantidad() * e.getCostoUnitario()).sum();
        double costoPromedio = totalCantidadEntradas > 0 ? totalCostoEntradas / totalCantidadEntradas : 0;

        List<Map<String, Object>> resultado = new ArrayList<>();
        for (Salida salida : salidas) {
            Map<String, Object> fila = new HashMap<>();
            fila.put("nombre_producto", salida.getProducto().getNombre());
            fila.put("fecha_salida", salida.getFechaSalida());
            fila.put("cantidad_salida", salida.getCantidad());
            fila.put("costo_unitario", costoPromedio);
            fila.put("costo_total", costoPromedio * salida.getCantidad());
            resultado.add(fila);
        }

        return resultado;
    }
}
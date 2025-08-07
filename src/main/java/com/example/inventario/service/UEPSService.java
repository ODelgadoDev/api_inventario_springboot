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
public class UEPSService {

    @Autowired
    private EntradaRepository entradaRepository;

    @Autowired
    private SalidaRepository salidaRepository;

    public List<Map<String, Object>> generarReporteUEPS(Integer productoId, LocalDateTime desde, LocalDateTime hasta) {
        List<Entrada> entradas = (productoId != null)
                ? entradaRepository.findByProducto_IdProductoAndFechaEntradaBetween(productoId, desde, hasta)
                : entradaRepository.findByFechaEntradaBetween(desde, hasta);

        List<Salida> salidas = (productoId != null)
                ? salidaRepository.findByProducto_IdProductoAndFechaSalidaBetween(productoId, desde, hasta)
                : salidaRepository.findByFechaSalidaBetween(desde, hasta);

        // 🔁 Ordenar entradas de más reciente a más antigua (UEPS)
        entradas.sort(Comparator.comparing(Entrada::getFechaEntrada).reversed());
        salidas.sort(Comparator.comparing(Salida::getFechaSalida));

        LinkedList<Entrada> uepsEntradas = new LinkedList<>(entradas);
        List<Map<String, Object>> reporte = new ArrayList<>();

        for (Salida salida : salidas) {
            int cantidadRestante = salida.getCantidad();
            double costoTotalSalida = 0.0;

            while (cantidadRestante > 0 && !uepsEntradas.isEmpty()) {
                Entrada entrada = uepsEntradas.peek();

                int cantidadDisponible = entrada.getCantidad();
                int cantidadUsada = Math.min(cantidadDisponible, cantidadRestante);
                double costoUsado = cantidadUsada * entrada.getCostoUnitario();

                Map<String, Object> fila = new LinkedHashMap<>();
                fila.put("fecha_salida", salida.getFechaSalida());
                fila.put("producto_id", salida.getProducto().getIdProducto());
                fila.put("nombre_producto", salida.getProducto().getNombre());
                fila.put("cantidad_salida", cantidadUsada);
                fila.put("costo_unitario", entrada.getCostoUnitario());
                fila.put("costo_total", costoUsado);
                reporte.add(fila);

                costoTotalSalida += costoUsado;
                cantidadRestante -= cantidadUsada;

                if (cantidadUsada == cantidadDisponible) {
                    uepsEntradas.poll();
                } else {
                    entrada.setCantidad(cantidadDisponible - cantidadUsada);
                }
            }

            if (cantidadRestante > 0) {
                Map<String, Object> error = new HashMap<>();
                error.put("error", "No hay suficiente inventario para salida en fecha " + salida.getFechaSalida());
                reporte.add(error);
            }
        }

        return reporte;
    }
}
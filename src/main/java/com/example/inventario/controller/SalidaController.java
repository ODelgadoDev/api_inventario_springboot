package com.example.inventario.controller;

import com.example.inventario.dto.SalidaDto;
import com.example.inventario.model.Salida;
import com.example.inventario.model.Producto;
import com.example.inventario.repository.SalidaRepository;
import com.example.inventario.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salidas")
@CrossOrigin(origins = "*")
public class SalidaController {

    @Autowired
    private SalidaRepository salidaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    // GET → Listar todas las salidas
    @GetMapping
    public List<Salida> listarSalidas() {
        return salidaRepository.findAll();
    }

    // POST → Crear una nueva salida usando DTO
    @PostMapping
    public Salida crearSalida(@RequestBody SalidaDto salidaDto) {

        // Verificar si existe el producto
        Producto producto = productoRepository.findById(salidaDto.getProductoId())
                .orElseThrow(() -> new RuntimeException("Producto con ID " + salidaDto.getProductoId() + " no encontrado."));

        // Validar stock suficiente
        if (producto.getCantidad() < salidaDto.getCantidad()) {
            throw new RuntimeException("No hay suficiente stock del producto.");
        }

        // Actualizar stock
        producto.setCantidad(producto.getCantidad() - salidaDto.getCantidad());
        productoRepository.save(producto);

        // Crear y guardar la salida
        Salida salida = new Salida();
        salida.setProducto(producto);
        salida.setFechaSalida(salidaDto.getFechaSalida());
        salida.setCantidad(salidaDto.getCantidad());
        salida.setPrecioUnitario(salidaDto.getPrecioUnitario());
        salida.setMetodoInventario(salidaDto.getMetodoInventario());

        return salidaRepository.save(salida);
    }

    // GET → Buscar una salida por ID
    @GetMapping("/{id}")
    public Salida buscarSalidaPorId(@PathVariable Integer id) {
        return salidaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Salida no encontrada con ID " + id));
    }

    // PUT → Actualizar una salida
    @PutMapping("/{id}")
    public Salida actualizarSalida(@PathVariable Integer id, @RequestBody Salida salidaActualizada) {
        return salidaRepository.findById(id)
                .map(salida -> {
                    salida.setCantidad(salidaActualizada.getCantidad());
                    salida.setPrecioUnitario(salidaActualizada.getPrecioUnitario());
                    salida.setFechaSalida(salidaActualizada.getFechaSalida());
                    salida.setMetodoInventario(salidaActualizada.getMetodoInventario());
                    return salidaRepository.save(salida);
                })
                .orElseThrow(() -> new RuntimeException("Salida no encontrada con ID " + id));
    }

    // DELETE → Eliminar una salida
    @DeleteMapping("/{id}")
    public void eliminarSalida(@PathVariable Integer id) {
        salidaRepository.deleteById(id);
    }
}
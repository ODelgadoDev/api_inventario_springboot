package com.example.inventario.controller;

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

    // POST → Crear una nueva salida
    @PostMapping
    public Salida crearSalida(@RequestBody Salida salida) {

        // Verificamos si el producto existe
        Producto producto = productoRepository.findById(salida.getProducto().getIdProducto())
                .orElseThrow(() -> new RuntimeException("Producto con ID " + salida.getProducto().getIdProducto() + " no encontrado."));

        // Actualizamos el stock del producto (restamos la cantidad salida)
        if (producto.getCantidad() < salida.getCantidad()) {
            throw new RuntimeException("No hay suficiente stock del producto.");
        }

        producto.setCantidad(producto.getCantidad() - salida.getCantidad());
        productoRepository.save(producto);

        // Guardamos la salida
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
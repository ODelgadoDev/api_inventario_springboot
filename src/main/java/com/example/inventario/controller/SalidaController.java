package com.example.inventario.controller;

import com.example.inventario.model.Salida;
import com.example.inventario.repository.SalidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salidas")
@CrossOrigin(origins = "*")
public class SalidaController {

    @Autowired
    private SalidaRepository salidaRepository;

    @GetMapping
    public List<Salida> listarSalidas() {
        return salidaRepository.findAll();
    }

    @PostMapping
    public Salida crearSalida(@RequestBody Salida salida) {
        return salidaRepository.save(salida);
    }
}
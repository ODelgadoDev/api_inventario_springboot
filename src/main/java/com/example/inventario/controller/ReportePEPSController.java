package com.example.inventario.controller;

import com.example.inventario.service.PEPSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reportes/peps")
@CrossOrigin(origins = "*")
public class ReportePEPSController {

    @Autowired
    private PEPSService pepsService;

    @GetMapping
    public List<Map<String, Object>> obtenerReportePEPS(
            @RequestParam(required = false) Integer productoId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime desde,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime hasta
    ) {
        return pepsService.generarReportePEPS(productoId, desde, hasta);
    }
}
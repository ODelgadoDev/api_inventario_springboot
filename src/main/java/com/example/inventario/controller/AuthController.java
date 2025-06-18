package com.example.inventario.controller;

import com.example.inventario.dto.LoginRequest;
import com.example.inventario.model.Usuario;
import com.example.inventario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByNombreUsuario(request.getNombreUsuario());

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            // Verifica la contraseña cifrada
            if (passwordEncoder.matches(request.getContrasena(), usuario.getContrasenaHash())) {
                return ResponseEntity.ok("Login exitoso");
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
    }

    // Endpoint temporal para generar hashes
    @PostMapping("/test/hash")
    public String generarHash() {
        return passwordEncoder.encode("12345");
    }
}
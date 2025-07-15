package com.example.inventario.controller;

import com.example.inventario.dto.LoginRequest;
import com.example.inventario.model.Usuario;
import com.example.inventario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Endpoint de login. Recibe nombreUsuario y contraseña en texto plano.
     * Valida contra el hash almacenado.
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        System.out.println("Intento de login para usuario: " + request.getNombreUsuario());

        Optional<Usuario> usuarioOptional = usuarioRepository.findByNombreUsuario(request.getNombreUsuario());

        if (!usuarioOptional.isPresent()) {
            System.out.println("Usuario no encontrado.");
            Map<String, String> error = new HashMap<>();
            error.put("message", "Credenciales inválidas");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        }

        Usuario usuario = usuarioOptional.get();

        boolean valid = passwordEncoder.matches(request.getContrasena(), usuario.getContrasenaHash());
        if (valid) {
            System.out.println("Login exitoso para usuario: " + usuario.getNombreUsuario());
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Login exitoso");
            response.put("user", usuario.getNombreUsuario());
            response.put("role", usuario.getRol());
            return ResponseEntity.ok(response);
        } else {
            System.out.println("Contraseña incorrecta para usuario: " + usuario.getNombreUsuario());
            Map<String, String> error = new HashMap<>();
            error.put("message", "Credenciales inválidas");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        }
    }

    /**
     * Endpoint temporal para probar el hasheo de contraseñas.
     * Puedes enviar cualquier texto y devuelve su hash.
     */
    @PostMapping("/test/hash")
    public String generarHash(@RequestBody String plain) {
        return passwordEncoder.encode(plain);
    }
}
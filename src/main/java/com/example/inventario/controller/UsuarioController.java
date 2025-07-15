package com.example.inventario.controller;

import com.example.inventario.model.Usuario;
import com.example.inventario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Obtener todos los usuarios (solo para pruebas)
    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    // Crear usuario con contraseña en hash
    @PostMapping
    public ResponseEntity<?> crearUsuario(@RequestBody Usuario usuario) {
        // Validar que no exista ya un usuario con ese nombre
        if (usuarioRepository.findByNombreUsuario(usuario.getNombreUsuario()).isPresent()) {
            return ResponseEntity.badRequest().body("El nombre de usuario ya está en uso.");
        }

        // Validar que se haya proporcionado una contraseña
        if (usuario.getContrasenaHash() == null || usuario.getContrasenaHash().isBlank()) {
            return ResponseEntity.badRequest().body("La contraseña no puede estar vacía.");
        }

        // Hashear la contraseña antes de guardar
        usuario.setContrasenaHash(passwordEncoder.encode(usuario.getContrasenaHash()));

        Usuario guardado = usuarioRepository.save(usuario);
        return ResponseEntity.ok(guardado);
    }

    // Buscar usuario por nombreUsuario
    @GetMapping("/{nombreUsuario}")
    public Optional<Usuario> buscarPorNombreUsuario(@PathVariable String nombreUsuario) {
        return usuarioRepository.findByNombreUsuario(nombreUsuario);
    }
}
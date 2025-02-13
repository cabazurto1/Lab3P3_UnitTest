package com.viajes.agenciaViajes.controler;

import com.viajes.agenciaViajes.model.Usuario;
import com.viajes.agenciaViajes.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/usuarios")
public class UsuarioControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping
    public List<Usuario> obtenerTodos() {
        return usuarioServicio.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerPorId(@PathVariable int id) {
        Optional<Usuario> usuario = usuarioServicio.obtenerPorId(id);
        return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Usuario> guardar( @RequestBody Usuario usuario) {
        Usuario nuevoUsuario = usuarioServicio.guardar(usuario);
        return ResponseEntity.status(201).body(nuevoUsuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizar(@PathVariable int id, @RequestBody Usuario usuario) {
        Optional<Usuario> usuarioExistente = usuarioServicio.obtenerPorId(id);
        if (usuarioExistente.isPresent()) {
            usuario.setId(id); // Asegurar que el ID no cambie
            Usuario usuarioActualizado = usuarioServicio.guardar(usuario);
            return ResponseEntity.ok(usuarioActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
        usuarioServicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}

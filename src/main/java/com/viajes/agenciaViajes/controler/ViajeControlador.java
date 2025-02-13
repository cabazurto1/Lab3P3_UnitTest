package com.viajes.agenciaViajes.controler;

import com.viajes.agenciaViajes.model.Viaje;
import com.viajes.agenciaViajes.servicio.ViajeServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/viajes")
public class ViajeControlador {

    @Autowired
    private ViajeServicio viajeServicio;

    @GetMapping
    public List<Viaje> obtenerTodos() {
        return viajeServicio.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Viaje> obtenerPorId(@PathVariable int id) {
        Optional<Viaje> viaje = viajeServicio.obtenerPorId(id);
        return viaje.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Viaje> guardar( @RequestBody Viaje viaje) {
        Viaje nuevoViaje = viajeServicio.guardar(viaje);
        return ResponseEntity.status(201).body(nuevoViaje);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Viaje> actualizar(@PathVariable int id,  @RequestBody Viaje viaje) {
        Optional<Viaje> viajeExistente = viajeServicio.obtenerPorId(id);
        if (viajeExistente.isPresent()) {
            viaje.setId(id); // Asegurar que el ID no cambie
            Viaje viajeActualizado = viajeServicio.guardar(viaje);
            return ResponseEntity.ok(viajeActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
        viajeServicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}

package com.viajes.agenciaViajes.controler;


import com.viajes.agenciaViajes.model.Reserva;
import com.viajes.agenciaViajes.servicio.ReservaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/reservas")
public class ReservaControlador {

    @Autowired
    private ReservaServicio reservaServicio;

    @GetMapping
    public List<Reserva> obtenerTodas() {
        return reservaServicio.obtenerTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reserva> obtenerPorId(@PathVariable int id) {
        Optional<Reserva> reserva = reservaServicio.obtenerPorId(id);
        return reserva.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Reserva> guardar( @RequestBody Reserva reserva) {
        Reserva nuevaReserva = reservaServicio.guardar(reserva);
        return ResponseEntity.status(201).body(nuevaReserva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reserva> actualizar(@PathVariable int id,  @RequestBody Reserva reserva) {
        Optional<Reserva> reservaExistente = reservaServicio.obtenerPorId(id);
        if (reservaExistente.isPresent()) {
            reserva.setId(id); // Asegurar que el ID no cambie
            Reserva reservaActualizada = reservaServicio.guardar(reserva);
            return ResponseEntity.ok(reservaActualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
        reservaServicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}

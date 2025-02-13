package com.viajes.agenciaViajes.controler;

import com.viajes.agenciaViajes.model.Pago;
import com.viajes.agenciaViajes.servicio.PagoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/pagos")
public class PagoControlador {

    @Autowired
    private PagoServicio pagoServicio;

    @GetMapping
    public List<Pago> obtenerTodos() {
        return pagoServicio.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pago> obtenerPorId(@PathVariable int id) {
        Optional<Pago> pago = pagoServicio.obtenerPorId(id);
        return pago.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Pago> guardar( @RequestBody Pago pago) {
        Pago nuevoPago = pagoServicio.guardar(pago);
        return ResponseEntity.status(201).body(nuevoPago);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pago> actualizar(@PathVariable int id, @RequestBody Pago pago) {
        Optional<Pago> pagoExistente = pagoServicio.obtenerPorId(id);
        if (pagoExistente.isPresent()) {
            pago.setId(id); // Asegurar que el ID no cambie
            Pago pagoActualizado = pagoServicio.guardar(pago);
            return ResponseEntity.ok(pagoActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
        pagoServicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}

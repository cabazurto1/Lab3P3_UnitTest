package com.viajes.agenciaViajes.servicio;

import com.viajes.agenciaViajes.model.Pago;
import com.viajes.agenciaViajes.repositorio.PagoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PagoServicio {

    @Autowired
    private PagoRepositorio pagoRepositorio;

    // Obtener todos los pagos
    public List<Pago> obtenerTodos() {
        return pagoRepositorio.findAll();
    }

    // Obtener pago por ID
    public Optional<Pago> obtenerPorId(int id) {
        return pagoRepositorio.findById(id);
    }

    // Guardar un pago nuevo
    public Pago guardar(Pago pago) {
        return pagoRepositorio.save(pago);
    }

    // Actualizar un pago existente
    public Pago actualizarPago(int id, Pago pago) {
        if (pagoRepositorio.existsById(id)) {
            pago.setId(id);
            return pagoRepositorio.save(pago);
        }
        return null; // Manejar de forma adecuada en tu controlador
    }

    // Eliminar un pago por ID
    public void eliminar(int id) {
        pagoRepositorio.deleteById(id);
    }

    public List<Pago> buscarPorEstadoPago(String estadoPago) {
        return pagoRepositorio.findByEstadoPago(estadoPago);
    }

}

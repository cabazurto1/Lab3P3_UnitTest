package com.viajes.agenciaViajes.servicio;

import com.viajes.agenciaViajes.model.Reserva;
import com.viajes.agenciaViajes.repositorio.ReservaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservaServicio {

    @Autowired
    private ReservaRepositorio reservaRepositorio;

    // Obtener todas las reservas
    public List<Reserva> obtenerTodas() {
        return reservaRepositorio.findAll();
    }

    // Obtener reserva por ID
    public Optional<Reserva> obtenerPorId(int id) {
        return reservaRepositorio.findById(id);
    }

    // Guardar una nueva reserva
    public Reserva guardar(Reserva reserva) {
        return reservaRepositorio.save(reserva);
    }

    // Actualizar una reserva existente
    public Reserva actualizarReserva(int id, Reserva reserva) {
        if (reservaRepositorio.existsById(id)) {
            reserva.setId(id);
            return reservaRepositorio.save(reserva);
        }
        return null; // Manejar de forma adecuada en tu controlador
    }

    // Eliminar una reserva
    public void eliminar(int id) {
        reservaRepositorio.deleteById(id);
    }


    // Buscar reservas por ID de usuario
    public List<Reserva> obtenerPorUsuarioId(int usuarioId) {
        return reservaRepositorio.findByUsuarioId(usuarioId);
    }

    // Buscar reservas por ID de viaje
    public List<Reserva> obtenerPorViajeId(int viajeId) {
        return reservaRepositorio.findByViajeId(viajeId);
    }

}

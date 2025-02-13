package com.viajes.agenciaViajes.servicio;

import com.viajes.agenciaViajes.model.Viaje;
import com.viajes.agenciaViajes.repositorio.ViajeRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ViajeServicio {

    @Autowired
    private ViajeRepositorio viajeRepositorio;

    // Obtener todos los viajes
    public List<Viaje> obtenerTodos() {
        return viajeRepositorio.findAll();
    }

    // Obtener un viaje por su ID
    public Optional<Viaje> obtenerPorId(int id) {
        return viajeRepositorio.findById(id);
    }

    // Guardar un viaje nuevo
    public Viaje guardar(Viaje viaje) {
        return viajeRepositorio.save(viaje);
    }

    // Actualizar un viaje existente
    public Viaje actualizarViaje(int id, Viaje viaje) {
        if (viajeRepositorio.existsById(id)) {
            viaje.setId(id);
            return viajeRepositorio.save(viaje);
        }
        return null; // Manejar de forma adecuada en tu controlador
    }

    // Eliminar un viaje por ID
    public void eliminar(int id) {
        viajeRepositorio.deleteById(id);
    }

    public List<Viaje> buscarPorDestino(String destino) {
        return viajeRepositorio.findByDestino(destino);
    }

}

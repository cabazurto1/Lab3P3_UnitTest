package com.viajes.agenciaViajes.repositorio;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.viajes.agenciaViajes.model.Reserva;

public interface ReservaRepositorio extends JpaRepository<Reserva, Integer> {
    // Podrías añadir métodos para encontrar reservas por usuario, por viaje, etc.
    List<Reserva> findByUsuarioId(int usuarioId);
    List<Reserva> findByViajeId(int viajeId);
}

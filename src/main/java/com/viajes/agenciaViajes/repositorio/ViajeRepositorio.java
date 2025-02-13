package com.viajes.agenciaViajes.repositorio;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.viajes.agenciaViajes.model.Viaje;

public interface ViajeRepositorio extends JpaRepository<Viaje, Integer> {
    // Ejemplo de método de búsqueda personalizado:
    List<Viaje> findByDestino(String destino);
}

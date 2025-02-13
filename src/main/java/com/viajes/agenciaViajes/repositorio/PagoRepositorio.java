package com.viajes.agenciaViajes.repositorio;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.viajes.agenciaViajes.model.Pago;

public interface PagoRepositorio extends JpaRepository<Pago, Integer> {

     List<Pago> findByEstadoPago(String estadoPago);
}

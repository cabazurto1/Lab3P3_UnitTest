package com.viajes.agenciaViajes.servicio;

import com.viajes.agenciaViajes.model.Pago;
import com.viajes.agenciaViajes.model.Reserva;
import com.viajes.agenciaViajes.repositorio.PagoRepositorio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PagoServicioTest {

    @Mock
    private PagoRepositorio pagoRepositorio;

    @InjectMocks
    private PagoServicio pagoServicio;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testObtenerTodos() {
        Pago pago1 = new Pago(1, new Reserva(), "TARJETA", 100.0, LocalDateTime.now(), "APROBADO");
        Pago pago2 = new Pago(2, new Reserva(), "PAYPAL", 200.0, LocalDateTime.now(), "PENDIENTE");

        when(pagoRepositorio.findAll()).thenReturn(Arrays.asList(pago1, pago2));

        List<Pago> pagos = pagoServicio.obtenerTodos();
        assertEquals(2, pagos.size());
    }

    @Test
    void testObtenerPorId_Existe() {
        Pago pago = new Pago(1, new Reserva(), "TARJETA", 100.0, LocalDateTime.now(), "APROBADO");

        when(pagoRepositorio.findById(1)).thenReturn(Optional.of(pago));

        Optional<Pago> resultado = pagoServicio.obtenerPorId(1);
        assertTrue(resultado.isPresent());
        assertEquals("TARJETA", resultado.get().getMetodoPago());
    }

    @Test
    void testObtenerPorId_NoExiste() {
        when(pagoRepositorio.findById(1)).thenReturn(Optional.empty());

        Optional<Pago> resultado = pagoServicio.obtenerPorId(1);
        assertFalse(resultado.isPresent());
    }

    @Test
    void testGuardar() {
        Pago pago = new Pago(1, new Reserva(), "TARJETA", 100.0, LocalDateTime.now(), "APROBADO");

        when(pagoRepositorio.save(pago)).thenReturn(pago);

        Pago resultado = pagoServicio.guardar(pago);
        assertNotNull(resultado);
        assertEquals("TARJETA", resultado.getMetodoPago());
    }

    @Test
    void testBuscarPorEstadoPago() {
        Pago pago = new Pago(1, new Reserva(), "TARJETA", 100.0, LocalDateTime.now(), "APROBADO");

        when(pagoRepositorio.findByEstadoPago("APROBADO")).thenReturn(Arrays.asList(pago));

        List<Pago> resultado = pagoServicio.buscarPorEstadoPago("APROBADO");
        assertEquals(1, resultado.size());
        assertEquals("APROBADO", resultado.get(0).getEstadoPago());
    }
}

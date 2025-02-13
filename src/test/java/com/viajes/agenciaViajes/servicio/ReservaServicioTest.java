package com.viajes.agenciaViajes.servicio;

import com.viajes.agenciaViajes.model.Reserva;
import com.viajes.agenciaViajes.model.Usuario;
import com.viajes.agenciaViajes.model.Viaje;
import com.viajes.agenciaViajes.repositorio.ReservaRepositorio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReservaServicioTest {

    @Mock
    private ReservaRepositorio reservaRepositorio;

    @InjectMocks
    private ReservaServicio reservaServicio;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testObtenerTodas() {
        Reserva reserva1 = new Reserva();
        reserva1.setId(1);

        Reserva reserva2 = new Reserva();
        reserva2.setId(2);

        when(reservaRepositorio.findAll()).thenReturn(Arrays.asList(reserva1, reserva2));

        List<Reserva> reservas = reservaServicio.obtenerTodas();
        assertEquals(2, reservas.size());
    }

    @Test
    void testObtenerPorId_Existe() {
        Reserva reserva = new Reserva();
        reserva.setId(1);

        when(reservaRepositorio.findById(1)).thenReturn(Optional.of(reserva));

        Optional<Reserva> resultado = reservaServicio.obtenerPorId(1);
        assertTrue(resultado.isPresent());
        assertEquals(1, resultado.get().getId());
    }

    @Test
    void testObtenerPorId_NoExiste() {
        when(reservaRepositorio.findById(1)).thenReturn(Optional.empty());

        Optional<Reserva> resultado = reservaServicio.obtenerPorId(1);
        assertFalse(resultado.isPresent());
    }

    @Test
    void testGuardar() {
        Reserva reserva = new Reserva();
        reserva.setId(1);

        when(reservaRepositorio.save(reserva)).thenReturn(reserva);

        Reserva resultado = reservaServicio.guardar(reserva);
        assertNotNull(resultado);
        assertEquals(1, resultado.getId());
    }

    @Test
    void testActualizarReserva_Existe() {
        Reserva reserva = new Reserva();
        reserva.setId(1);

        when(reservaRepositorio.existsById(1)).thenReturn(true);
        when(reservaRepositorio.save(reserva)).thenReturn(reserva);

        Reserva actualizada = reservaServicio.actualizarReserva(1, reserva);
        assertNotNull(actualizada);
        assertEquals(1, actualizada.getId());
    }

    @Test
    void testActualizarReserva_NoExiste() {
        Reserva reserva = new Reserva();
        reserva.setId(1);

        when(reservaRepositorio.existsById(1)).thenReturn(false);

        Reserva actualizada = reservaServicio.actualizarReserva(1, reserva);
        assertNull(actualizada);
    }

    @Test
    void testEliminar() {
        doNothing().when(reservaRepositorio).deleteById(1);
        reservaServicio.eliminar(1);
        verify(reservaRepositorio, times(1)).deleteById(1);
    }

    @Test
    void testObtenerPorUsuarioId() {
        Usuario usuario = new Usuario();
        usuario.setId(100);

        Reserva reserva = new Reserva();
        reserva.setId(1);
        reserva.setUsuario(usuario);

        when(reservaRepositorio.findByUsuarioId(100)).thenReturn(Arrays.asList(reserva));

        List<Reserva> resultado = reservaServicio.obtenerPorUsuarioId(100);
        assertEquals(1, resultado.size());
        assertEquals(100, resultado.get(0).getUsuario().getId());
    }

    @Test
    void testObtenerPorViajeId() {
        Viaje viaje = new Viaje();
        viaje.setId(200);

        Reserva reserva = new Reserva();
        reserva.setId(1);
        reserva.setViaje(viaje);

        when(reservaRepositorio.findByViajeId(200)).thenReturn(Arrays.asList(reserva));

        List<Reserva> resultado = reservaServicio.obtenerPorViajeId(200);
        assertEquals(1, resultado.size());
        assertEquals(200, resultado.get(0).getViaje().getId());
    }
}

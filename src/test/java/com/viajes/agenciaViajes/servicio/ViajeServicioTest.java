package com.viajes.agenciaViajes.servicio;

import com.viajes.agenciaViajes.model.Viaje;
import com.viajes.agenciaViajes.repositorio.ViajeRepositorio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ViajeServicioTest {

    @Mock
    private ViajeRepositorio viajeRepositorio;

    @InjectMocks
    private ViajeServicio viajeServicio;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testObtenerTodos() {
        Viaje viaje1 = new Viaje(1, "París", LocalDate.now(), LocalDate.now().plusDays(7), 1200.0, 10);
        Viaje viaje2 = new Viaje(2, "Madrid", LocalDate.now(), LocalDate.now().plusDays(5), 900.0, 8);

        when(viajeRepositorio.findAll()).thenReturn(Arrays.asList(viaje1, viaje2));

        List<Viaje> viajes = viajeServicio.obtenerTodos();
        assertEquals(2, viajes.size());
    }

    @Test
    void testObtenerPorId_Existe() {
        Viaje viaje = new Viaje(1, "París", LocalDate.now(), LocalDate.now().plusDays(7), 1200.0, 10);

        when(viajeRepositorio.findById(1)).thenReturn(Optional.of(viaje));

        Optional<Viaje> resultado = viajeServicio.obtenerPorId(1);
        assertTrue(resultado.isPresent());
        assertEquals("París", resultado.get().getDestino());
    }

    @Test
    void testObtenerPorId_NoExiste() {
        when(viajeRepositorio.findById(1)).thenReturn(Optional.empty());

        Optional<Viaje> resultado = viajeServicio.obtenerPorId(1);
        assertFalse(resultado.isPresent());
    }

    @Test
    void testGuardar() {
        Viaje viaje = new Viaje(1, "París", LocalDate.now(), LocalDate.now().plusDays(7), 1200.0, 10);

        when(viajeRepositorio.save(viaje)).thenReturn(viaje);

        Viaje resultado = viajeServicio.guardar(viaje);
        assertNotNull(resultado);
        assertEquals("París", resultado.getDestino());
    }

    @Test
    void testBuscarPorDestino() {
        Viaje viaje = new Viaje(1, "París", LocalDate.now(), LocalDate.now().plusDays(7), 1200.0, 10);

        when(viajeRepositorio.findByDestino("París")).thenReturn(Arrays.asList(viaje));

        List<Viaje> resultado = viajeServicio.buscarPorDestino("París");
        assertEquals(1, resultado.size());
        assertEquals("París", resultado.get(0).getDestino());
    }
}

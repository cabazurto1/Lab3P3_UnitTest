package com.viajes.agenciaViajes.controler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.viajes.agenciaViajes.model.Reserva;
import com.viajes.agenciaViajes.model.Usuario;
import com.viajes.agenciaViajes.model.Viaje;
import com.viajes.agenciaViajes.servicio.ReservaServicio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ReservaControladorTest {

    private MockMvc mockMvc;

    @Mock
    private ReservaServicio reservaServicio;

    @InjectMocks
    private ReservaControlador reservaControlador;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(reservaControlador).build();
    }

    @Test
    void testObtenerTodas() throws Exception {
        when(reservaServicio.obtenerTodas()).thenReturn(Arrays.asList(new Reserva(), new Reserva()));

        mockMvc.perform(get("/reservas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void testGuardar() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setId(100);

        Viaje viaje = new Viaje();
        viaje.setId(200);

        Reserva reserva = new Reserva();
        reserva.setId(1);
        reserva.setUsuario(usuario);
        reserva.setViaje(viaje);

        when(reservaServicio.guardar(any(Reserva.class))).thenReturn(reserva);

        mockMvc.perform(post("/reservas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(reserva)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void testEliminar() throws Exception {
        doNothing().when(reservaServicio).eliminar(1);

        mockMvc.perform(delete("/reservas/1"))
                .andExpect(status().isNoContent());

        verify(reservaServicio, times(1)).eliminar(1);
    }
}

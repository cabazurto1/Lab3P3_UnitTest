package com.viajes.agenciaViajes.controler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.viajes.agenciaViajes.model.Viaje;
import com.viajes.agenciaViajes.servicio.ViajeServicio;
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

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ViajeControladorTest {

    private MockMvc mockMvc;

    @Mock
    private ViajeServicio viajeServicio;

    @InjectMocks
    private ViajeControlador viajeControlador;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(viajeControlador).build();
    }

    @Test
    void testObtenerTodos() throws Exception {
        when(viajeServicio.obtenerTodos()).thenReturn(Arrays.asList(new Viaje(), new Viaje()));

        mockMvc.perform(get("/viajes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void testGuardar() throws Exception {
        Viaje viaje = new Viaje(1, "París", LocalDate.now(), LocalDate.now().plusDays(7), 1200.0, 10);

        when(viajeServicio.guardar(any(Viaje.class))).thenReturn(viaje);

        mockMvc.perform(post("/viajes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(viaje)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.destino").value("París"));
    }

    @Test
    void testEliminar() throws Exception {
        doNothing().when(viajeServicio).eliminar(1);

        mockMvc.perform(delete("/viajes/1"))
                .andExpect(status().isNoContent());

        verify(viajeServicio, times(1)).eliminar(1);
    }

    @Test
    void testBuscarPorDestino() throws Exception {
        Viaje viaje = new Viaje(1, "París", LocalDate.now(), LocalDate.now().plusDays(7), 1200.0, 10);

        when(viajeServicio.buscarPorDestino("París")).thenReturn(Arrays.asList(viaje));

        mockMvc.perform(get("/viajes?destino=París"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].destino").value("París"));
    }
}

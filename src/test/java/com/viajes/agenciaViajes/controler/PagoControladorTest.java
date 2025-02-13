package com.viajes.agenciaViajes.controler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.viajes.agenciaViajes.model.Pago;
import com.viajes.agenciaViajes.model.Reserva;
import com.viajes.agenciaViajes.servicio.PagoServicio;
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

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PagoControladorTest {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Mock
    private PagoServicio pagoServicio;

    @InjectMocks
    private PagoControlador pagoControlador;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(pagoControlador).build();
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule()); // Soporte para LocalDateTime
    }

    @Test
    void testObtenerTodos() throws Exception {
        when(pagoServicio.obtenerTodos()).thenReturn(Arrays.asList(new Pago(), new Pago()));

        mockMvc.perform(get("/pagos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));
    }

    @Test
    void testGuardar() throws Exception {
        Pago pago = new Pago(1, new Reserva(), "TARJETA", 100.0, LocalDateTime.now(), "APROBADO");

        when(pagoServicio.guardar(any(Pago.class))).thenReturn(pago);

        mockMvc.perform(post("/pagos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pago))) // Usar el ObjectMapper corregido
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.estadoPago").value("APROBADO"));
    }

    @Test
    void testBuscarPorEstadoPago() throws Exception {
        Pago pago = new Pago(1, new Reserva(), "TARJETA", 100.0, LocalDateTime.now(), "APROBADO");

        when(pagoServicio.buscarPorEstadoPago("APROBADO")).thenReturn(Arrays.asList(pago));

        mockMvc.perform(get("/pagos?estadoPago=APROBADO"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1)) // Corrección aquí
                .andExpect(jsonPath("$[0].estadoPago").value("APROBADO"));
    }
}

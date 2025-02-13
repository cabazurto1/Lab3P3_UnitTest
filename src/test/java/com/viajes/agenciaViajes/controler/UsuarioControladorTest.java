package com.viajes.agenciaViajes.controler;
import com.viajes.agenciaViajes.servicio.UsuarioServicio;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UsuarioControladorTest {

    private MockMvc mockMvc;

    @Mock
    private UsuarioServicio usuarioServicio;

    @InjectMocks
    private UsuarioControlador usuarioControlador;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(usuarioControlador).build();
    }

    @Test
    void testObtenerTodos() throws Exception {
        mockMvc.perform(get("/usuarios"))
                .andExpect(status().isOk());
    }
}

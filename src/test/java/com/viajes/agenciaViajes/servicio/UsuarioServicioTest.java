package com.viajes.agenciaViajes.servicio;

import com.viajes.agenciaViajes.model.Usuario;
import com.viajes.agenciaViajes.repositorio.UsuarioRepositorio;
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

class UsuarioServicioTest {

    @Mock
    private UsuarioRepositorio usuarioRepositorio;

    @InjectMocks
    private UsuarioServicio usuarioServicio;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testObtenerTodos() {
        Usuario usuario1 = new Usuario();
        usuario1.setId(1);
        usuario1.setNombre("Juan");

        Usuario usuario2 = new Usuario();
        usuario2.setId(2);
        usuario2.setNombre("Maria");

        when(usuarioRepositorio.findAll()).thenReturn(Arrays.asList(usuario1, usuario2));

        List<Usuario> usuarios = usuarioServicio.obtenerTodos();
        assertEquals(2, usuarios.size());
    }

    @Test
    void testObtenerPorId() {
        Usuario usuario = new Usuario();
        usuario.setId(1);
        usuario.setNombre("Juan");

        when(usuarioRepositorio.findById(1)).thenReturn(Optional.of(usuario));

        Optional<Usuario> resultado = usuarioServicio.obtenerPorId(1);
        assertTrue(resultado.isPresent());
        assertEquals("Juan", resultado.get().getNombre());
    }

    @Test
    void testGuardar() {
        Usuario usuario = new Usuario();
        usuario.setId(1);
        usuario.setNombre("Juan");

        when(usuarioRepositorio.save(usuario)).thenReturn(usuario);

        Usuario resultado = usuarioServicio.guardar(usuario);
        assertNotNull(resultado);
        assertEquals("Juan", resultado.getNombre());
    }

    @Test
    void testActualizarUsuario() {
        Usuario usuario = new Usuario();
        usuario.setId(1);
        usuario.setNombre("Juan");

        when(usuarioRepositorio.existsById(1)).thenReturn(true);
        when(usuarioRepositorio.save(usuario)).thenReturn(usuario);

        Usuario actualizado = usuarioServicio.actualizarUsuario(1, usuario);
        assertNotNull(actualizado);
        assertEquals("Juan", actualizado.getNombre());
    }

    @Test
    void testActualizarUsuario_NoExistente() {
        Usuario usuario = new Usuario();
        usuario.setId(1);
        usuario.setNombre("Juan");

        when(usuarioRepositorio.existsById(1)).thenReturn(false);

        Usuario actualizado = usuarioServicio.actualizarUsuario(1, usuario);
        assertNull(actualizado);
    }

    @Test
    void testEliminar() {
        doNothing().when(usuarioRepositorio).deleteById(1);
        usuarioServicio.eliminar(1);
        verify(usuarioRepositorio, times(1)).deleteById(1);
    }

    @Test
    void testObtenerPorEmail() {
        Usuario usuario = new Usuario();
        usuario.setId(1);
        usuario.setEmail("juan@email.com");

        when(usuarioRepositorio.findByEmail("juan@email.com")).thenReturn(usuario);

        Usuario resultado = usuarioServicio.obtenerPorEmail("juan@email.com");
        assertNotNull(resultado);
        assertEquals("juan@email.com", resultado.getEmail());
    }
}

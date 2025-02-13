package com.viajes.agenciaViajes.servicio;

import com.viajes.agenciaViajes.model.Usuario;
import com.viajes.agenciaViajes.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServicio {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    // Obtener todos los usuarios
    public List<Usuario> obtenerTodos() {
        return usuarioRepositorio.findAll();
    }

    // Obtener un usuario por su ID
    public Optional<Usuario> obtenerPorId(int id) {
        return usuarioRepositorio.findById(id);
    }

    // Guardar un usuario nuevo
    public Usuario guardar(Usuario usuario) {
        return usuarioRepositorio.save(usuario);
    }

    // Actualizar un usuario existente
    public Usuario actualizarUsuario(int id, Usuario usuario) {
        if (usuarioRepositorio.existsById(id)) {
            usuario.setId(id);
            return usuarioRepositorio.save(usuario);
        }
        return null; // Manejar de forma adecuada en tu controlador
    }

    // Eliminar un usuario por ID
    public void eliminar(int id) {
        usuarioRepositorio.deleteById(id);
    }


    public Usuario obtenerPorEmail(String email) {
        return usuarioRepositorio.findByEmail(email);
    }

}

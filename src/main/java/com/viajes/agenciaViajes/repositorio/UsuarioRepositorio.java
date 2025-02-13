package com.viajes.agenciaViajes.repositorio;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.viajes.agenciaViajes.model.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer> {
    // Aquí puedes agregar métodos de consulta personalizados, por ejemplo:
    Usuario findByEmail(String email);
}

package com.sistema.elearning.repositorios;

import com.sistema.elearning.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    public Usuario findByUsername(String username);
}

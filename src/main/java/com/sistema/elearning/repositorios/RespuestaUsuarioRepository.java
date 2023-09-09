package com.sistema.elearning.repositorios;

import com.sistema.elearning.entidades.RespuestaUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RespuestaUsuarioRepository extends JpaRepository<RespuestaUsuario, Long> {
}


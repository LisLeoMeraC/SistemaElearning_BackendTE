package com.sistema.elearning.repositorios;

import com.sistema.elearning.entidades.Categoria;
import com.sistema.elearning.entidades.Usuario;
import com.sistema.elearning.entidades.UsuarioCategoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioCategoriaRepository extends JpaRepository<UsuarioCategoria,Long> {
    Optional<UsuarioCategoria> findByUsuarioAndCategoria(Usuario usuario, Categoria categoria);
    List<UsuarioCategoria> findByUsuario(Usuario usuario); // Para obtener por estudiante

}

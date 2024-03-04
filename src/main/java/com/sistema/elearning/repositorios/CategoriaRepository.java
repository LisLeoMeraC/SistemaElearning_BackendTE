package com.sistema.elearning.repositorios;

import com.sistema.elearning.entidades.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface CategoriaRepository extends JpaRepository<Categoria,Long> {
    Set<Categoria> findByUsuarioid(Long usuarioid);
}




package com.sistema.elearning.repositorios;

import com.sistema.elearning.entidades.Archivo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArchivoRepository extends JpaRepository<Archivo,Long> {
    List<Archivo> findByCategoriaId(Long categoriaId);
}

package com.sistema.elearning.repositorios;

import com.sistema.elearning.entidades.Examen;
import com.sistema.elearning.entidades.Pregunta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface PreguntaRepository  extends JpaRepository<Pregunta,Long> {
    Set<Pregunta> findByExamen(Examen examen);
}
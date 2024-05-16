package com.sistema.elearning.repositorios;

import com.sistema.elearning.entidades.Examen;
import com.sistema.elearning.entidades.Pregunta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface PreguntaRepository  extends JpaRepository<Pregunta,Long> {
    Set<Pregunta> findByExamen(Examen examen);

    @Query("SELECT COUNT(p) FROM Pregunta p WHERE p.examen.examenId = :examenId")
    int countByExamenId(@Param("examenId") Long examenId);
}
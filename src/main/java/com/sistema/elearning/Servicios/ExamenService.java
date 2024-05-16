package com.sistema.elearning.Servicios;

import com.sistema.elearning.entidades.Archivo;
import com.sistema.elearning.entidades.Categoria;
import com.sistema.elearning.entidades.Examen;
import com.sistema.elearning.entidades.Usuario;

import java.util.List;
import java.util.Set;

public interface ExamenService {
    Examen agregarExamen(Examen examen);

    Examen actualizarExamen(Examen examen);

    Set<Examen> obtenerExamenes();

    Examen obtenerExamen(Long examenId);

    void eliminarExamen(Long examenId);

    List<Examen> listarExamenesDeUnaCategoria(Categoria categoria);



    List<Examen> obtenerExamenesActivos();

    List<Examen> obtenerExamenesActivosDeUnaCategoria(Categoria categoria);


    //filtrar examenes por docente
    List<Examen> obtenerExamenesPorUsuario(String username);


    List<Examen> obtenerExamenesPorCategoria(Long categoriaId);

}

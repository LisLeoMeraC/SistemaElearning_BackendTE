package com.sistema.elearning.Servicios.impl;

import com.sistema.elearning.Servicios.CategoriaService;
import com.sistema.elearning.Servicios.ExamenService;
import com.sistema.elearning.Servicios.UsuarioService;
import com.sistema.elearning.entidades.*;
import com.sistema.elearning.repositorios.ExamenRepository;
import com.sistema.elearning.repositorios.PreguntaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class ExamenServiceImpl implements ExamenService {

    @Autowired
    private ExamenRepository examenRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private PreguntaRepository preguntaRepository;

    @Override
    public Examen agregarExamen(Examen examen) {
        return examenRepository.save(examen);
    }

    @Override
    public Examen actualizarExamen(Examen examen) {
        return examenRepository.save(examen);
    }

    @Override
    public Set<Examen> obtenerExamenes() {
        return new LinkedHashSet<>(examenRepository.findAll());
    }

    @Override
    public Examen obtenerExamen(Long examenId) {
        return examenRepository.findById(examenId).get();
    }

    @Override
    public void eliminarExamen(Long examenId) {
        Examen examen = examenRepository.findById(examenId)
                .orElseThrow(() -> new RuntimeException("No se encontró el examen con el ID: " + examenId));
        Set<Pregunta> preguntas = examen.getPreguntas();
        if (preguntas != null) {
            for (Pregunta pregunta : preguntas) {
                pregunta.setExamen(null);
                preguntaRepository.delete(pregunta);
            }
        }
        examenRepository.delete(examen);
    }

    @Override
    public List<Examen> listarExamenesDeUnaCategoria(Categoria categoria) {
        return this.examenRepository.findByCategoria(categoria);
    }

    @Override
    public List<Examen> obtenerExamenesActivos() {
        return examenRepository.findByActivo(true);
    }

    @Override
    public List<Examen> obtenerExamenesActivosDeUnaCategoria(Categoria categoria) {
        return examenRepository.findByCategoriaAndActivo(categoria,true);
    }



    //filtrar examenes por docente
    @Override
    public List<Examen> obtenerExamenesPorUsuario(String username) {
        Usuario usuario = usuarioService.obtenerUsuario(username); // Asegúrate de que este método exista y funcione correctamente
        Set<Categoria> categorias = categoriaService.obtenerCategoriasPorUsuario(username); // Asume que este método ya existe y funciona
        List<Examen> examenes = new ArrayList<>();
        for (Categoria categoria : categorias) {
            List<Examen> examenesPorCategoria = examenRepository.findByCategoria(categoria);
            examenes.addAll(examenesPorCategoria);
        }
        return examenes;
    }

    @Override
    public List<Examen> obtenerExamenesPorCategoria(Long categoriaId) {
        return examenRepository.findByCategoriaId(categoriaId);
    }


}

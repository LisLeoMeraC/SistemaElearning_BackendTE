package com.sistema.elearning.Servicios.impl;

import com.sistema.elearning.Excepciones.ResourceNotFoundException;
import com.sistema.elearning.Servicios.CategoriaService;
import com.sistema.elearning.Servicios.UsuarioService;
import com.sistema.elearning.entidades.Categoria;
import com.sistema.elearning.repositorios.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public Categoria agregarCategoria(Categoria categoria, String username){

        Long userId = usuarioService.obtenerIdUsuarioPorUsername(username);
        categoria.setUsuarioid(userId);
        return categoriaRepository.save(categoria);
    }

    @Override
    public Categoria actualizarCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @Override
    public Set<Categoria> obtenerCategorias() {
        return new LinkedHashSet<>(categoriaRepository.findAll());
    }



    @Override
    public Categoria obtenerCategoria(Long categoriaId) {
        return categoriaRepository.findById(categoriaId).get();
    }

    @Override
    public void eliminarCategoria(Long categoriaId) {
        Categoria categoria = new Categoria();
        categoria.setId(categoriaId);
        categoriaRepository.delete(categoria);
    }
    //probando
    @Override
    public Set<Categoria> obtenerCategoriasPorUsuario(String username) {
        Long userId = usuarioService.obtenerIdUsuarioPorUsername(username);
        return categoriaRepository.findByUsuarioid(userId);
    }

    @Override
    public Categoria obtenerCategoriaPorCodigo(String codigoAcceso) {
        Optional<Categoria> categoriaOptional = categoriaRepository.findByUniqueCode(codigoAcceso);
        if (categoriaOptional.isPresent()) {
            return categoriaOptional.get();
        } else {
            throw new RuntimeException("Categoria no encontrada con código de acceso: " + codigoAcceso);
        }
    }

}

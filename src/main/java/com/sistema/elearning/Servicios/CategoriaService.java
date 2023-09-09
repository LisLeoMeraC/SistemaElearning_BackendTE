package com.sistema.elearning.Servicios;

import com.sistema.elearning.entidades.Categoria;

import java.util.Set;

public interface CategoriaService {
    Categoria agregarCategoria(Categoria categoria);

    Categoria actualizarCategoria(Categoria categoria);

    Set<Categoria> obtenerCategorias();

    Categoria obtenerCategoria(Long categoriaId);

    void eliminarCategoria(Long categoriaId);
}

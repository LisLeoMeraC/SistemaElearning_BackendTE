package com.sistema.elearning.Servicios;

import com.sistema.elearning.entidades.Categoria;

import java.util.Set;

public interface CategoriaService {
    Categoria agregarCategoria(Categoria categoria, String username);

    Categoria actualizarCategoria(Categoria categoria);

    Set<Categoria> obtenerCategorias();



    Categoria obtenerCategoria(Long categoriaId);



    void eliminarCategoria(Long categoriaId);

    Set<Categoria> obtenerCategoriasPorUsuario(String username);


    Categoria obtenerCategoriaPorCodigo(String codigoAcceso);
}

package com.sistema.elearning.Servicios;

import com.sistema.elearning.entidades.Categoria;
import com.sistema.elearning.entidades.Usuario;
import com.sistema.elearning.entidades.UsuarioCategoria;

import java.util.List;
import java.util.Set;

public interface UsuarioCategoriaService {
    UsuarioCategoria agregarAsignacion(Long categoriaId, Long usuarioId);
    List<UsuarioCategoria> obtenerAsignacionesPorUsuario(Usuario usuario);

    Set<Categoria> obtenerCategoriasPorUsuario(Long usuarioId);
}

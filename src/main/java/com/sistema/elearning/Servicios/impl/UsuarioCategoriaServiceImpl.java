package com.sistema.elearning.Servicios.impl;

import com.sistema.elearning.Servicios.UsuarioCategoriaService;
import com.sistema.elearning.entidades.Categoria;
import com.sistema.elearning.entidades.Usuario;
import com.sistema.elearning.entidades.UsuarioCategoria;
import com.sistema.elearning.repositorios.UsuarioCategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UsuarioCategoriaServiceImpl implements UsuarioCategoriaService {

    @Autowired
    private UsuarioCategoriaRepository usuarioCategoriaRepository;
    @Override
    public UsuarioCategoria agregarAsignacion(Long categoriaId, Long usuarioId) {
        UsuarioCategoria usuarioCategoria = new UsuarioCategoria();
        Usuario usuario = new Usuario();
        usuario.setId(usuarioId);
        Categoria categoria = new Categoria();
        categoria.setId(categoriaId);
        usuarioCategoria.setUsuario(usuario);
        usuarioCategoria.setCategoria(categoria);
        return usuarioCategoriaRepository.save(usuarioCategoria);
    }

    @Override
    public List<UsuarioCategoria> obtenerAsignacionesPorUsuario(Usuario usuario) {
        return usuarioCategoriaRepository.findByUsuario(usuario);
    }

    @Override
    public Set<Categoria> obtenerCategoriasPorUsuario(Long usuarioId) {
        Usuario usuario = new Usuario();
        usuario.setId(usuarioId);
        List<UsuarioCategoria> asignaciones = usuarioCategoriaRepository.findByUsuario(usuario);
        return asignaciones.stream()
                .map(UsuarioCategoria::getCategoria)
                .collect(Collectors.toSet());
    }
}

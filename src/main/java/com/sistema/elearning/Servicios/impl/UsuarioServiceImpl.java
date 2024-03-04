package com.sistema.elearning.Servicios.impl;

import com.sistema.elearning.Excepciones.UsuarioFoundException;
import com.sistema.elearning.Servicios.UsuarioService;
import com.sistema.elearning.entidades.Usuario;
import com.sistema.elearning.entidades.UsuarioRol;
import com.sistema.elearning.repositorios.RolRepository;
import com.sistema.elearning.repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Override
    public Usuario registrarUsuario(Usuario usuario, Set<UsuarioRol> usuarioRols) throws Exception {
        Usuario usuarioLocal= usuarioRepository.findByUsername(usuario.getUsername());
        if(usuarioLocal!=null){
            System.out.println("El usuario ya existe");
            throw new UsuarioFoundException("El usuario ya esta presente");
        }
        else {
            for (UsuarioRol usuarioRol:usuarioRols){
                rolRepository.save(usuarioRol.getRol());
            }
            usuario.getUsuarioRoles().addAll(usuarioRols);
            usuarioLocal=usuarioRepository.save(usuario);
        }
        return usuarioLocal;
    }

    @Override
    public Usuario obtenerUsuario(String username) {
        return usuarioRepository.findByUsername(username);
    }
    @Override
    public void eliminarUsuario(Long usuarioID) {
        usuarioRepository.deleteById(usuarioID);

    }

    @Override
    public Long obtenerIdUsuarioPorUsername(String username) {
        Usuario usuario = obtenerUsuario(username);
        if (usuario != null) {
            return usuario.getId(); // Suponiendo que hay un método getId() en la entidad Usuario
        }
        return null; // Si el usuario no se encuentra
    }

}

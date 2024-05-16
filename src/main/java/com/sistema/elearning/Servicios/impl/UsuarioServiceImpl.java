package com.sistema.elearning.Servicios.impl;

import com.sistema.elearning.Excepciones.UsuarioFoundException;
import com.sistema.elearning.Servicios.UsuarioService;
import com.sistema.elearning.entidades.Usuario;
import com.sistema.elearning.entidades.UsuarioRol;
import com.sistema.elearning.repositorios.RolRepository;
import com.sistema.elearning.repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;
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

    @Override
    public Usuario obtenerUsuarioActual(Principal principal) {
        if (SecurityContextHolder.getContext().getAuthentication() == null ||
                !SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            throw new AuthenticationCredentialsNotFoundException("Usuario no autenticado");
        }

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Usuario usuario = usuarioRepository.findByUsername(userDetails.getUsername());
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        return usuario;
    }

    @Override
    public Usuario actualizarUsuario(Long id, Usuario usuarioActualizado) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
        if (optionalUsuario.isPresent()) {
            Usuario usuarioExistente = optionalUsuario.get();
            // Actualiza los campos relevantes del usuario con los nuevos valores
            usuarioExistente.setNombre(usuarioActualizado.getNombre());
            usuarioExistente.setApellido(usuarioActualizado.getApellido());
            usuarioExistente.setEmail(usuarioActualizado.getEmail());
            usuarioExistente.setTelefono(usuarioActualizado.getTelefono());
            usuarioExistente.setIdentidad(usuarioActualizado.getIdentidad());
            // Guarda el usuario actualizado en la base de datos
            return usuarioRepository.save(usuarioExistente);
        } else {
            // Si el usuario no existe, retorna null o lanza una excepción según sea necesario
            return null;
        }
    }


}


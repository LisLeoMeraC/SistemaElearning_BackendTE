package com.sistema.elearning.Servicios.impl;

import com.sistema.elearning.entidades.Usuario;
import com.sistema.elearning.repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    //Metodo para buscar un usuario usando su username

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario= this.usuarioRepository.findByUsername(username);
        if(usuario==null){
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        return usuario;
    }
}

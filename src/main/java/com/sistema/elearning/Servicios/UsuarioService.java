package com.sistema.elearning.Servicios;

import com.sistema.elearning.entidades.Usuario;
import com.sistema.elearning.entidades.UsuarioRol;

import java.util.Set;

public interface UsuarioService {

    public Usuario registrarUsuario(Usuario usuario, Set<UsuarioRol> usuarioRols) throws Exception;

    public  Usuario obtenerUsuario(String username);

    public void eliminarUsuario(Long usuarioID);

    //Obtener el id del usuario
    public Long obtenerIdUsuarioPorUsername(String username);



































}

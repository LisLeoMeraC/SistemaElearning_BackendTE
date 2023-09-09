package com.sistema.elearning.Servicios.impl;

import com.sistema.elearning.Servicios.RespuestaUsuarioService;
import com.sistema.elearning.entidades.RespuestaUsuario;
import com.sistema.elearning.repositorios.RespuestaUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RespuestaUsuarioServiceImpl implements RespuestaUsuarioService {
    @Autowired
    private RespuestaUsuarioRepository respuestaUsuarioRepository;

    @Override
    public RespuestaUsuario save(RespuestaUsuario respuestaUsuario) {
        return respuestaUsuarioRepository.save(respuestaUsuario);
    }
}

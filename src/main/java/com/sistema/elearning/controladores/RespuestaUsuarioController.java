package com.sistema.elearning.controladores;

import com.sistema.elearning.Servicios.RespuestaUsuarioService;
import com.sistema.elearning.entidades.RespuestaUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/respuestasUsuario")
@CrossOrigin("*")
public class RespuestaUsuarioController {
    @Autowired
    private RespuestaUsuarioService respuestaUsuarioService;

    @PostMapping("/")
    public ResponseEntity<RespuestaUsuario> registrarRespuesta(@RequestBody RespuestaUsuario respuestaUsuario) {
        RespuestaUsuario nuevaRespuesta = respuestaUsuarioService.save(respuestaUsuario);
        return new ResponseEntity<>(nuevaRespuesta, HttpStatus.CREATED);
    }

}

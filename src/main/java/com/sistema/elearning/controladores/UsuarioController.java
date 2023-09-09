package com.sistema.elearning.controladores;

import com.sistema.elearning.Servicios.UsuarioService;
import com.sistema.elearning.entidades.Rol;
import com.sistema.elearning.entidades.Usuario;
import com.sistema.elearning.entidades.UsuarioRol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin("*")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;
    @PostMapping("/")
    public Usuario guardarUsuario(@RequestBody Usuario usuario) throws Exception {
        usuario.setPerfil("default.png");

        Set<UsuarioRol> usuario_roles= new HashSet<>();
        Rol rol= new Rol();

        if ("docente".equals(usuario.getRolFormulario())) {
            rol.setRolID(1L); // Asumo que 1L corresponde al ID del rol "Admin"
            rol.setRolNombre("Admin");
        } else { // Por defecto, se asume que es "estudiante"
            rol.setRolID(2L);
            rol.setRolNombre("Normal");
        }

        UsuarioRol usuarioRol= new UsuarioRol();
        usuarioRol.setUsuario(usuario);
        usuarioRol.setRol(rol);

        //Para agregar en la tabla usuario_rol cuando el usuario se registra
        usuario_roles.add(usuarioRol);
        return usuarioService.registrarUsuario(usuario, usuario_roles);
    }
  /*  @PostMapping("/teacher")
    public Usuario guardarUsuarioDocente(@RequestBody Usuario usuario)throws Exception{

        usuario.setPerfil("default.png");

        Set<UsuarioRol>usuario_roles= new HashSet<>();
        Rol rol= new Rol();
        rol.setRolID(2L);
        rol.setRolNombre("Admin");

        UsuarioRol usuarioRol= new UsuarioRol();
        usuarioRol.setUsuario(usuario);
        usuarioRol.setRol(rol);

        //Para agregar en la tabla usuario_rol cuando el usuario se registra
        usuario_roles.add(usuarioRol);
        return usuarioService.registrarUsuario(usuario,usuario_roles);

    }*/
    @GetMapping("/{username}")
    public Usuario obtenerUsuario(@PathVariable("username") String username){
        return usuarioService.obtenerUsuario(username);
    }

    @DeleteMapping("/{usuarioID}")
    public void eliminarUsuario(@PathVariable("usuarioID")long usuarioID){
        usuarioService.eliminarUsuario(usuarioID);

    }


}

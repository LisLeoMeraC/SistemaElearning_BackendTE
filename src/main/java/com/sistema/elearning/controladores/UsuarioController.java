package com.sistema.elearning.controladores;

import com.sistema.elearning.Servicios.UsuarioService;
import com.sistema.elearning.entidades.Rol;
import com.sistema.elearning.entidades.Usuario;
import com.sistema.elearning.entidades.UsuarioRol;
import com.sistema.elearning.entidades.usuarioActualizado;
import com.sistema.elearning.repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin("*")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

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

    @GetMapping("/{username}")
    public Usuario obtenerUsuario(@PathVariable("username") String username){
        return usuarioService.obtenerUsuario(username);
    }

    @DeleteMapping("/{usuarioID}")
    public void eliminarUsuario(@PathVariable("usuarioID")long usuarioID){
        usuarioService.eliminarUsuario(usuarioID);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarUsuario(@PathVariable("id") Long id, @RequestBody usuarioActualizado usuarioActualizadoDTO) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);

        if (optionalUsuario.isPresent()) {
            Usuario usuarioExistente = optionalUsuario.get();

            // Actualiza los campos permitidos del usuario con los nuevos valores
            if (usuarioActualizadoDTO.getNombre() != null) {
                usuarioExistente.setNombre(usuarioActualizadoDTO.getNombre());
            }
            if (usuarioActualizadoDTO.getApellido() != null) {
                usuarioExistente.setApellido(usuarioActualizadoDTO.getApellido());
            }
            if (usuarioActualizadoDTO.getEmail() != null) {
                usuarioExistente.setEmail(usuarioActualizadoDTO.getEmail());
            }
            if (usuarioActualizadoDTO.getTelefono() != null) {
                usuarioExistente.setTelefono(usuarioActualizadoDTO.getTelefono());
            }

            // Guarda el usuario actualizado en la base de datos
            usuarioExistente = usuarioRepository.save(usuarioExistente);

            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }



}

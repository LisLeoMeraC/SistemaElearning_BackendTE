package com.sistema.elearning.controladores;
import com.sistema.elearning.Servicios.ExamenService;
import com.sistema.elearning.Servicios.UsuarioService;
import com.sistema.elearning.Servicios.impl.UsuarioServiceImpl;
import com.sistema.elearning.entidades.Categoria;
import com.sistema.elearning.entidades.Examen;
import com.sistema.elearning.entidades.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;

@RestController
@RequestMapping("/examen")
@CrossOrigin("*")
public class ExamenController {

    @Autowired
    private ExamenService examenService;
    @Autowired
    private UsuarioService usuarioService;
    @PostMapping("/")
    public ResponseEntity<Examen> guardarExamen(@RequestBody Examen examen){
        //  Usuario usuarioLogueado= getUsuarioActual();
        //  examen.setUsuario(usuarioLogueado);
        return ResponseEntity.ok(examenService.agregarExamen(examen));
    }



    //obtener el id del usuario que se loguea
/*    private Usuario getUsuarioActual() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String nombreUsuario = auth.getName();
        return usuarioService.obtenerUsuario(nombreUsuario);
    }
*/


    @PutMapping("/")
    public ResponseEntity<Examen> actualizarExamen(@RequestBody Examen examen){
        return ResponseEntity.ok(examenService.actualizarExamen(examen));
    }

    @GetMapping("/")
    public ResponseEntity<?> listarExamenes(){
        return ResponseEntity.ok(examenService.obtenerExamenes());
    }

    @GetMapping("/{examenId}")
    public Examen listarExamen(@PathVariable("examenId") Long examenId){
        return examenService.obtenerExamen(examenId);
    }

    @DeleteMapping("/{examenId}")
    public void eliminarExamen(@PathVariable("examenId") Long examenId){
        examenService.eliminarExamen(examenId);
    }

    @GetMapping("/categoria/{categoriaId}")
    public List<Examen> listarExamenesDeUnaCategoria(@PathVariable("categoriaId") Long categoriaId){
        Categoria categoria = new Categoria();
        categoria.setId(categoriaId);
        return examenService.listarExamenesDeUnaCategoria(categoria);
    }

    @GetMapping("/activo")
    public List<Examen> listarExamenesActivos(){
        return examenService.obtenerExamenesActivos();
    }

    @GetMapping("/categoria/activo/{categoriaId}")
    public List<Examen> listarExamenesActivosDeUnaCategoria(@PathVariable("categoriaId") Long categoriaId){
        Categoria categoria = new Categoria();
        categoria.setId(categoriaId);
        return examenService.obtenerExamenesActivosDeUnaCategoria(categoria);
    }


    //Listarexamenes por usuario
    @GetMapping("/usuario")
    public ResponseEntity<?> listarExamenesPorUsuario(Principal principal) {
        String username = principal.getName();
        List<Examen> examenes = examenService.obtenerExamenesPorUsuario(username);
        return ResponseEntity.ok(examenes);
    }
}
package com.sistema.elearning.controladores;

import com.sistema.elearning.Servicios.CategoriaService;
import com.sistema.elearning.Servicios.UsuarioCategoriaService;
import com.sistema.elearning.Servicios.UsuarioService;
import com.sistema.elearning.entidades.Categoria;
import com.sistema.elearning.entidades.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/categoria")
@CrossOrigin("*")
public class CategoriaController {
    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private UsuarioCategoriaService usuarioCategoriaService;
    @PostMapping("/")
    public ResponseEntity<Categoria> guardarCategoria(@RequestBody Categoria categoria, Principal principal){

        String username= principal.getName();

        Categoria categoriaGuardada = categoriaService.agregarCategoria(categoria, username);
        return ResponseEntity.ok(categoriaGuardada);
    }

    @GetMapping("/{categoriaId}")
    public Categoria listarCategoriaPorId(@PathVariable("categoriaId") Long categoriaId){
        return categoriaService.obtenerCategoria(categoriaId);
    }

    @GetMapping("/")
    public ResponseEntity<?> listarCategorias(Principal principal){
        String username= principal.getName();
        return ResponseEntity.ok(categoriaService.obtenerCategoriasPorUsuario(username));
    }

    //listar todas las categorias
    @GetMapping("/todas")
    public ResponseEntity<?> listartodasCategorias() {
        return ResponseEntity.ok(categoriaService.obtenerCategorias());
    }

    @PutMapping("/")
    public Categoria actualizarCategoria(@RequestBody Categoria categoria){
        return categoriaService.actualizarCategoria(categoria);
    }

    @DeleteMapping("/{categoriaId}")
    public void eliminarCategoria(@PathVariable("categoriaId") Long categoriaId){
        categoriaService.eliminarCategoria(categoriaId);
    }

    @PostMapping("/verificar")
    public ResponseEntity<?> verificarCodigoAcceso(@RequestParam("codigoAcceso") String codigoAcceso, Principal principal) {
        Categoria categoria = categoriaService.obtenerCategoriaPorCodigo(codigoAcceso);
        Usuario usuario = usuarioService.obtenerUsuarioActual(principal); // Asumo que tienes método para esto

        // Asignar la categoría al usuario:
        usuarioCategoriaService.agregarAsignacion(categoria.getId(), usuario.getId());

        return ResponseEntity.ok("Categoria asignada correctamente");
    }

    @GetMapping("/usuario-logueado")
    public ResponseEntity<?> listarCategoriasUsuarioLogueado(Principal principal) {
        String username = principal.getName();
        Long usuarioId = usuarioService.obtenerIdUsuarioPorUsername(username); // Asume que existe este método en UsuarioService
        return ResponseEntity.ok(usuarioCategoriaService.obtenerCategoriasPorUsuario(usuarioId));
    }
}

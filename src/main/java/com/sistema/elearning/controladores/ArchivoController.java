package com.sistema.elearning.controladores;

import com.sistema.elearning.Servicios.ArchivoService;
import com.sistema.elearning.entidades.Archivo;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/archivos")
@CrossOrigin("*")
public class ArchivoController {
    private ArchivoService archivoService;

    public ArchivoController(ArchivoService archivoService) {
        this.archivoService = archivoService;
    }

    @PostMapping("/subir")
    public ResponseEntity<String>subirArchivo(@RequestParam("file")MultipartFile file, @RequestParam("categoriaId") Long categoriaId){
        try {
            archivoService.subirArchivo(file, categoriaId);
            return new ResponseEntity<>("Archivo subido exitosamente!", HttpStatus.OK);
        } catch (Exception e) { // Mejorar el manejo de excepciones
            return new ResponseEntity<>("Error al subir archivo: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<List<Archivo>> obtenerArchivosPorCategoria(@PathVariable Long categoriaId) {
        List<Archivo> archivos = archivoService.obtenerArchivosPorCategoria(categoriaId);
        return new ResponseEntity<>(archivos, HttpStatus.OK);
    }

    @GetMapping("{id}/descargar")
    public ResponseEntity<byte[]> descargarArchivo(@PathVariable Long id) {
        byte[] contenido = archivoService.descargarArchivo(id);
        Archivo archivo = archivoService.obtenerArchivoPorId(id);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(archivo.getTipoArchivo()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + archivo.getNombreArchivo() + "\"")
                .body(contenido);
    }
}

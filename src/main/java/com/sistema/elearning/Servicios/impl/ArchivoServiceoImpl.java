package com.sistema.elearning.Servicios.impl;

import com.sistema.elearning.Servicios.ArchivoService;
import com.sistema.elearning.Servicios.CategoriaService;
import com.sistema.elearning.Servicios.UsuarioService;
import com.sistema.elearning.entidades.Archivo;
import com.sistema.elearning.entidades.Categoria;
import com.sistema.elearning.entidades.Examen;
import com.sistema.elearning.entidades.Usuario;
import com.sistema.elearning.repositorios.ArchivoRepository;
import com.sistema.elearning.repositorios.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ArchivoServiceoImpl implements ArchivoService {

    @Autowired
    private ArchivoRepository archivoRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private CategoriaService categoriaService;

    @Override
    public void subirArchivo(MultipartFile file, Long categoriaId) {
        try {
            Categoria categoria = categoriaRepository.findById(categoriaId).orElseThrow(() -> new RuntimeException("Categoria no encontrada"));

            Archivo archivo = new Archivo();
            archivo.setNombreArchivo(file.getOriginalFilename());
            archivo.setTipoArchivo(file.getContentType());
            archivo.setTamanoArchivo(file.getSize());
            archivo.setContenidoArchivo(file.getBytes());
            archivo.setCategoria(categoria);

            archivoRepository.save(archivo);
        } catch (IOException e) {
            throw new RuntimeException("Error al subir el archivo");
        }
    }

    @Override
    public byte[] descargarArchivo(Long id) {
        Archivo archivo = archivoRepository.findById(id).orElseThrow(() -> new RuntimeException("Archivo no encontrado"));
        return archivo.getContenidoArchivo();
    }

    @Override
    public void eliminarArchivo(Long id) {
        archivoRepository.deleteById(id);
    }

    @Override
    public List<Archivo> obtenerArchivosPorCategoria(Long categoriaId) {
        return archivoRepository.findByCategoriaId(categoriaId);
    }

    @Override
    public Archivo obtenerArchivoPorId(Long id) {
        return archivoRepository.findById(id).orElseThrow(() -> new RuntimeException("Archivo no encontrado"));
    }

    @Override
    public List<Archivo> listarArchivos(String username) {
        Usuario usuario = usuarioService.obtenerUsuario(username);
        Set<Categoria> categorias = categoriaService.obtenerCategoriasPorUsuario(username);

        List<Archivo> archivos = new ArrayList<>();
        for (Categoria categoria : categorias) {
            List<Archivo> archivosPorCategoria = archivoRepository.findByCategoria(categoria);
            archivos.addAll(archivosPorCategoria);
        }
        return archivos;
       // return archivoRepository.findAll();
    }
}

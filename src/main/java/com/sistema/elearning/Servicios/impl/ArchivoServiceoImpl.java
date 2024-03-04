package com.sistema.elearning.Servicios.impl;

import com.sistema.elearning.Servicios.ArchivoService;
import com.sistema.elearning.entidades.Archivo;
import com.sistema.elearning.entidades.Categoria;
import com.sistema.elearning.repositorios.ArchivoRepository;
import com.sistema.elearning.repositorios.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ArchivoServiceoImpl implements ArchivoService {

    @Autowired
    private ArchivoRepository archivoRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;

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
}

package com.sistema.elearning.Servicios;

import com.sistema.elearning.entidades.Archivo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ArchivoService {
    void subirArchivos(MultipartFile file,Long categoriaId);
    byte[] descargarArchivo(Long id);

    void eliminarArchivo(Long id);

    List<Archivo> obtenerArchivosPorCategoria(Long categoriaId);
}

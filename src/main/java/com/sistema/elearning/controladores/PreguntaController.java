package com.sistema.elearning.controladores;

import com.sistema.elearning.Servicios.ExamenService;
import com.sistema.elearning.Servicios.PreguntaService;
import com.sistema.elearning.entidades.Examen;
import com.sistema.elearning.entidades.Pregunta;
import com.sistema.elearning.repositorios.PreguntaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/pregunta")
@CrossOrigin("*")
public class PreguntaController {

    @Autowired
    private PreguntaService preguntaService;

    @Autowired
    private PreguntaRepository preguntaRepository;


    @Autowired
    private ExamenService examenService;

    @PostMapping("/")
    public ResponseEntity<?> guardarPregunta(@RequestBody Pregunta pregunta) {
        Long examenId = pregunta.getExamen().getExamenId();
        int numeroDePreguntasActuales = preguntaRepository.countByExamenId(examenId);

        Examen examen = examenService.obtenerExamen(examenId);
        int numeroMaximoDePreguntas = examen.getNumeroDePreguntas();

        if (numeroDePreguntasActuales >= numeroMaximoDePreguntas) {
            return ResponseEntity.badRequest().body("Has alcanzado el número máximo de preguntas para este examen.");
        }
        System.out.println("Número de preguntas actuales: " + numeroDePreguntasActuales);
        System.out.println("Número de preguntas fijas: " + numeroMaximoDePreguntas);
        return ResponseEntity.ok(preguntaService.agregarPregunta(pregunta));

    }

    @PutMapping("/")
    public ResponseEntity<Pregunta> actualizarPregunta(@RequestBody Pregunta pregunta) {
        return ResponseEntity.ok(preguntaService.actualizarPregunta(pregunta));
    }

    @GetMapping("/examen/{examenId}")
    public ResponseEntity<?> listarPreguntasDelExamen(@PathVariable("examenId") Long examenId) {
        int numeroDePreguntasActuales = preguntaRepository.countByExamenId(examenId);
        System.out.println("Número de preguntas actuales: " + numeroDePreguntasActuales);
        Examen examen = examenService.obtenerExamen(examenId);
        Set<Pregunta> preguntas = examen.getPreguntas();

        List examenes = new ArrayList(preguntas);
        if (examenes.size() > examen.getNumeroDePreguntas()) {
            examenes = examenes.subList(0, examen.getNumeroDePreguntas() + 1);
        }

        Collections.shuffle(examenes);
        return ResponseEntity.ok(examenes);
    }

    @GetMapping("/{preguntaId}")
    public Pregunta listarPreguntaPorId(@PathVariable("preguntaId") Long preguntaId) {
        return preguntaService.obtenerPregunta(preguntaId);
    }

    @DeleteMapping("/{preguntaId}")
    public void eliminarPregunta(@PathVariable("preguntaId") Long preguntaId) {
        preguntaService.eliminarPregunta(preguntaId);
    }

    @GetMapping("/examen/todos/{examenId}")
    public ResponseEntity<?> listarPreguntaDelExamenComoAdministrador(@PathVariable("examenId") Long examenId) {
        Examen examen = new Examen();
        examen.setExamenId(examenId);
        Set<Pregunta> preguntas = preguntaService.obtenerPreguntasDelExamen(examen);
        return ResponseEntity.ok(preguntas);
    }

    @PostMapping("/evaluar-examen")
    public ResponseEntity<?> evaluarExamen(@RequestBody List<Pregunta> preguntas) {
        double puntosMaximos = 0;
        Integer respuestasCorrectas = 0;
        Integer intentos = 0;

        // Validar si la lista de preguntas está vacía
        if (preguntas.isEmpty()) {
            return ResponseEntity.badRequest().body("La lista de preguntas está vacía");
        }

        // Obtener el examen asociado a la primera pregunta
        Examen examen = preguntas.get(0).getExamen();
        double puntosPorPregunta = Double.parseDouble(examen.getPuntosMaximos()) / preguntas.size();

        List<Pregunta> preguntasIncorrectas = new ArrayList<>();

        for (Pregunta p : preguntas) {
            Pregunta pregunta = preguntaService.listarPregunta(p.getPreguntaId());

            // Validar si la pregunta existe
            if (pregunta == null) {
                return ResponseEntity.badRequest().body("La pregunta con ID " + p.getPreguntaId() + " no existe");
            }

            // Evaluar si la respuesta es correcta
            if (pregunta.getRespuesta().equals(p.getRespuestaDada())) {
                respuestasCorrectas++;
                puntosMaximos += puntosPorPregunta;
            } else {
                preguntasIncorrectas.add(pregunta);
            }

            // Contar los intentos solo si se ha dado una respuesta
            if (p.getRespuestaDada() != null) {
                intentos++;
            }
        }

        Map<String, Object> respuestas = new HashMap<>();
        respuestas.put("puntosMaximos", puntosMaximos);
        respuestas.put("respuestasCorrectas", respuestasCorrectas);
        respuestas.put("intentos", intentos);
        respuestas.put("preguntasIncorrectas", preguntasIncorrectas);

        return ResponseEntity.ok(respuestas);
    }


}

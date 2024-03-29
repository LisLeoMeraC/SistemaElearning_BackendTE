package com.sistema.elearning.entidades;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "preguntas")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class Pregunta {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long preguntaId;

        @Column(length = 5000)
        private String contenido;

        private String imagen;
        private String opcion1;
        private String opcion2;
        private String opcion3;
        private String opcion4;
        @Transient
        private String respuestaDada;

        private String respuesta;

        @Column(length = 5000)
        private  String justificacion;

        private String url;

        @ManyToOne(fetch = FetchType.EAGER)
        private Examen examen;

    public Pregunta() {
    }

    public Pregunta(Long preguntaId, String contenido, String imagen, String opcion1, String opcion2, String opcion3, String opcion4, String respuestaDada, String respuesta, String justificacion, String url, Examen examen) {
        this.preguntaId = preguntaId;
        this.contenido = contenido;
        this.imagen = imagen;
        this.opcion1 = opcion1;
        this.opcion2 = opcion2;
        this.opcion3 = opcion3;
        this.opcion4 = opcion4;
        this.respuestaDada = respuestaDada;
        this.respuesta = respuesta;
        //cambiar justificacion para tener observacion
        this.justificacion = justificacion;
        this.url = url;
        this.examen = examen;
    }

    public Long getPreguntaId() {
        return preguntaId;
    }

    public void setPreguntaId(Long preguntaId) {
        this.preguntaId = preguntaId;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getOpcion1() {
        return opcion1;
    }

    public void setOpcion1(String opcion1) {
        this.opcion1 = opcion1;
    }

    public String getOpcion2() {
        return opcion2;
    }

    public void setOpcion2(String opcion2) {
        this.opcion2 = opcion2;
    }

    public String getOpcion3() {
        return opcion3;
    }

    public void setOpcion3(String opcion3) {
        this.opcion3 = opcion3;
    }

    public String getOpcion4() {
        return opcion4;
    }

    public void setOpcion4(String opcion4) {
        this.opcion4 = opcion4;
    }

    public String getRespuestaDada() {
        return respuestaDada;
    }

    public void setRespuestaDada(String respuestaDada) {
        this.respuestaDada = respuestaDada;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Examen getExamen() {
        return examen;
    }

    public void setExamen(Examen examen) {
        this.examen = examen;
    }
}

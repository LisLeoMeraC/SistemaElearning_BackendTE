package com.sistema.elearning.entidades;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "respuesta_usuario")
public class RespuestaUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pregunta_id", nullable = false)
    private Pregunta pregunta;

    @Column(name = "respuesta_dada", nullable = false)
    private String respuestaDada;

    @Column(name = "fecha_respuesta", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRespuesta = new Date();

    // Constructor, getters, setters y otros métodos necesarios


    public RespuestaUsuario() {
    }

    public RespuestaUsuario(Long id, Usuario usuario, Pregunta pregunta, String respuestaDada, Date fechaRespuesta) {
        this.id = id;
        this.usuario = usuario;
        this.pregunta = pregunta;
        this.respuestaDada = respuestaDada;
        this.fechaRespuesta = fechaRespuesta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Pregunta getPregunta() {
        return pregunta;
    }

    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }

    public String getRespuestaDada() {
        return respuestaDada;
    }

    public void setRespuestaDada(String respuestaDada) {
        this.respuestaDada = respuestaDada;
    }

    public Date getFechaRespuesta() {
        return fechaRespuesta;
    }

    public void setFechaRespuesta(Date fechaRespuesta) {
        this.fechaRespuesta = fechaRespuesta;
    }
}
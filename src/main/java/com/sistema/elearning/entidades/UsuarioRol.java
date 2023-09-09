package com.sistema.elearning.entidades;

import javax.persistence.*;

@Entity
@Table(name="usuarioRol")
public class UsuarioRol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usuarioRolID;

    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario usuario;

    @ManyToOne
    private Rol rol;

    public Long getUsuarioRolID() {
        return usuarioRolID;
    }

    public void setUsuarioRolID(Long usuarioRolID) {
        this.usuarioRolID = usuarioRolID;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public UsuarioRol(){

    }
}

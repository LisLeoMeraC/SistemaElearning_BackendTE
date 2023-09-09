package com.sistema.elearning.entidades;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="roles")
public class Rol {
    @Id
    private Long rolID;
    private String rolNombre;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "rol")
    private Set<UsuarioRol>usuarioRols= new HashSet<>();



    public Long getRolID() {
        return rolID;
    }

    public void setRolID(Long rolID) {
        this.rolID = rolID;
    }

    public String getRolNombre() {
        return rolNombre;
    }

    public void setRolNombre(String rolNombre) {
        this.rolNombre = rolNombre;
    }

    public Set<UsuarioRol> getUsuarioRols() {
        return usuarioRols;
    }

    public void setUsuarioRols(Set<UsuarioRol> usuarioRols) {
        this.usuarioRols = usuarioRols;
    }

    public Rol(){

    }
}

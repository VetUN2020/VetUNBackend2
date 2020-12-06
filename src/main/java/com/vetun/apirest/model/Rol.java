package com.vetun.apirest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="rol")
public class Rol {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id_rol")
    private int idRol;

    @Column(name = "nombre_rol" )
    private String nombreRol;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "rol")
    private List<Usuario> usuarios;

    public Rol() {
        //Necesary method
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    @Override
    public String toString() {
        return "Rol{" +
                "idRol=" + idRol +
                ", nombreRol='" + nombreRol + '}';
    }
}

package com.vetun.apirest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="veterinaria")
public class Veterinaria {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id_veterinaria")
    private int idVeterinaria;

    @Column(name="nombre_veterinaria")
    private String nombreVeterinaria;

    @Column(name="direccion_veterinaria")
    private String direccionVeterinaria;

    @Column(name="telefono_veterinaria")
    private long telefonoVeterinaria;

    @Column(name="tipo_veterinaria")
    private String tipoVeterinaria;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "idVeterinaria")
    private List<Medico> medicos;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "idVeterinaria")
    private List<ComentarioVeterinaria> comentarios;

    public Veterinaria() {
        //Necesary method
    }

    public int getIdVeterinaria() {
        return idVeterinaria;
    }

    public void setIdVeterinaria(int idVeterinaria) {
        this.idVeterinaria = idVeterinaria;
    }

    public String getNombreVeterinaria() {
        return nombreVeterinaria;
    }

    public void setNombreVeterinaria(String nombreVeterinaria) {
        this.nombreVeterinaria = nombreVeterinaria;
    }

    public String getDireccionVeterinaria() {
        return direccionVeterinaria;
    }

    public void setDireccionVeterinaria(String direccionVeterinaria) {
        this.direccionVeterinaria = direccionVeterinaria;
    }

    public long getTelefonoVeterinaria() {
        return telefonoVeterinaria;
    }

    public void setTelefonoVeterinaria(long telefonoVeterinaria) {
        this.telefonoVeterinaria = telefonoVeterinaria;
    }


    public String getTipoVeterinaria() {
        return tipoVeterinaria;
    }

    public void setTipoVeterinaria(String tipoVeterinaria) {
        this.tipoVeterinaria = tipoVeterinaria;
    }

    public List<Medico> getMedicos() {
        return medicos;
    }

    public void setMedicos(List<Medico> medicos) {
        this.medicos = medicos;
    }

    public List<ComentarioVeterinaria> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<ComentarioVeterinaria> comentarios) {
        this.comentarios = comentarios;
    }

    @Override
    public String toString() {
        return "Veterinaria{" +
                "idVeterinaria=" + idVeterinaria +
                ", nombreVeterinaria=" + nombreVeterinaria +
                ", direccionVeterinaria=" + direccionVeterinaria +
                ", telefonoVeterinaria=" + telefonoVeterinaria +
                ", tipoVeterinaria=" + tipoVeterinaria +
                ", medicos=" + medicos +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Veterinaria that = (Veterinaria) o;
        return idVeterinaria == that.idVeterinaria;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idVeterinaria);
    }
}

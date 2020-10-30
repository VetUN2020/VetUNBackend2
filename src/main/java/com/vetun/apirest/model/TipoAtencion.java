package com.vetun.apirest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="tipo_atencion")
public class TipoAtencion {

    @Id
    @Column(name="id_atencion")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int idAtencion;

    @Column(name = "descripcion_atencion")
    private String descripcionAtencion;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "idAtencion")
    private List<Costo> costo;


    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "idAtencion")
    private List<Cita> citas;

    public TipoAtencion() {
    }

    public TipoAtencion(int idAtencion, String descripcionAtencion) {
        this.idAtencion = idAtencion;
        this.descripcionAtencion = descripcionAtencion;
    }

    public int getIdAtencion() {
        return idAtencion;
    }

    public void setIdAtencion(int idAtencion) {
        this.idAtencion = idAtencion;
    }

    public String getDescripcionAtencion() {
        return descripcionAtencion;
    }

    public void setDescripcionAtencion(String descripcionAtencion) {
        this.descripcionAtencion = descripcionAtencion;
    }

    public List<Costo> getCosto() {
        return costo;
    }

    public void setCosto(List<Costo> costo) {
        this.costo = costo;
    }

    public List<Cita> getCitas() {
        return citas;
    }

    public void setCitas(List<Cita> citas) {
        this.citas = citas;
    }
}

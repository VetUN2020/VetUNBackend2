package com.vetun.apirest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="mascota")
public class Mascota {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id_mascota")
    private int idMascota;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="id_dueno")
    private Dueno idDueno;

    @Column(name="nombre_mascota")
    private String nombreMascota;

    @Column(name="especie")
    private String especie;

    @Column(name="raza")
    private String raza;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "idMascota")
    private List<Vacunacion> vacunas;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "idMascota")
    private List<Cita> citas;

    public Mascota(){
        //Necesary method
    }

    public Mascota(int idMascota, Dueno idDueno, String nombreMascota, String especie, String raza, List<Vacunacion> vacunas, List<Cita> citas) {
        this.idMascota = idMascota;
        this.idDueno = idDueno;
        this.nombreMascota = nombreMascota;
        this.especie = especie;
        this.raza = raza;
        this.vacunas = vacunas;
        this.citas = citas;
    }

    public Dueno getIdDueno() {
        return idDueno;
    }

    public void setIdDueno(Dueno idDueno) {
        this.idDueno = idDueno;
    }

    public int getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(int idMascota) {
        this.idMascota = idMascota;
    }

    public String getNombreMascota() {
        return nombreMascota;
    }

    public void setNombreMascota(String nombreMascota) {
        this.nombreMascota = nombreMascota;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public List<Vacunacion> getVacunas() {
        return vacunas;
    }

    public void setVacunas(List<Vacunacion> vacunas) {
        this.vacunas = vacunas;
    }

    public List<Cita> getCitas() {
        return citas;
    }

    public void setCitas(List<Cita> citas) {
        this.citas = citas;
    }

    @Override
    public String toString() {
        return "Mascota{" +
                "idMascota=" + idMascota +
                ", idDueno=" + idDueno +
                ", nombreMascota='" + nombreMascota + '\'' +
                ", especie='" + especie + '\'' +
                ", raza='" + raza + '\'' +
                ", vacunas=" + vacunas +
                ", citas=" + citas +
                '}';
    }
}

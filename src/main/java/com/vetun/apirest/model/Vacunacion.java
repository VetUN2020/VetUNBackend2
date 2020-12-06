package com.vetun.apirest.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="vacunacion")
public class Vacunacion {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id_vacunacion")
    private int idVacuna;

    @ManyToOne
    @JoinColumn(name="id_mascota")
    private Mascota idMascota;

    @ManyToOne
    @JoinColumn(name="id_medico")
    private Medico idMedico;

    @Column(name="descripcion_vacuna")
    private String descripcionVacuna;

    @Column(name="fecha_vacuna")
    private Date fechaVacuna;

    public Vacunacion() {
        //Necesary method
    }

    public int getIdVacuna() {
        return idVacuna;
    }

    public void setIdVacuna(int idVacuna) {
        this.idVacuna = idVacuna;
    }

    public Mascota getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(Mascota idMascota) {
        this.idMascota = idMascota;
    }

    public Medico getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(Medico idMedico) {
        this.idMedico = idMedico;
    }

    public String getDescripcionVacuna() {
        return descripcionVacuna;
    }

    public void setDescripcionVacuna(String descripcionVacuna) {
        this.descripcionVacuna = descripcionVacuna;
    }

    public Date getFechaVacuna() {
        return fechaVacuna;
    }

    public void setFechaVacuna(Date fechaVacuna) {
        this.fechaVacuna = fechaVacuna;
    }

    @Override
    public String toString() {
        return "Vacunacion{" +
                "idVacuna=" + idVacuna +
                ", idMascota=" + idMascota +
                ", idMedico=" + idMedico +
                ", descripcionVacuna='" + descripcionVacuna + '\'' +
                ", fechaVacuna=" + fechaVacuna +
                '}';
    }
}

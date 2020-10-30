package com.vetun.apirest.model;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name="cita")
public class Cita {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id_cita")
    private int idCita;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="id_medico")
    private Medico idMedico;

    @ManyToOne
    @JoinColumn(name="id_mascota")
    private Mascota idMascota;

    @ManyToOne
    @JoinColumn(name="id_atencion")
    private TipoAtencion idAtencion;

    @Column(name="fecha_cita")
    private Time fechaCita;

    @Column(name="modalidad_cita")
    private String modalidadCita;

    public Cita() {

    }

    public Cita(int idCita, Medico idMedico, Mascota idMascota, TipoAtencion idAtencion, Time fechaCita, String modalidadCita) {
        this.idCita = idCita;
        this.idMedico = idMedico;
        this.idMascota = idMascota;
        this.idAtencion = idAtencion;
        this.fechaCita = fechaCita;
        this.modalidadCita = modalidadCita;
    }

    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    public Medico getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(Medico idMedico) {
        this.idMedico = idMedico;
    }

    public Mascota getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(Mascota idMascota) {
        this.idMascota = idMascota;
    }

    public TipoAtencion getIdAtencion() {
        return idAtencion;
    }

    public void setIdAtencion(TipoAtencion idAtencion) {
        this.idAtencion = idAtencion;
    }

    public Time getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(Time fechaCita) {
        this.fechaCita = fechaCita;
    }

    public String getModalidadCita() {
        return modalidadCita;
    }

    public void setModalidadCita(String modalidadCita) {
        this.modalidadCita = modalidadCita;
    }
}

package com.vetun.apirest.model;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

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
    @JsonIgnore
    @JoinColumn(name="id_mascota")
    private Mascota idMascota;

    @ManyToOne
    @JoinColumn(name="id_atencion")
    private TipoAtencion idAtencion;

    @Column(name="fecha_cita")
    private Date fechaCita;

    @Column(name="hora_cita")
    private Time horaCita;

    @Column(name="modalidad_cita")
    private String modalidadCita;

    public Cita() {
    //Necesary method
    }

    public Cita(int idCita, Medico idMedico, Mascota idMascota, TipoAtencion idAtencion, Date fechaCita, Time horaCita, String modalidadCita) {
        this.idCita = idCita;
        this.idMedico = idMedico;
        this.idMascota = idMascota;
        this.idAtencion = idAtencion;
        this.fechaCita = fechaCita;
        this.horaCita = horaCita;
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

    public Date getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(Date fechaCita) {
        this.fechaCita = fechaCita;
    }

    public Time getHoraCita() {
        return horaCita;
    }

    public void setHoraCita(Time horaCita) {
        this.horaCita = horaCita;
    }

    public String getModalidadCita() {
        return modalidadCita;
    }

    public void setModalidadCita(String modalidadCita) {
        this.modalidadCita = modalidadCita;
    }
}

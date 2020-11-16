package com.vetun.apirest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name="comentario_medico")
public class ComentarioMedico {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id_comentario_m")
    private int idComentarioMedico;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="id_dueno")
    private Dueno idDueno;

    @ManyToOne
    @JoinColumn(name ="id_medico")
    private Medico idMedico;

    @Column(name="comentario_m")
    private String comentarioM;

    @Column(name="puntuacion_m")
    private float puntuacionM;

    public ComentarioMedico(){
    }

    public int getIdComentarioMedico() {
        return idComentarioMedico;
    }

    public void setIdComentarioMedico(int idComentarioMedico) {
        this.idComentarioMedico = idComentarioMedico;
    }

    public Medico getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(Medico idMedico) {
        this.idMedico = idMedico;
    }

    public String getComentarioM() {
        return comentarioM;
    }

    public void setComentarioM(String comentarioM) {
        this.comentarioM = comentarioM;
    }

    public float getPuntuacionM() {
        return puntuacionM;
    }

    public void setPuntuacionM(float puntuacionM) {
        this.puntuacionM = puntuacionM;
    }

    public Dueno getIdDueno() {
        return idDueno;
    }

    public void setIdDueno(Dueno idDueno) {
        this.idDueno = idDueno;
    }
}

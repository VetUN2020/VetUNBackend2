package com.vetun.apirest.model;


import javax.persistence.*;

@Entity
@Table(name="comentario_veterinaria")
public class ComentarioVeterinaria {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id_comentario_v")
    private int idComentarioVeterinaria;

    @ManyToOne
    @JoinColumn(name="id_dueno")
    private Dueno idDueno;

    @ManyToOne
    @JoinColumn(name ="id_veterinaria")
    private Veterinaria idVeterinaria;

    @Column(name="comentario_v")
    private String comentarioV;

    @Column(name="puntuacion_v")
    private float puntuacionV;


    public ComentarioVeterinaria() {
        //Necesary method
    }

    public int getIdComentarioVeterinaria() {
        return idComentarioVeterinaria;
    }

    public void setIdComentarioVeterinaria(int idComentarioVeterinaria) {
        this.idComentarioVeterinaria = idComentarioVeterinaria;
    }

    public Dueno getIdDueno() {
        return idDueno;
    }

    public void setIdDueno(Dueno idDueno) {
        this.idDueno = idDueno;
    }

    public Veterinaria getIdVeterinaria() {
        return idVeterinaria;
    }

    public void setIdVeterinaria(Veterinaria idVeterinaria) {
        this.idVeterinaria = idVeterinaria;
    }

    public String getComentarioV() {
        return comentarioV;
    }

    public void setComentarioV(String comentarioV) {
        this.comentarioV = comentarioV;
    }

    public float getPuntuacionV() {
        return puntuacionV;
    }

    public void setPuntuacionV(float puntuacionV) {
        this.puntuacionV = puntuacionV;
    }
}

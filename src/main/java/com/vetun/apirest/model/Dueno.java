package com.vetun.apirest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="dueno")
public class Dueno {

    @Id
    @Column(name="id_dueno")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int idDueno;


    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @Column(name="cedula_dueno")
    private String cedulaDueno;

    @Column(name="nombre_dueno")
    private String nombreDueno;

    @Column(name="apellido_dueno")
    private String apellidoDueno;

    @Column(name="telefono_dueno")
    private Long telefonoDueno;

    @Column(name="direccion_dueno")
    private String direccionDueno;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "idDueno")
    private List<Mascota> mascotas;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "idDueno")
    private List<ComentarioMedico> comentariosM;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "idDueno")
    private List<ComentarioVeterinaria> comentariosV;

    public Dueno(){
        //Necesary method
    }

    public int getIdDueno() {
        return idDueno;
    }

    public void setIdDueno(int idDueno) {
        this.idDueno = idDueno;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getCedulaDueno() {
        return cedulaDueno;
    }

    public void setCedulaDueno(String cedulaDueno) {
        this.cedulaDueno = cedulaDueno;
    }

    public String getNombreDueno() {
        return nombreDueno;
    }

    public void setNombreDueno(String nombreDueno) {
        this.nombreDueno = nombreDueno;
    }

    public String getApellidoDueno() {
        return apellidoDueno;
    }

    public void setApellidoDueno(String apellidoDueno) {
        this.apellidoDueno = apellidoDueno;
    }

    public Long getTelefonoDueno() {
        return telefonoDueno;
    }

    public void setTelefonoDueno(Long telefonoDueno) {
        this.telefonoDueno = telefonoDueno;
    }

    public String getDireccionDueno() {
        return direccionDueno;
    }

    public void setDireccionDueno(String direccionDueno) {
        this.direccionDueno = direccionDueno;
    }

    public List<Mascota> getMascotas() {
        return mascotas;
    }

    public void setMascotas(List<Mascota> mascotas) {
        this.mascotas = mascotas;
    }

    public List<ComentarioMedico> getComentariosM() {
        return comentariosM;
    }

    public void setComentariosM(List<ComentarioMedico> comentariosM) {
        this.comentariosM = comentariosM;
    }

    public List<ComentarioVeterinaria> getComentariosV() {
        return comentariosV;
    }

    public void setComentariosV(List<ComentarioVeterinaria> comentariosV) {
        this.comentariosV = comentariosV;
    }

    @Override
    public String toString() {
        return "Dueno{" +
                "idDueno=" + idDueno +
                ", usuario=" + usuario +
                ", cedulaDueno='" + cedulaDueno + '\'' +
                ", nombreDueno='" + nombreDueno + '\'' +
                ", apellidoDueno='" + apellidoDueno + '\'' +
                ", telefonoDueno=" + telefonoDueno +
                ", direccionDueno='" + direccionDueno + '\'' +
                ", mascotas=" + mascotas +
                '}';
    }
}

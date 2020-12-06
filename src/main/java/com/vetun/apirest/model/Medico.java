package com.vetun.apirest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="medico")
public class Medico {
    @Id
    @Column(name="id_medico")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int idMedico;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="id_veterinaria")
    private Veterinaria idVeterinaria;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @Column(name="cedula_medico")
    private String cedulaMedico;

    @Column(name="nombre_medico")
    private String nombreMedico;

    @Column(name="apellido_medico")
    private String apellidoMedico;

    @Column(name="direccion_medico")
    private String direccionMedico;

    @Column(name="telefono_medico")
    private Long telefonoMedico;

    @Column(name="matricula_profesional")
    private String matriculaProfesional;

    @Column(name="link_medico")
    private String linkMedico;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "idMedico")
    private List<HoraAtencion> horarioMedico;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "idMedico")
    private List<Vacunacion> vacunados;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "idMedico")
    private List<Cita> citas;

    @JsonIgnore
    @JsonIgnoreProperties("idMedico")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "idMedico")
    private List<Costo> costos;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "idMedico")
    private List<ComentarioMedico> comentarios;


    public Medico() {
    }

    public int getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
    }

    public Veterinaria getIdVeterinaria() {
        return idVeterinaria;
    }

    public void setIdVeterinaria(Veterinaria idVeterinaria) {
        this.idVeterinaria = idVeterinaria;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getCedulaMedico() {
        return cedulaMedico;
    }

    public void setCedulaMedico(String cedulaMedico) {
        this.cedulaMedico = cedulaMedico;
    }

    public String getNombreMedico() {
        return nombreMedico;
    }

    public void setNombreMedico(String nombreMedico) {
        this.nombreMedico = nombreMedico;
    }

    public String getApellidoMedico() {
        return apellidoMedico;
    }

    public void setApellidoMedico(String apellidoMedico) {
        this.apellidoMedico = apellidoMedico;
    }

    public String getDireccionMedico() {
        return direccionMedico;
    }

    public void setDireccionMedico(String direccionMedico) {
        this.direccionMedico = direccionMedico;
    }

    public Long getTelefonoMedico() {
        return telefonoMedico;
    }

    public void setTelefonoMedico(Long telefonoMedico) {
        this.telefonoMedico = telefonoMedico;
    }

    public String getMatriculaProfesional() {
        return matriculaProfesional;
    }

    public void setMatriculaProfesional(String matriculaProfesional) {
        this.matriculaProfesional = matriculaProfesional;
    }

    public String getLinkMedico() {
        return linkMedico;
    }

    public void setLinkMedico(String linkMedico) {
        this.linkMedico = linkMedico;
    }

    public List<HoraAtencion> getHorarioMedico() {
        return horarioMedico;
    }

    public void setHorarioMedico(List<HoraAtencion> horarioMedico) {
        this.horarioMedico = horarioMedico;
    }

    public List<Vacunacion> getVacunados() {
        return vacunados;
    }

    public void setVacunados(List<Vacunacion> vacunados) {
        this.vacunados = vacunados;
    }

    public List<Cita> getCitas() {
        return citas;
    }

    public void setCitas(List<Cita> citas) {
        this.citas = citas;
    }

    public List<Costo> getCostos() {
        return costos;
    }

    public void setCostos(List<Costo> costos) {
        this.costos = costos;
    }

    public List<ComentarioMedico> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<ComentarioMedico> comentarios) {
        this.comentarios = comentarios;
    }

    @Override
    public String toString() {
        return "Medico{" +
                "idMedico=" + idMedico +
                ", cedulaMedico='" + cedulaMedico + '\'' +
                ", nombreMedico='" + nombreMedico + '\'' +
                ", apellidoMedico='" + apellidoMedico + '\'' +
                ", direccionMedico='" + direccionMedico + '\'' +
                ", telefonoMedico=" + telefonoMedico +
                ", matriculaProfesional='" + matriculaProfesional + '\'' +
                ", linkMedico='" + linkMedico + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Medico medico = (Medico) o;
        return idMedico == medico.idMedico;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMedico);
    }
}

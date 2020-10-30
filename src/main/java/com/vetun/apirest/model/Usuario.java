package com.vetun.apirest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id_usuario")
    private int idUsuario;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, cascade =  CascadeType.ALL, mappedBy = "usuario")
    private Dueno dueno;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, cascade =  CascadeType.ALL, mappedBy = "usuario")
    private Medico medico;

    @ManyToOne
    @JoinColumn(name = "id_rol" )
    private Rol rol;

    @JoinColumn(name = "username" )
    private String username;

    @JsonIgnore
    @JoinColumn(name = "password" )
    private String password;

    @JoinColumn(name = "correo_electronico" )
    private String correoElectronico;

    public Usuario() {
    }

    public Usuario(int idUsuario, Dueno dueno, Medico medico, Rol rol, String username, String password, String correoElectronico) {
        this.idUsuario = idUsuario;
        this.dueno = dueno;
        this.medico = medico;
        this.rol = rol;
        this.username = username;
        this.password = password;
        this.correoElectronico = correoElectronico;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Dueno getDueno() {
        return dueno;
    }

    public void setDueno(Dueno dueno) {
        this.dueno = dueno;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", dueno=" + dueno +
                ", medico=" + medico +
                ", rol=" + rol +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", correoElectronico='" + correoElectronico + '\'' +
                '}';
    }
}

package com.vetun.apirest.model;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name="password_reset")
public class PasswordReset {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id_token")
    private int idToken;

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @JoinColumn(name = "token" )
    private String token;

    @JoinColumn(name = "fecha_expiracion" )
    private Date fechaExpiracion;

    @JoinColumn(name = "hora_expiracion" )
    private Time horaExpiracion;

    public PasswordReset() {
        //Necesary method
    }

    public int getIdToken() {
        return idToken;
    }

    public void setIdToken(int idToken) {
        this.idToken = idToken;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(Date fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    public Time getHoraExpiracion() {
        return horaExpiracion;
    }

    public void setHoraExpiracion(Time horaExpiracion) {
        this.horaExpiracion = horaExpiracion;
    }
}

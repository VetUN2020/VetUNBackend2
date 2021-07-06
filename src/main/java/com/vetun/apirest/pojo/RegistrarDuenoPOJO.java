package com.vetun.apirest.pojo;

public class RegistrarDuenoPOJO {

    private String username;
    private String password;
    private String correoElectronico;

    private String cedulaDueno;
    private String nombreDueno;
    private String apellidoDueno;
    private Long telefonoDueno;
    private String direccionDueno;
    private String token;

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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "RegistrarDuenoPOJO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", correoElectronico='" + correoElectronico + '\'' +
                ", cedulaDueno='" + cedulaDueno + '\'' +
                ", nombreDueno='" + nombreDueno + '\'' +
                ", apellidoDueno='" + apellidoDueno + '\'' +
                ", telefonoDueno=" + telefonoDueno +
                ", direccionDueno='" + direccionDueno + '\'' +
                '}';
    }
}

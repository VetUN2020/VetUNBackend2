package com.vetun.apirest.pojo;

public class PerfilDuenoPOJO {

    private int idDueno;
    private String nombreDueno;
    private String apellidoDueno;
    private String cedulaDueno;
    private String direccionDueno;
    private long telefonoDueno;
    private String correoDueno;
    private String usuarioDueno;

    public PerfilDuenoPOJO() {
    }

    public int getIdDueno(){return idDueno;}

    public void setIdDueno(int idDueno){this.idDueno = idDueno;}

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

    public String getCedulaDueno() {
        return cedulaDueno;
    }

    public void setCedulaDueno(String cedulaDueno) {
        this.cedulaDueno = cedulaDueno;
    }

    public String getDireccionDueno() {
        return direccionDueno;
    }

    public void setDireccionDueno(String direccionDueno) {
        this.direccionDueno = direccionDueno;
    }

    public long getTelefonoDueno() {
        return telefonoDueno;
    }

    public void setTelefonoDueno(long telefonoDueno) {
        this.telefonoDueno = telefonoDueno;
    }

    public String getCorreoDueno() {
        return correoDueno;
    }

    public void setCorreoDueno(String correoDueno) {
        this.correoDueno = correoDueno;
    }

    public String getUsuarioDueno() {
        return usuarioDueno;
    }

    public void setUsuarioDueno(String usuarioDueno) {
        this.usuarioDueno = usuarioDueno;
    }
}

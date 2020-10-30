package com.vetun.apirest.pojo;


public class PerfilVeterinariaPOJO {

    private String nombreVeterinaria;
    private String direccionVeterinaria;
    private long telefonoVeterinaria;
    private String tipoVeterinaria;

    public PerfilVeterinariaPOJO() {
    }

    public String getNombreVeterinaria() {
        return nombreVeterinaria;
    }

    public void setNombreVeterinaria(String nombreVeterinaria) {
        this.nombreVeterinaria = nombreVeterinaria;
    }

    public String getDireccionVeterinaria() {
        return direccionVeterinaria;
    }

    public void setDireccionVeterinaria(String direccionVeterinaria) {
        this.direccionVeterinaria = direccionVeterinaria;
    }

    public long getTelefonoVeterinaria() {
        return telefonoVeterinaria;
    }

    public void setTelefonoVeterinaria(long telefonoVeterinaria) {
        this.telefonoVeterinaria = telefonoVeterinaria;
    }

    public String getTipoVeterinaria() {
        return tipoVeterinaria;
    }

    public void setTipoVeterinaria(String tipoVeterinaria) {
        this.tipoVeterinaria = tipoVeterinaria;
    }

    @Override
    public String toString() {
        return "PerfilVeterinariaPOJO{" +
                "nombreVeterinaria='" + nombreVeterinaria + '\'' +
                ", direccionVeterinaria='" + direccionVeterinaria + '\'' +
                ", telefonoVeterinaria=" + telefonoVeterinaria +
                ", tipoVeterinaria='" + tipoVeterinaria + '\'' +
                '}';
    }
}

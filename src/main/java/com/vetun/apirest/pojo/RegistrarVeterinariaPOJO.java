package com.vetun.apirest.pojo;

public class RegistrarVeterinariaPOJO {
    private String nombreVeterinaria;
    private String direccionVeterinaria;
    private long telefonoVeterinaria;
    private String tipoVeterinaria;

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
        return "RegistrarVeterinariaPOJO{" +
                "nombreVeterinaria='" + nombreVeterinaria + '\'' +
                ", direccionVeterinaria='" + direccionVeterinaria + '\'' +
                ", telefonoVeterinaria=" + telefonoVeterinaria +
                ", tipoVeterinaria='" + tipoVeterinaria + '\'' +
                '}';
    }
}

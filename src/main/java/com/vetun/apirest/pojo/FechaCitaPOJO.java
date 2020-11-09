package com.vetun.apirest.pojo;

import java.sql.Date;

public class FechaCitaPOJO {
    private Date fechaCita;
    private int idMedico;

    public Date getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(Date fechaCita) {
        this.fechaCita = fechaCita;
    }

    public int getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
    }
}

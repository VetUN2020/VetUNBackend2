package com.vetun.apirest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name="hora_atencion")
public class HoraAtencion {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id_hora")
    private int idHora;

    @ManyToOne
    @JsonIgnore
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name="id_medico")
    private Medico idMedico;

    @JoinColumn(name="hora_texto")
    private String horaTexto;

    @JoinColumn(name="hora_tiempo")
    private Time horaTiempo;

    public HoraAtencion() {
    }

    public int getIdHora() {
        return idHora;
    }

    public void setIdHora(int idHora) {
        this.idHora = idHora;
    }

    @JsonIgnore
    public Medico getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(Medico idMedico) {
        this.idMedico = idMedico;
    }

    public String getHoraTexto() {
        return horaTexto;
    }

    public void setHoraTexto(String horaTexto) {
        this.horaTexto = horaTexto;
    }

    public Time getHoraTiempo() {
        return horaTiempo;
    }

    public void setHoraTiempo(Time horaTiempo) {
        this.horaTiempo = horaTiempo;
    }
}

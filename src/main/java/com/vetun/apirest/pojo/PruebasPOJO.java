package com.vetun.apirest.pojo;

import java.sql.Timestamp;

public class PruebasPOJO {

    private String prueba;

    public String getPrueba() {
        return prueba;
    }

    public void setPrueba(String prueba) {
        this.prueba = prueba;
    }

    @Override
    public String toString() {
        return "PruebasPOJO{" +
                "prueba='" + prueba + '\'' +
                '}';
    }
}

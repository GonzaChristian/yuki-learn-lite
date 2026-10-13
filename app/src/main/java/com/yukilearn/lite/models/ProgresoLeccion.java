package com.yukilearn.lite.models;

public class ProgresoLeccion {

    private final int leccionId;
    private final int mejorPuntaje;
    private final int estrellas;

    public ProgresoLeccion(int leccionId, int mejorPuntaje, int estrellas) {
        this.leccionId = leccionId;
        this.mejorPuntaje = mejorPuntaje;
        this.estrellas = estrellas;
    }

    public int getLeccionId() {
        return leccionId;
    }

    public int getMejorPuntaje() {
        return mejorPuntaje;
    }

    public int getEstrellas() {
        return estrellas;
    }

    public boolean isCompletada() {
        return mejorPuntaje >= 50;
    }
}

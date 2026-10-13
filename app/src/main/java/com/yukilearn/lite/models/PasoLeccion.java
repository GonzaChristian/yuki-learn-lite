package com.yukilearn.lite.models;

public class PasoLeccion {

    private final String explicacion;
    private final String codigo;
    private final String contenidoExtra;
    private final String mensajeYuki;

    public PasoLeccion(String explicacion, String codigo,
                       String contenidoExtra, String mensajeYuki) {

        this.explicacion = explicacion;
        this.codigo = codigo;
        this.contenidoExtra = contenidoExtra;
        this.mensajeYuki = mensajeYuki;
    }

    public String getExplicacion() {
        return explicacion;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getContenidoExtra() {
        return contenidoExtra;
    }

    public String getMensajeYuki() {
        return mensajeYuki;
    }
}

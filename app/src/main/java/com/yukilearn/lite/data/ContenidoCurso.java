package com.yukilearn.lite.data;

public class ContenidoCurso {

    public static String obtenerTitulo(int leccionId) {

        switch (leccionId) {
            case 1:
                return "¿Qué es programar?";
            case 2:
                return "Mostrar información";
            case 3:
                return "Variables";
            case 4:
                return "Tipos de datos";
            case 5:
                return "Operadores";
            case 6:
                return "Condicionales";
            case 7:
                return "Desafío de Luna";
            default:
                return "";
        }
    }
}

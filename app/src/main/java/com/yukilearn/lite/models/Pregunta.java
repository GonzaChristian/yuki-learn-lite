package com.yukilearn.lite.models;

public class Pregunta {

    public static final int OPCION = 1;
    public static final int MULTIPLE = 2;
    public static final int LISTA = 3;
    public static final int TEXTO = 4;

    private final int tipo;
    private final String enunciado;
    private final String[] opciones;
    private final int[] correctas;
    private final String respuestaTexto;
    private final String explicacion;

    private Pregunta(int tipo, String enunciado,
                     String[] opciones, int[] correctas,
                     String respuestaTexto, String explicacion) {

        this.tipo = tipo;
        this.enunciado = enunciado;
        this.opciones = opciones;
        this.correctas = correctas;
        this.respuestaTexto = respuestaTexto;
        this.explicacion = explicacion;
    }

    public static Pregunta unica(String enunciado,
                                 String[] opciones,
                                 int correcta,
                                 String explicacion) {

        return new Pregunta(OPCION, enunciado,
                opciones, new int[]{correcta}, "", explicacion);
    }

    public static Pregunta multiple(String enunciado,
                                    String[] opciones,
                                    int[] correctas,
                                    String explicacion) {

        return new Pregunta(MULTIPLE, enunciado,
                opciones, correctas, "", explicacion);
    }

    public static Pregunta lista(String enunciado,
                                 String[] opciones,
                                 int correcta,
                                 String explicacion) {

        return new Pregunta(LISTA, enunciado,
                opciones, new int[]{correcta}, "", explicacion);
    }

    public static Pregunta texto(String enunciado,
                                 String respuesta,
                                 String explicacion) {

        return new Pregunta(TEXTO, enunciado,
                new String[0], new int[0], respuesta, explicacion);
    }

    public int getTipo() {
        return tipo;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public String[] getOpciones() {
        return opciones;
    }

    public String getExplicacion() {
        return explicacion;
    }

    // Para RadioButton y Spinner
    public boolean verificarSeleccion(int indice) {
        return indice == correctas[0];
    }

    // Para CheckBox
    public boolean verificarMultiples(boolean[] marcadas) {

        for (int i = 0; i < marcadas.length; i++) {

            boolean deberiaEstarMarcada = false;

            for (int correcta : correctas) {
                if (correcta == i) {
                    deberiaEstarMarcada = true;
                    break;
                }
            }

            if (marcadas[i] != deberiaEstarMarcada) {
                return false;
            }
        }

        return true;
    }

    // Para EditText
    public boolean verificarTexto(String respuesta) {
        return respuesta.trim()
                .equalsIgnoreCase(respuestaTexto.trim());
    }
}

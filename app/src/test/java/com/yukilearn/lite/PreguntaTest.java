package com.yukilearn.lite;

import com.yukilearn.lite.models.Pregunta;
import org.junit.Test;
import static org.junit.Assert.*;

/** Pruebas unitarias puras de las reglas de respuesta. */
public class PreguntaTest {
    @Test
    public void seleccionUnicaValidaSoloElIndiceCorrecto() {
        Pregunta p = Pregunta.unica("Selecciona", new String[]{"A", "B", "C", "D"}, 2, "Explicacion");
        assertTrue(p.verificarSeleccion(2));
        assertFalse(p.verificarSeleccion(1));
    }

    @Test
    public void seleccionMultipleRequiereLaCombinacionExacta() {
        Pregunta p = Pregunta.multiple("Marca", new String[]{"A", "B", "C", "D"}, new int[]{0, 2}, "Explicacion");
        assertTrue(p.verificarMultiples(new boolean[]{true, false, true, false}));
        assertFalse(p.verificarMultiples(new boolean[]{true, false, false, false}));
        assertFalse(p.verificarMultiples(new boolean[]{true, true, true, false}));
    }

    @Test
    public void respuestaEscritaIgnoraMayusculasYEspaciosExteriores() {
        Pregunta p = Pregunta.texto("Concepto", "algoritmo", "Explicacion");
        assertTrue(p.verificarTexto("  ALGORITMO  "));
        assertFalse(p.verificarTexto("variable"));
    }

    @Test
    public void listaUsaIndiceCorrecto() {
        Pregunta p = Pregunta.lista("Lista", new String[]{"a", "b", "c", "d"}, 1, "Explicacion");
        assertTrue(p.verificarSeleccion(1));
        assertFalse(p.verificarSeleccion(0));
    }
}

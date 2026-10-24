package com.yukilearn.lite;

import com.yukilearn.lite.data.ContenidoCurso;
import com.yukilearn.lite.models.Pregunta;
import org.junit.Test;
import static org.junit.Assert.*;

/** Verifica la integridad basica del contenido incluido en la version 1.0. */
public class ContenidoCursoTest {
    @Test
    public void seisLeccionesTienenContenidoYCincoPreguntas() {
        for (int id = 1; id <= 6; id++) {
            assertFalse(ContenidoCurso.obtenerTitulo(id).isEmpty());
            assertTrue(ContenidoCurso.obtenerPasos(id).length > 0);
            Pregunta[] preguntas = ContenidoCurso.obtenerPreguntas(id);
            assertEquals("Leccion " + id, 5, preguntas.length);
            for (Pregunta pregunta : preguntas) {
                assertFalse(pregunta.getEnunciado().isEmpty());
                assertFalse(pregunta.getExplicacion().isEmpty());
                if (pregunta.getTipo() != Pregunta.TEXTO) {
                    assertEquals(4, pregunta.getOpciones().length);
                }
            }
        }
    }

    @Test
    public void desafioFinalContieneDiezPreguntas() {
        assertEquals(10, ContenidoCurso.obtenerPreguntas(7).length);
        assertEquals(0, ContenidoCurso.obtenerPasos(7).length);
    }
}

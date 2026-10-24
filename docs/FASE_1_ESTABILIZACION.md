# Fase 1 — Estabilizacion tecnica de Yuki Learn Lite

## Alcance

Esta rama contiene cambios de estabilizacion, sin modificar el diseño aprobado ni la estructura de la base de datos.

## Correcciones incorporadas

- **Resultado:** se elimina un valor no valido de `android:minHeight` en `activity_result.xml`.
- **Lecciones:** `LessonActivity` conserva el indice del paso de lectura mediante `onSaveInstanceState`; ademas comprueba que haya pasos disponibles.
- **Cuestionarios:** `QuizActivity` conserva respuestas marcadas en RadioButton, CheckBox, Spinner y EditText cuando Android recrea la Activity, ademas del contador y estado del ejercicio.
- **Pruebas:** se agregan pruebas JUnit para el modelo `Pregunta` y para verificar la integridad del contenido educativo.

## Como probar en Android Studio

1. Abrir la rama `feature/estabilizacion` y esperar la sincronizacion de Gradle.
2. Ejecutar **Build > Make Project** y corregir cualquier incidencia propia del SDK/JDK instalado.
3. Ejecutar los tests de la ruta `app/src/test/java` o el comando `gradlew.bat :app:testDebugUnitTest` en Windows.
4. Crear un perfil nuevo. Abrir la primera leccion y pasar al segundo o tercer paso; girar la pantalla (o usar recreacion desde Opciones de desarrollador) y comprobar que conserva el paso.
5. Comenzar un cuestionario. Probar restauracion con RadioButton, CheckBox, Spinner y texto libre, antes y despues de comprobar una respuesta.
6. Terminar un cuestionario, comprobar resultado, XP y desbloqueo; cerrar y abrir la app y confirmar que persiste el progreso.
7. Repetir una leccion con una nota inferior y verificar que no disminuye el mejor puntaje guardado.
8. Probar tema claro y oscuro; las mejoras de interfaz se implementaran en otra fase.

## Correspondencia con el silabo

- **Semana 5:** variables, metodos y eventos con Java.
- **Semana 6:** condicionales, validaciones y mensajes.
- **Semanas 7 y 8:** RadioButton, CheckBox, Spinner y arreglos.
- **Semana 9:** Activities y navegacion.
- **Semanas 10 y 11:** SQLite y SQLiteOpenHelper.
- **Semana 12:** usabilidad, consistencia y retroalimentacion.

## Concepto adicional que se debe explicar en la sustentacion

`onSaveInstanceState` y `onRestoreInstanceState` preservan el estado temporal de una Activity cuando el sistema Android la recrea (por ejemplo, ante un cambio de configuracion). Este estado es distinto de SQLite: el primero guarda temporalmente el formulario o paso en pantalla; el segundo conserva el progreso educativo en almacenamiento local.

## Pendiente de validacion

No se ejecuto Android Studio ni el emulador en el entorno de revision. Las pruebas y la compilacion deben confirmarse en el equipo con Android SDK y Gradle instalado. El esquema de SQLite sigue en version 1; cualquier evolucion futura debe incluir migraciones mediante `onUpgrade`.

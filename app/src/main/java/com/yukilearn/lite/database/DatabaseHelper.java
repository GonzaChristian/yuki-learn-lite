
package com.yukilearn.lite.database;
import com.yukilearn.lite.models.ProgresoLeccion;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteException;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "yuki_learn.db";
    private static final int DB_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String tablaPerfil =
                "CREATE TABLE perfil (" +
                        "id INTEGER PRIMARY KEY, " +
                        "nombre TEXT NOT NULL, " +
                        "xp INTEGER NOT NULL DEFAULT 0)";

        String tablaProgreso =
                "CREATE TABLE progreso_leccion (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "leccion_id INTEGER UNIQUE NOT NULL, " +
                        "completada INTEGER NOT NULL DEFAULT 0, " +
                        "mejor_puntaje INTEGER NOT NULL DEFAULT 0, " +
                        "estrellas INTEGER NOT NULL DEFAULT 0)";

        db.execSQL(tablaPerfil);
        db.execSQL(tablaProgreso);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Por ahora usamos la primera versión de la base de datos.
    }

    public boolean existePerfil() {

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT id FROM perfil WHERE id = 1",
                null
        );

        boolean existe = cursor.moveToFirst();
        cursor.close();

        return existe;
    }

    public boolean crearPerfil(String nombre) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put("id", 1);
        valores.put("nombre", nombre);
        valores.put("xp", 0);

        long resultado = db.insert("perfil", null, valores);

        return resultado != -1;
    }


    public String obtenerNombre() {

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT nombre FROM perfil WHERE id = 1",
                null
        );

        String nombre = "Estudiante";

        if (cursor.moveToFirst()) {
            nombre = cursor.getString(0);
        }

        cursor.close();
        return nombre;
    }

    public int obtenerXp() {

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT xp FROM perfil WHERE id = 1",
                null
        );

        int xp = 0;

        if (cursor.moveToFirst()) {
            xp = cursor.getInt(0);
        }

        cursor.close();
        return xp;
    }

    public int obtenerSiguienteLeccionId() {

        SQLiteDatabase db = getReadableDatabase();

        for (int i = 1; i <= 7; i++) {

            Cursor cursor = db.rawQuery(
                    "SELECT mejor_puntaje FROM progreso_leccion WHERE leccion_id = ?",
                    new String[]{String.valueOf(i)}
            );

            int mejorPuntaje = 0;

            if (cursor.moveToFirst()) {
                mejorPuntaje = cursor.getInt(0);
            }

            cursor.close();

            if (mejorPuntaje < 50) {
                return i;
            }
        }

        return 0;
    }


    public ProgresoLeccion obtenerProgresoLeccion(int leccionId) {

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT mejor_puntaje, estrellas " +
                        "FROM progreso_leccion WHERE leccion_id = ?",
                new String[]{String.valueOf(leccionId)}
        );

        int mejorPuntaje = 0;
        int estrellas = 0;

        if (cursor.moveToFirst()) {
            mejorPuntaje = cursor.getInt(0);
            estrellas = cursor.getInt(1);
        }

        cursor.close();

        return new ProgresoLeccion(leccionId, mejorPuntaje, estrellas);
    }


    public int guardarResultado(int leccionId, int aciertos, int total) {

        int totalEsperado = (leccionId == 7) ? 10 : 5;

        if (leccionId < 1 || leccionId > 7 ||
                total != totalEsperado ||
                aciertos < 0 || aciertos > total) {
            throw new IllegalArgumentException("Resultado inválido");
        }

        SQLiteDatabase db = getWritableDatabase();

        int porcentaje = (aciertos * 100) / total;
        int xpGanados = 0;

        db.beginTransaction();

        try {

            // Consultar el mejor puntaje anterior
            int mejorAnterior = 0;

            Cursor cursor = db.rawQuery(
                    "SELECT mejor_puntaje FROM progreso_leccion " +
                            "WHERE leccion_id = ?",
                    new String[]{String.valueOf(leccionId)}
            );

            if (cursor.moveToFirst()) {
                mejorAnterior = cursor.getInt(0);
            }

            cursor.close();

            // Solo actualizar si superó su récord anterior
            if (porcentaje > mejorAnterior) {

                int estrellas = 0;

                if (leccionId != 7) {

                    if (porcentaje >= 90) {
                        estrellas = 3;
                    } else if (porcentaje >= 70) {
                        estrellas = 2;
                    } else if (porcentaje >= 50) {
                        estrellas = 1;
                    }
                }

                ContentValues progreso = new ContentValues();

                progreso.put("leccion_id", leccionId);
                progreso.put("mejor_puntaje", porcentaje);
                progreso.put("completada", porcentaje >= 50 ? 1 : 0);
                progreso.put("estrellas", estrellas);

                int filas = db.update(
                        "progreso_leccion",
                        progreso,
                        "leccion_id = ?",
                        new String[]{String.valueOf(leccionId)}
                );

                if (filas == 0) {
                    db.insertOrThrow(
                            "progreso_leccion",
                            null,
                            progreso
                    );
                }

                // XP adicionales por mejorar el récord
                int aciertosAnteriores =
                        (mejorAnterior * total) / 100;

                int xpAdicionales =
                        (aciertos - aciertosAnteriores) * 10;

                Cursor cursorXp = db.rawQuery(
                        "SELECT xp FROM perfil WHERE id = 1",
                        null
                );

                if (!cursorXp.moveToFirst()) {
                    cursorXp.close();
                    throw new SQLiteException("Perfil no encontrado");
                }

                int xpActual = cursorXp.getInt(0);
                cursorXp.close();

                int xpNuevo = Math.min(
                        400,
                        xpActual + xpAdicionales
                );

                ContentValues perfil = new ContentValues();
                perfil.put("xp", xpNuevo);

                int actualizados = db.update(
                        "perfil",
                        perfil,
                        "id = 1",
                        null
                );

                if (actualizados != 1) {
                    throw new SQLiteException("No se guardó el XP");
                }

                xpGanados = xpNuevo - xpActual;
            }

            db.setTransactionSuccessful();

        } finally {
            db.endTransaction();
        }

        return xpGanados;
    }

}


package com.yukilearn.lite.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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

}

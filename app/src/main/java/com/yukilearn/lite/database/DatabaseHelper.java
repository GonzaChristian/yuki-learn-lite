
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
}

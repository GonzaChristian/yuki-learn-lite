package com.yukilearn.lite;

import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.yukilearn.lite.database.DatabaseHelper;

public class ResultActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;

    private int leccionId;
    private int aciertos;
    private int total;
    private int porcentaje;

    private int xpGanados;
    private int mejorPuntaje;
    private boolean desbloqueo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        leccionId = getIntent().getIntExtra("lessonId", -1);
        aciertos = getIntent().getIntExtra("aciertos", -1);
        total = getIntent().getIntExtra("total", -1);

        int totalEsperado = (leccionId == 7) ? 10 : 5;

        if (leccionId < 1 || leccionId > 7 ||
                total != totalEsperado ||
                aciertos < 0 || aciertos > total) {
            finish();
            return;
        }

        porcentaje = (aciertos * 100) / total;

        dbHelper = new DatabaseHelper(this);

        if (savedInstanceState == null) {

            try {

                int mejorAnterior = dbHelper
                        .obtenerProgresoLeccion(leccionId)
                        .getMejorPuntaje();

                xpGanados = dbHelper.guardarResultado(
                        leccionId, aciertos, total
                );

                mejorPuntaje = dbHelper
                        .obtenerProgresoLeccion(leccionId)
                        .getMejorPuntaje();

                desbloqueo = mejorAnterior < 50
                        && mejorPuntaje >= 50;

            } catch (SQLiteException | IllegalArgumentException e) {

                Toast.makeText(
                        this,
                        R.string.result_save_error,
                        Toast.LENGTH_LONG
                ).show();

                finish();
                return;
            }

        } else {

            xpGanados = savedInstanceState.getInt("xpGanados");
            mejorPuntaje = savedInstanceState.getInt("mejorPuntaje");
            desbloqueo = savedInstanceState.getBoolean("desbloqueo");
        }

        setContentView(R.layout.activity_result);

        TextView txtMessage = findViewById(R.id.txtResultMessage);
        TextView txtScore = findViewById(R.id.txtResultScore);
        TextView txtCorrect = findViewById(R.id.txtResultCorrect);
        TextView txtStars = findViewById(R.id.txtResultStars);
        TextView txtBest = findViewById(R.id.txtResultBest);
        TextView txtXp = findViewById(R.id.txtResultXp);
        TextView txtUnlock = findViewById(R.id.txtResultUnlock);

        MaterialButton btnContinue =
                findViewById(R.id.btnResultContinue);

        MaterialButton btnRepeat =
                findViewById(R.id.btnResultRepeat);

        txtScore.setText(
                getString(R.string.result_score, porcentaje)
        );

        txtCorrect.setText(
                getString(R.string.result_correct, aciertos, total)
        );

        txtBest.setText(
                getString(R.string.result_best, mejorPuntaje)
        );

        txtXp.setText(
                getString(R.string.result_xp, xpGanados)
        );

        // Mensaje según el resultado
        if (porcentaje < 50) {

            txtMessage.setText(R.string.result_failed);

        } else if (leccionId == 7) {

            txtMessage.setText(
                    R.string.result_challenge_success
            );

        } else {

            txtMessage.setText(R.string.result_success);
        }

        // Estrellas: solamente para las lecciones normales
        if (leccionId == 7) {

            txtStars.setVisibility(View.GONE);

        } else {

            int estrellas = calcularEstrellas(porcentaje);
            txtStars.setText(mostrarEstrellas(estrellas));
        }

        // Avisar cuando se desbloquea contenido
        if (desbloqueo && leccionId < 7) {

            txtUnlock.setVisibility(View.VISIBLE);

            if (leccionId == 6) {

                txtUnlock.setText(
                        R.string.result_unlock_challenge
                );

            } else {

                txtUnlock.setText(
                        R.string.result_unlock_lesson
                );
            }

        } else {

            txtUnlock.setVisibility(View.GONE);
        }

        btnContinue.setOnClickListener(v -> irAlMapa());

        btnRepeat.setOnClickListener(v -> repetirPractica());

        // El botón Atrás también regresa al mapa.
        getOnBackPressedDispatcher().addCallback(
                this,
                new OnBackPressedCallback(true) {
                    @Override
                    public void handleOnBackPressed() {
                        irAlMapa();
                    }
                }
        );
    }

    private int calcularEstrellas(int puntaje) {

        if (puntaje >= 90) {
            return 3;
        } else if (puntaje >= 70) {
            return 2;
        } else if (puntaje >= 50) {
            return 1;
        }

        return 0;
    }

    private String mostrarEstrellas(int cantidad) {

        StringBuilder resultado = new StringBuilder();

        for (int i = 1; i <= 3; i++) {

            if (i <= cantidad) {
                resultado.append("★");
            } else {
                resultado.append("☆");
            }
        }

        return resultado.toString();
    }

    private void irAlMapa() {

        Intent intent = new Intent(
                this,
                UnitsActivity.class
        );

        intent.addFlags(
                Intent.FLAG_ACTIVITY_CLEAR_TOP |
                        Intent.FLAG_ACTIVITY_SINGLE_TOP
        );

        startActivity(intent);
        finish();
    }

    private void repetirPractica() {

        Intent intent;

        if (leccionId == 7) {

            intent = new Intent(this, QuizActivity.class);

        } else {

            intent = new Intent(this, LessonActivity.class);
        }

        intent.putExtra("lessonId", leccionId);

        startActivity(intent);
        finish();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        outState.putInt("xpGanados", xpGanados);
        outState.putInt("mejorPuntaje", mejorPuntaje);
        outState.putBoolean("desbloqueo", desbloqueo);

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {

        if (dbHelper != null) {
            dbHelper.close();
        }

        super.onDestroy();
    }
}

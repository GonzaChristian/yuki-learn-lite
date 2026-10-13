package com.yukilearn.lite;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.yukilearn.lite.database.DatabaseHelper;
import com.yukilearn.lite.models.ProgresoLeccion;

public class ProgressActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;

    private TextView txtProgressLevel;
    private TextView txtProgressXp;
    private TextView txtProgressNextLevel;

    private TextView txtLessonsCount;
    private TextView txtStarsCount;

    private TextView[] textosUnidades;
    private ProgressBar[] barrasUnidades;

    private TextView txtChallengeStatus;
    private TextView txtChallengeDetail;

    private ProgressBar barProgressXp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_progress);

        dbHelper = new DatabaseHelper(this);

        ImageButton btnBack = findViewById(R.id.btnBackProgress);
        btnBack.setOnClickListener(v -> finish());

        txtProgressLevel = findViewById(R.id.txtProgressLevel);
        txtProgressXp = findViewById(R.id.txtProgressXp);
        txtProgressNextLevel =
                findViewById(R.id.txtProgressNextLevel);

        txtLessonsCount = findViewById(R.id.txtLessonsCount);
        txtStarsCount = findViewById(R.id.txtStarsCount);

        barProgressXp = findViewById(R.id.barProgressXp);

        textosUnidades = new TextView[]{
                findViewById(R.id.txtUnit1Progress),
                findViewById(R.id.txtUnit2Progress),
                findViewById(R.id.txtUnit3Progress)
        };

        barrasUnidades = new ProgressBar[]{
                findViewById(R.id.barUnit1),
                findViewById(R.id.barUnit2),
                findViewById(R.id.barUnit3)
        };

        txtChallengeStatus = findViewById(R.id.txtChallengeStatus);
        txtChallengeDetail = findViewById(R.id.txtChallengeDetail);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (dbHelper != null) {
            cargarProgreso();
        }
    }

    private void cargarProgreso() {

        // Experiencia y nivel
        int xp = dbHelper.obtenerXp();
        int nivel = Math.min(5, (xp / 100) + 1);

        txtProgressLevel.setText(
                getString(R.string.progress_level, nivel)
        );

        txtProgressXp.setText(
                getString(R.string.progress_total_xp, xp)
        );

        if (nivel == 5) {

            barProgressXp.setProgress(100);
            txtProgressNextLevel.setText(
                    R.string.progress_max_level
            );

        } else {

            barProgressXp.setProgress(xp % 100);

            int restantes = (nivel * 100) - xp;

            txtProgressNextLevel.setText(
                    getString(
                            R.string.progress_next_level,
                            restantes
                    )
            );
        }

        // Lecciones y estrellas
        int totalCompletadas = 0;
        int totalEstrellas = 0;

        int[] completadasUnidad = new int[3];

        for (int leccionId = 1; leccionId <= 6; leccionId++) {

            ProgresoLeccion progreso =
                    dbHelper.obtenerProgresoLeccion(leccionId);

            if (progreso.isCompletada()) {

                totalCompletadas++;

                // Cada unidad tiene dos lecciones
                int unidad = (leccionId - 1) / 2;
                completadasUnidad[unidad]++;
            }

            totalEstrellas += progreso.getEstrellas();
        }

        txtLessonsCount.setText(
                getString(
                        R.string.progress_lessons_count,
                        totalCompletadas
                )
        );

        txtStarsCount.setText(
                getString(
                        R.string.progress_stars_count,
                        totalEstrellas
                )
        );

        // Actualizar las tres unidades
        for (int i = 0; i < 3; i++) {

            int completadas = completadasUnidad[i];
            int porcentaje = completadas * 50;

            textosUnidades[i].setText(
                    getString(
                            R.string.progress_unit_count,
                            completadas,
                            porcentaje
                    )
            );

            barrasUnidades[i].setProgress(porcentaje);
        }

        // Desafío de Luna
        ProgresoLeccion desafio =
                dbHelper.obtenerProgresoLeccion(7);

        if (desafio.isCompletada()) {

            txtChallengeStatus.setText(
                    R.string.progress_challenge_completed
            );
            txtChallengeDetail.setVisibility(View.GONE);

        } else if (totalCompletadas == 6) {

            txtChallengeStatus.setText(
                    R.string.progress_challenge_available
            );
            txtChallengeDetail.setVisibility(View.GONE);

        } else {

            txtChallengeStatus.setText(
                    R.string.progress_challenge_locked
            );
            txtChallengeDetail.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onDestroy() {

        if (dbHelper != null) {
            dbHelper.close();
        }

        super.onDestroy();
    }
}

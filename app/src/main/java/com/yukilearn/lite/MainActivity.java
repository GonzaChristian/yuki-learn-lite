package com.yukilearn.lite;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;
import com.yukilearn.lite.data.ContenidoCurso;
import com.yukilearn.lite.database.DatabaseHelper;

public class MainActivity extends AppCompatActivity {

    private TextView txtGreeting;
    private TextView txtLevel;
    private TextView txtXp;
    private TextView txtXpNextLevel;
    private TextView txtContinueLesson;

    private ProgressBar progressXp;

    private MaterialButton btnContinue;
    private MaterialButton btnLearn;
    private MaterialButton btnProgress;

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.main),
                (v, insets) -> {
                    Insets barras = insets.getInsets(
                            WindowInsetsCompat.Type.systemBars()
                    );

                    v.setPadding(
                            barras.left,
                            barras.top,
                            barras.right,
                            barras.bottom
                    );

                    return insets;
                }
        );

        dbHelper = new DatabaseHelper(this);

        txtGreeting = findViewById(R.id.txtGreeting);
        txtLevel = findViewById(R.id.txtLevel);
        txtXp = findViewById(R.id.txtXp);
        txtXpNextLevel = findViewById(R.id.txtXpNextLevel);
        txtContinueLesson = findViewById(R.id.txtContinueLesson);

        progressXp = findViewById(R.id.progressXp);

        btnContinue = findViewById(R.id.btnContinue);
        btnLearn = findViewById(R.id.btnLearn);
        btnProgress = findViewById(R.id.btnProgress);

        // Navegación temporal hasta crear las siguientes Activities
        btnContinue.setOnClickListener(v -> {
            Toast.makeText(
                    this,
                    R.string.main_pending_route,
                    Toast.LENGTH_SHORT
            ).show();
        });

        btnLearn.setOnClickListener(v -> {
            Toast.makeText(
                    this,
                    R.string.main_pending_route,
                    Toast.LENGTH_SHORT
            ).show();
        });

        btnProgress.setOnClickListener(v -> {
            Toast.makeText(
                    this,
                    R.string.main_pending_progress,
                    Toast.LENGTH_SHORT
            ).show();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (dbHelper != null) {
            cargarDatos();
        }
    }

    private void cargarDatos() {

        // Datos del perfil
        String nombre = dbHelper.obtenerNombre();
        int xp = dbHelper.obtenerXp();

        txtGreeting.setText(
                getString(R.string.main_greeting, nombre)
        );

        txtXp.setText(
                getString(R.string.main_xp_total, xp)
        );

        // Cálculo del nivel
        int nivel = (xp / 100) + 1;

        if (nivel > 5) {
            nivel = 5;
        }

        txtLevel.setText(
                getString(R.string.main_level, nivel)
        );

        // Progreso de experiencia
        if (nivel == 5) {

            progressXp.setProgress(100);
            txtXpNextLevel.setText(R.string.main_max_level);

        } else {

            int progreso = xp % 100;
            int xpRestantes = (nivel * 100) - xp;

            progressXp.setProgress(progreso);

            txtXpNextLevel.setText(
                    getString(
                            R.string.main_xp_remaining,
                            xpRestantes,
                            nivel + 1
                    )
            );
        }

        // Siguiente lección disponible
        int siguienteId = dbHelper.obtenerSiguienteLeccionId();

        if (siguienteId >= 1 && siguienteId <= 6) {

            String titulo = ContenidoCurso.obtenerTitulo(siguienteId);

            txtContinueLesson.setText(
                    getString(
                            R.string.main_lesson_format,
                            siguienteId,
                            titulo
                    )
            );

            btnContinue.setText(R.string.main_continue);

        } else if (siguienteId == 7) {

            txtContinueLesson.setText(R.string.main_challenge_title);
            btnContinue.setText(R.string.main_challenge_button);

        } else {

            txtContinueLesson.setText(R.string.main_course_completed);
            btnContinue.setText(R.string.main_completed_button);
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

package com.yukilearn.lite;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.button.MaterialButton;
import com.yukilearn.lite.data.ContenidoCurso;
import com.yukilearn.lite.database.DatabaseHelper;
import com.yukilearn.lite.models.ProgresoLeccion;

public class UnitsActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;

    private MaterialButton[] nodos;
    private FrameLayout[] filas;
    private TextView[] textosEstrellas;

    private ImageView imgYuki;
    private TextView txtFinalStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_units);

        dbHelper = new DatabaseHelper(this);

        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

        imgYuki = findViewById(R.id.imgYuki);
        txtFinalStatus = findViewById(R.id.txtFinalStatus);

        nodos = new MaterialButton[]{
                findViewById(R.id.nodeLesson1),
                findViewById(R.id.nodeLesson2),
                findViewById(R.id.nodeLesson3),
                findViewById(R.id.nodeLesson4),
                findViewById(R.id.nodeLesson5),
                findViewById(R.id.nodeLesson6),
                findViewById(R.id.nodeFinalChallenge)
        };

        filas = new FrameLayout[]{
                findViewById(R.id.rowLesson1),
                findViewById(R.id.rowLesson2),
                findViewById(R.id.rowLesson3),
                findViewById(R.id.rowLesson4),
                findViewById(R.id.rowLesson5),
                findViewById(R.id.rowLesson6),
                findViewById(R.id.rowFinalChallenge)
        };

        textosEstrellas = new TextView[]{
                findViewById(R.id.txtStars1),
                findViewById(R.id.txtStars2),
                findViewById(R.id.txtStars3),
                findViewById(R.id.txtStars4),
                findViewById(R.id.txtStars5),
                findViewById(R.id.txtStars6)
        };

        for (int i = 0; i < nodos.length; i++) {

            int leccionId = i + 1;

            nodos[i].setOnClickListener(
                    v -> abrirLeccion(leccionId)
            );
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        actualizarMapa();
    }

    private void actualizarMapa() {

        int[] mejoresPuntajes = new int[8];
        int[] estrellas = new int[8];

        // Consultar las 6 lecciones y el desafío
        for (int i = 1; i <= 7; i++) {

            ProgresoLeccion progreso =
                    dbHelper.obtenerProgresoLeccion(i);

            mejoresPuntajes[i] = progreso.getMejorPuntaje();
            estrellas[i] = progreso.getEstrellas();
        }

        for (int i = 1; i <= 7; i++) {

            boolean completada = mejoresPuntajes[i] >= 50;

            boolean bloqueada = false;

            if (i > 1 && mejoresPuntajes[i - 1] < 50) {
                bloqueada = true;
            }

            MaterialButton nodo = nodos[i - 1];

            String titulo = ContenidoCurso.obtenerTitulo(i);

            String texto;

            if (i == 7) {
                texto = titulo;
            } else {
                texto = i + " · " + titulo;
            }

            int colorFondo;
            int colorTexto;

            if (completada) {

                texto = "✓ " + texto;

                colorFondo = obtenerColor(R.color.yuki_success);
                colorTexto = Color.WHITE;

            } else if (bloqueada) {

                texto = "🔒 " + texto;

                colorFondo = obtenerColor(R.color.yuki_secondary);
                colorTexto = obtenerColor(R.color.yuki_text_secondary);

            } else {

                texto = "▶ " + texto;

                colorFondo = obtenerColor(R.color.yuki_primary);
                colorTexto = obtenerColor(R.color.yuki_on_primary);
            }

            // Desafío disponible: color especial
            if (i == 7 && !bloqueada && !completada) {

                colorFondo = obtenerColor(R.color.yuki_accent);
                colorTexto = Color.rgb(37, 40, 61);
            }

            nodo.setText(texto);
            nodo.setBackgroundTintList(
                    ColorStateList.valueOf(colorFondo)
            );
            nodo.setTextColor(colorTexto);

            // Estrellas solo para las 6 lecciones
            if (i <= 6) {

                TextView txtStars = textosEstrellas[i - 1];

                if (estrellas[i] > 0) {

                    txtStars.setText(
                            construirEstrellas(estrellas[i])
                    );

                    txtStars.setVisibility(View.VISIBLE);

                } else {
                    txtStars.setVisibility(View.GONE);
                }

            } else {

                if (completada) {
                    txtFinalStatus.setText(
                            R.string.units_challenge_completed
                    );
                } else if (bloqueada) {
                    txtFinalStatus.setText(
                            R.string.units_challenge_locked
                    );
                } else {
                    txtFinalStatus.setText(
                            R.string.units_challenge_available
                    );
                }
            }
        }

        int siguienteId = dbHelper.obtenerSiguienteLeccionId();

        posicionarYuki(siguienteId);
    }

    private String construirEstrellas(int cantidad) {

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

    private void posicionarYuki(int leccionId) {

        if (leccionId == 0) {
            imgYuki.setVisibility(View.GONE);
            return;
        }

        ViewGroup padreAnterior =
                (ViewGroup) imgYuki.getParent();

        if (padreAnterior != null) {
            padreAnterior.removeView(imgYuki);
        }

        int lado;

        if (leccionId % 2 == 1) {
            lado = Gravity.END;
        } else {
            lado = Gravity.START;
        }

        int tamanio = dp(64);

        FrameLayout.LayoutParams parametros =
                new FrameLayout.LayoutParams(
                        tamanio,
                        tamanio,
                        lado | Gravity.CENTER_VERTICAL
                );

        parametros.setMargins(dp(4), 0, dp(4), 0);

        filas[leccionId - 1].addView(
                imgYuki,
                parametros
        );

        imgYuki.setVisibility(View.VISIBLE);
    }

    private int dp(int valor) {
        return (int) (
                valor * getResources()
                        .getDisplayMetrics().density
        );
    }

    private int obtenerColor(int colorId) {
        return ContextCompat.getColor(this, colorId);
    }


    private void abrirLeccion(int leccionId) {

        if (leccionId > 1) {

            ProgresoLeccion anterior =
                    dbHelper.obtenerProgresoLeccion(leccionId - 1);

            if (!anterior.isCompletada()) {

                Toast.makeText(
                        this,
                        R.string.units_locked_message,
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }
        }

        if (leccionId == 7) {

            // El Desafío de Luna se conectará con QuizActivity.
            Toast.makeText(
                    this,
                    R.string.units_challenge_pending,
                    Toast.LENGTH_SHORT
            ).show();

        } else {

            Intent intent = new Intent(
                    UnitsActivity.this,
                    LessonActivity.class
            );

            intent.putExtra("lessonId", leccionId);

            startActivity(intent);
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

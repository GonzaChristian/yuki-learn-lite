package com.yukilearn.lite;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import com.google.android.material.button.MaterialButton;
import com.yukilearn.lite.data.ContenidoCurso;
import com.yukilearn.lite.models.PasoLeccion;

public class LessonActivity extends AppCompatActivity {

    private TextView txtLessonTitle;
    private TextView txtStepCounter;
    private TextView txtExplanation;
    private TextView txtCode;
    private TextView txtExtraContent;
    private TextView txtYukiMessage;

    private LinearLayout cardCode;
    private LinearLayout layoutYukiReaction;

    private ProgressBar progressLesson;
    private MaterialButton btnNext;
    private NestedScrollView scrollLesson;

    private PasoLeccion[] pasos;

    private int pasoActual = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lesson);

        int leccionId = getIntent().getIntExtra("lessonId", -1);

        if (leccionId < 1 || leccionId > 6) {
            finish();
            return;
        }

        pasos = ContenidoCurso.obtenerPasos(leccionId);

        txtLessonTitle = findViewById(R.id.txtLessonTitle);
        txtStepCounter = findViewById(R.id.txtStepCounter);
        txtExplanation = findViewById(R.id.txtExplanation);
        txtCode = findViewById(R.id.txtCode);
        txtExtraContent = findViewById(R.id.txtExtraContent);
        txtYukiMessage = findViewById(R.id.txtYukiMessage);

        cardCode = findViewById(R.id.cardCode);
        layoutYukiReaction = findViewById(R.id.layoutYukiReaction);

        progressLesson = findViewById(R.id.progressLesson);
        scrollLesson = findViewById(R.id.scrollLesson);
        btnNext = findViewById(R.id.btnNext);

        ImageButton btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(v -> finish());

        txtLessonTitle.setText(
                ContenidoCurso.obtenerTitulo(leccionId)
        );

        progressLesson.setMax(pasos.length);

        mostrarPaso();

        btnNext.setOnClickListener(v -> siguientePaso());
    }

    private void mostrarPaso() {

        PasoLeccion paso = pasos[pasoActual];

        txtStepCounter.setText(
                getString(
                        R.string.lesson_step_counter,
                        pasoActual + 1,
                        pasos.length
                )
        );

        progressLesson.setProgress(pasoActual + 1);

        txtExplanation.setText(paso.getExplicacion());

        // Mostrar u ocultar código
        if (paso.getCodigo().isEmpty()) {
            cardCode.setVisibility(View.GONE);
        } else {
            cardCode.setVisibility(View.VISIBLE);
            txtCode.setText(paso.getCodigo());
        }

        // Mostrar u ocultar contenido complementario
        if (paso.getContenidoExtra().isEmpty()) {
            txtExtraContent.setVisibility(View.GONE);
        } else {
            txtExtraContent.setVisibility(View.VISIBLE);
            txtExtraContent.setText(paso.getContenidoExtra());
        }

        // Mostrar u ocultar comentario de Yuki
        if (paso.getMensajeYuki().isEmpty()) {
            layoutYukiReaction.setVisibility(View.GONE);
        } else {
            layoutYukiReaction.setVisibility(View.VISIBLE);
            txtYukiMessage.setText(paso.getMensajeYuki());
        }

        if (pasoActual == pasos.length - 1) {
            btnNext.setText(R.string.lesson_start_quiz);
        } else {
            btnNext.setText(R.string.lesson_next);
        }

        scrollLesson.post(() -> scrollLesson.scrollTo(0, 0));
    }


    private void siguientePaso() {

        if (pasoActual < pasos.length - 1) {

            pasoActual++;
            mostrarPaso();

        } else {

            int leccionId = getIntent().getIntExtra("lessonId", -1);

            Intent intent = new Intent(
                    LessonActivity.this,
                    QuizActivity.class
            );

            intent.putExtra("lessonId", leccionId);

            startActivity(intent);
            finish();
        }
    }

}

package com.yukilearn.lite;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;

import com.google.android.material.button.MaterialButton;
import com.yukilearn.lite.data.ContenidoCurso;
import com.yukilearn.lite.models.Pregunta;

public class QuizActivity extends AppCompatActivity {

    private Pregunta[] preguntas;

    private int preguntaActual = 0;
    private int aciertos = 0;

    private boolean comprobada = false;
    private boolean ultimaCorrecta = false;

    private TextView txtQuizTitle;
    private TextView txtQuestionCounter;
    private TextView txtQuestion;
    private TextView txtInstruction;
    private TextView txtFeedback;

    private ProgressBar progressQuiz;
    private NestedScrollView scrollQuiz;

    private RadioGroup groupRadio;
    private LinearLayout groupChecks;

    private RadioButton[] radios;
    private CheckBox[] checks;

    private Spinner spinnerAnswer;
    private EditText edtAnswer;
    private MaterialButton btnCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int leccionId = getIntent().getIntExtra("lessonId", -1);

        if (leccionId < 1 || leccionId > 7) {
            finish();
            return;
        }

        preguntas = ContenidoCurso.obtenerPreguntas(leccionId);

        if (preguntas.length == 0) {
            finish();
            return;
        }

        setContentView(R.layout.activity_quiz);

        txtQuizTitle = findViewById(R.id.txtQuizTitle);
        txtQuestionCounter = findViewById(R.id.txtQuestionCounter);
        txtQuestion = findViewById(R.id.txtQuestion);
        txtInstruction = findViewById(R.id.txtInstruction);
        txtFeedback = findViewById(R.id.txtFeedback);

        progressQuiz = findViewById(R.id.progressQuiz);
        scrollQuiz = findViewById(R.id.scrollQuiz);

        groupRadio = findViewById(R.id.groupRadio);
        groupChecks = findViewById(R.id.groupChecks);

        spinnerAnswer = findViewById(R.id.spinnerAnswer);
        edtAnswer = findViewById(R.id.edtAnswer);
        btnCheck = findViewById(R.id.btnCheck);

        radios = new RadioButton[]{
                findViewById(R.id.radio0),
                findViewById(R.id.radio1),
                findViewById(R.id.radio2),
                findViewById(R.id.radio3)
        };

        checks = new CheckBox[]{
                findViewById(R.id.check0),
                findViewById(R.id.check1),
                findViewById(R.id.check2),
                findViewById(R.id.check3)
        };

        ImageButton btnBack = findViewById(R.id.btnBackQuiz);
        btnBack.setOnClickListener(v -> confirmarSalida());

        getOnBackPressedDispatcher().addCallback(
                this,
                new OnBackPressedCallback(true) {
                    @Override
                    public void handleOnBackPressed() {
                        confirmarSalida();
                    }
                }
        );

        txtQuizTitle.setText(ContenidoCurso.obtenerTitulo(leccionId));
        progressQuiz.setMax(preguntas.length);

        boolean estabaComprobada = false;

        if (savedInstanceState != null) {
            preguntaActual = savedInstanceState.getInt("preguntaActual");
            aciertos = savedInstanceState.getInt("aciertos");
            estabaComprobada = savedInstanceState.getBoolean("comprobada");
            ultimaCorrecta = savedInstanceState.getBoolean("ultimaCorrecta");
        }

        mostrarPregunta();

        if (estabaComprobada) {
            comprobada = true;
            mostrarFeedback(ultimaCorrecta);
        }

        btnCheck.setOnClickListener(v -> {

            if (comprobada) {
                siguientePregunta();
            } else {
                comprobarRespuesta();
            }
        });
    }

    private void mostrarPregunta() {

        Pregunta pregunta = preguntas[preguntaActual];

        comprobada = false;

        txtQuestionCounter.setText(
                getString(
                        R.string.quiz_question_counter,
                        preguntaActual + 1,
                        preguntas.length
                )
        );

        progressQuiz.setProgress(preguntaActual + 1);
        txtQuestion.setText(pregunta.getEnunciado());

        groupRadio.setVisibility(View.GONE);
        groupChecks.setVisibility(View.GONE);
        spinnerAnswer.setVisibility(View.GONE);
        edtAnswer.setVisibility(View.GONE);
        txtFeedback.setVisibility(View.GONE);

        groupRadio.clearCheck();

        for (CheckBox check : checks) {
            check.setChecked(false);
        }

        edtAnswer.setText("");

        // Habilitar los controles de la nueva pregunta
        for (RadioButton radio : radios) {
            radio.setEnabled(true);
        }

        for (CheckBox check : checks) {
            check.setEnabled(true);
        }

        spinnerAnswer.setEnabled(true);
        edtAnswer.setEnabled(true);

        String[] opciones = pregunta.getOpciones();

        switch (pregunta.getTipo()) {

            case Pregunta.OPCION:

                txtInstruction.setText(
                        R.string.quiz_single_instruction
                );

                groupRadio.setVisibility(View.VISIBLE);

                for (int i = 0; i < radios.length; i++) {
                    radios[i].setText(opciones[i]);
                }
                break;

            case Pregunta.MULTIPLE:

                txtInstruction.setText(
                        R.string.quiz_multiple_instruction
                );

                groupChecks.setVisibility(View.VISIBLE);

                for (int i = 0; i < checks.length; i++) {
                    checks[i].setText(opciones[i]);
                }
                break;

            case Pregunta.LISTA:

                txtInstruction.setText(
                        R.string.quiz_list_instruction
                );

                spinnerAnswer.setVisibility(View.VISIBLE);

                String[] elementos = new String[opciones.length + 1];

                elementos[0] = getString(
                        R.string.quiz_select_placeholder
                );

                for (int i = 0; i < opciones.length; i++) {
                    elementos[i + 1] = opciones[i];
                }

                ArrayAdapter<String> adaptador =
                        new ArrayAdapter<>(
                                this,
                                android.R.layout.simple_spinner_item,
                                elementos
                        );

                adaptador.setDropDownViewResource(
                        android.R.layout.simple_spinner_dropdown_item
                );

                spinnerAnswer.setAdapter(adaptador);
                spinnerAnswer.setSelection(0);
                break;

            case Pregunta.TEXTO:

                txtInstruction.setText(
                        R.string.quiz_text_instruction
                );

                edtAnswer.setVisibility(View.VISIBLE);
                break;
        }

        btnCheck.setText(R.string.quiz_check);

        scrollQuiz.post(() -> scrollQuiz.scrollTo(0, 0));
    }

    private void comprobarRespuesta() {

        Pregunta pregunta = preguntas[preguntaActual];

        boolean correcta = false;
        boolean respondida = true;

        switch (pregunta.getTipo()) {

            case Pregunta.OPCION:

                int idSeleccionado =
                        groupRadio.getCheckedRadioButtonId();

                int indiceSeleccionado = -1;

                for (int i = 0; i < radios.length; i++) {
                    if (radios[i].getId() == idSeleccionado) {
                        indiceSeleccionado = i;
                        break;
                    }
                }

                if (indiceSeleccionado == -1) {
                    respondida = false;
                } else {
                    correcta = pregunta.verificarSeleccion(
                            indiceSeleccionado
                    );
                }
                break;

            case Pregunta.MULTIPLE:

                boolean[] marcadas = new boolean[checks.length];
                boolean algunaMarcada = false;

                for (int i = 0; i < checks.length; i++) {
                    marcadas[i] = checks[i].isChecked();

                    if (marcadas[i]) {
                        algunaMarcada = true;
                    }
                }

                if (!algunaMarcada) {
                    respondida = false;
                } else {
                    correcta = pregunta.verificarMultiples(marcadas);
                }
                break;

            case Pregunta.LISTA:

                int posicion = spinnerAnswer.getSelectedItemPosition();

                if (posicion <= 0) {
                    respondida = false;
                } else {
                    correcta = pregunta.verificarSeleccion(
                            posicion - 1
                    );
                }
                break;

            case Pregunta.TEXTO:

                String respuesta =
                        edtAnswer.getText().toString().trim();

                if (respuesta.isEmpty()) {
                    respondida = false;
                } else {
                    correcta = pregunta.verificarTexto(respuesta);
                }
                break;
        }

        if (!respondida) {
            Toast.makeText(
                    this,
                    R.string.quiz_answer_required,
                    Toast.LENGTH_SHORT
            ).show();
            return;
        }

        comprobada = true;
        ultimaCorrecta = correcta;

        if (correcta) {
            aciertos++;
        }

        mostrarFeedback(correcta);
    }

    private void mostrarFeedback(boolean correcta) {

        Pregunta pregunta = preguntas[preguntaActual];

        String titulo;
        int color;

        if (correcta) {
            titulo = getString(R.string.quiz_correct);
            color = R.color.yuki_success;
        } else {
            titulo = getString(R.string.quiz_incorrect);
            color = R.color.yuki_error;
        }

        txtFeedback.setText(
                titulo + "\n\n" + pregunta.getExplicacion()
        );

        txtFeedback.setTextColor(
                ContextCompat.getColor(this, color)
        );

        txtFeedback.setVisibility(View.VISIBLE);

        // No permitir modificar una respuesta ya comprobada
        for (RadioButton radio : radios) {
            radio.setEnabled(false);
        }

        for (CheckBox check : checks) {
            check.setEnabled(false);
        }

        spinnerAnswer.setEnabled(false);
        edtAnswer.setEnabled(false);

        btnCheck.setText(R.string.quiz_continue);
    }

    private void siguientePregunta() {

        if (preguntaActual < preguntas.length - 1) {

            preguntaActual++;
            mostrarPregunta();

        } else {
            terminarPractica();
        }
    }


    private void terminarPractica() {

        int leccionId = getIntent().getIntExtra("lessonId", -1);

        Intent intent = new Intent(
                QuizActivity.this,
                ResultActivity.class
        );

        intent.putExtra("lessonId", leccionId);
        intent.putExtra("aciertos", aciertos);
        intent.putExtra("total", preguntas.length);

        startActivity(intent);
        finish();
    }


    private void confirmarSalida() {

        new AlertDialog.Builder(this)
                .setTitle(R.string.quiz_exit_title)
                .setMessage(R.string.quiz_exit_message)
                .setNegativeButton(
                        R.string.quiz_stay,
                        null
                )
                .setPositiveButton(
                        R.string.quiz_exit,
                        (dialog, which) -> finish()
                )
                .show();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("preguntaActual", preguntaActual);
        outState.putInt("aciertos", aciertos);
        outState.putBoolean("comprobada", comprobada);
        outState.putBoolean("ultimaCorrecta", ultimaCorrecta);

        super.onSaveInstanceState(outState);
    }
}

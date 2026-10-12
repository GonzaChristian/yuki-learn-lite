
package com.yukilearn.lite;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.yukilearn.lite.database.DatabaseHelper;

public class ProfileActivity extends AppCompatActivity {

    private EditText edtName;
    private MaterialButton btnStart;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dbHelper = new DatabaseHelper(this);

        if (dbHelper.existePerfil()) {
            abrirInicio();
            return;
        }

        setContentView(R.layout.activity_profile);

        edtName = findViewById(R.id.edtName);
        btnStart = findViewById(R.id.btnStart);

        btnStart.setOnClickListener(v -> guardarPerfil());
    }

    private void guardarPerfil() {

        String nombre = edtName.getText().toString().trim();

        if (nombre.isEmpty()) {
            edtName.setError(getString(R.string.profile_name_error));
            edtName.requestFocus();
            return;
        }

        btnStart.setEnabled(false);

        boolean guardado = dbHelper.crearPerfil(nombre);

        if (guardado) {
            abrirInicio();
        } else {
            Toast.makeText(
                    this,
                    R.string.profile_save_error,
                    Toast.LENGTH_SHORT
            ).show();

            btnStart.setEnabled(true);
        }
    }

    private void abrirInicio() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        if (dbHelper != null) {
            dbHelper.close();
        }
        super.onDestroy();
    }
}

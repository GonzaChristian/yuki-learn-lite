
package com.yukilearn.lite;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;

import com.yukilearn.lite.database.DatabaseHelper;

public class SplashActivity extends AppCompatActivity {

    private final Handler handler = new Handler(Looper.getMainLooper());

    private final Runnable abrirInicio = () -> {

        if (!isFinishing() && !isDestroyed()) {

            DatabaseHelper dbHelper = new DatabaseHelper(this);

            boolean existePerfil = dbHelper.existePerfil();

            dbHelper.close();

            Intent intent;

            if (existePerfil) {
                intent = new Intent(this, MainActivity.class);
            } else {
                intent = new Intent(this, ProfileActivity.class);
            }

            startActivity(intent);
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        handler.postDelayed(abrirInicio, 1800);
    }

    @Override
    protected void onDestroy() {
        handler.removeCallbacks(abrirInicio);
        super.onDestroy();
    }
}

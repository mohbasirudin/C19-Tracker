package com.bismillah.basirudin.covidapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.bismillah.basirudin.covidapp.R;

public class PageSplash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_splash);

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(getApplicationContext(), PageMain.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }, 2000);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        recreate();
    }
}

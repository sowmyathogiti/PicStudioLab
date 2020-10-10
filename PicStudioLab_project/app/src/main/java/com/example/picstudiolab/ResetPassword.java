package com.example.picstudiolab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResetPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        TextView back = findViewById(R.id.back);
        Button reset = findViewById(R.id.reset);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               backScreen();
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetScreen();
            }
        });
    }

    private void resetScreen() {
        Intent resetIntent = new Intent(this, SentReset.class);
        startActivity(resetIntent);
    }

    private void backScreen() {
        Intent back = new Intent(this, MainActivity.class);
        startActivity(back);
    }

    public static class dashboardFragment {
    }
}
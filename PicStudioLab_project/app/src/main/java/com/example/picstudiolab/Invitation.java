package com.example.picstudiolab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Invitation extends AppCompatActivity {
    Button addinv;
    Button zoom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invitation);
        addinv = findViewById(R.id.addinv);
        zoom = findViewById(R.id.zoom);
        addinv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intaddinv = new Intent(getApplicationContext(), BrowsePics.class);
                startActivity(intaddinv);
            }
        });
        zoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intzoom = new Intent(getApplicationContext(), ZoomInvitation.class);
                startActivity(intzoom);
            }
        });
    }
}
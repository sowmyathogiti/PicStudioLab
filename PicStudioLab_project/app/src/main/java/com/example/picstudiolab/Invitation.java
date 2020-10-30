package com.example.picstudiolab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Invitation extends AppCompatActivity {
    Button addinv;
    Button zoom;

    int count7,count8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invitation);

        FloatingActionButton add7 = findViewById(R.id.add7);
        FloatingActionButton add8 = findViewById(R.id.add8);

        final TextView addvalue7 = findViewById(R.id.addvalue7);
        final TextView addvalue8 = findViewById(R.id.addvalue8);

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

        add7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count7++;
                String num = (String.valueOf(count7));
                addvalue7.setText(num);
            }
        });

        add8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count8++;
                String num = (String.valueOf(count8));
                addvalue8.setText(num);
            }
        });
    }
}
package com.example.picstudiolab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FrameSetup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_setup);

        Button cartbtn = findViewById(R.id.cartbtn);

        cartbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               cartClick();
            }
        });
    }

    private void cartClick() {
        Intent browseIntent = new Intent(this, BrowsePics.class);
        startActivity(browseIntent);
    }
}
package com.example.picstudiolab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ZoomInvitation extends AppCompatActivity {
    TextView link;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom_invitation);

        link = findViewById(R.id.link);

        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri zoomlink = Uri.parse("https://zoom.us/j/98769858501?pwd=K0NKQm5uUUpiYWVGaFlxMm5LcEZ2QT09");
                Intent zoomIntent = new Intent(Intent.ACTION_VIEW, zoomlink);
                startActivity(zoomIntent);

            }
        });
    }
}
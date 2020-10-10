package com.example.picstudiolab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Wedding extends AppCompatActivity {
    Button add;
    Button attach;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wedding);
       add= findViewById(R.id.add);
       attach = findViewById(R.id.attach);
       add.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intadd = new Intent(getApplicationContext(), BrowsePics.class);
               startActivity(intadd);
           }
       });
       attach.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intattach = new Intent(getApplicationContext(), ConfirmationPage.class);
               startActivity(intattach);
           }
       });
    }
}
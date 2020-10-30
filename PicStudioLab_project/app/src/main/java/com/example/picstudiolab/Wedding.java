package com.example.picstudiolab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Wedding extends AppCompatActivity {
    Button add;
    Button attach;

    int count4,count5,count6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wedding);

        FloatingActionButton add4 = findViewById(R.id.add4);
        FloatingActionButton add5 = findViewById(R.id.add5);
        FloatingActionButton add6 = findViewById(R.id.add6);

        final TextView addvalue4 = findViewById(R.id.addvalue4);
        final TextView addvalue5 = findViewById(R.id.addvalue5);
        final TextView addvalue6 = findViewById(R.id.addvalue6);

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

        add4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count4++;
                String num = (String.valueOf(count4));
                addvalue4.setText(num);
            }
        });

        add5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count5++;
                String num = (String.valueOf(count5));
                addvalue5.setText(num);
            }
        });

        add6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count6++;
                String num = (String.valueOf(count6));
                addvalue6.setText(num);
            }
        });
    }
}
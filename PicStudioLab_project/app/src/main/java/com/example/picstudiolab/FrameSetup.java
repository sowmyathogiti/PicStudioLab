package com.example.picstudiolab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class FrameSetup extends AppCompatActivity {

    int count1,count2,count3,count9;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_setup);

        Button cartbtn = findViewById(R.id.cartbtn);
        FloatingActionButton add1 = findViewById(R.id.add1);
        FloatingActionButton add2 = findViewById(R.id.add2);
        FloatingActionButton add3 = findViewById(R.id.add3);
        FloatingActionButton add9 = findViewById(R.id.add9);
        final TextView addvalue1 = (TextView) findViewById(R.id.addvalue1);
        final TextView addvalue2 = findViewById(R.id.addvalue2);
        final TextView addvalue3 = findViewById(R.id.addvalue3);
        final TextView addvalue9 = findViewById(R.id.addvalue9);

        cartbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               cartClick();
            }
        });

        add1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count1++;
                String num = (String.valueOf(count1));
                addvalue1.setText(num);
            }
        });

        add2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count2++;
                String num = (String.valueOf(count2));
                addvalue2.setText(num);
            }
        });

        add3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count3++;
                String num = (String.valueOf(count3));
                addvalue3.setText(num);
            }
        });

        add9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count9++;
                String num = (String.valueOf(count9));
                addvalue9.setText(num);
            }
        });
    }

    private void cartClick() {
        Intent browseIntent = new Intent(this, BrowsePics.class);
        startActivity(browseIntent);
    }
}
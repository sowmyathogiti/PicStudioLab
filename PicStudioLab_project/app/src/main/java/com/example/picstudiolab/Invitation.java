package com.example.picstudiolab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Invitation extends AppCompatActivity {
    Button addinv;
    Button zoom2;
    ArrayList<Order> order = new ArrayList<>();
    int count7,count8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invitation);

        FloatingActionButton add7 = findViewById(R.id.add7);
        FloatingActionButton add8 = findViewById(R.id.add8);
        FloatingActionButton minus4 = findViewById(R.id.minus4);
        FloatingActionButton minus5 = findViewById(R.id.minus5);

        final TextView addvalue7 = findViewById(R.id.addvalue7);
        final TextView addvalue8 = findViewById(R.id.addvalue8);

        addinv = findViewById(R.id.addinv);
        zoom2 = findViewById(R.id.zoom2);

        addinv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browseIntent = new Intent(getApplicationContext(), BrowsePics.class);
                if (count7 > 0) {
                    order.add(new Order("Invitation Frame 1", count7, 10.0));
                }
                if (count8 > 0) {
                    order.add(new Order("Invitation Frame 2", count8, 11.0));
                }

                browseIntent.putParcelableArrayListExtra("order", order);
                startActivity(browseIntent);
            }
        });
        zoom2.setOnClickListener(new View.OnClickListener() {
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
        minus4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count7--;
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
        minus5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count8--;
                String num = (String.valueOf(count8));
                addvalue8.setText(num);
            }
        });
    }
}
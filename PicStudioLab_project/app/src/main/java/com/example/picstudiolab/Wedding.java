package com.example.picstudiolab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Wedding extends AppCompatActivity {
    Button add;
//    Button attach;
ArrayList<Order> order = new ArrayList<>();
    int count4,count5,count6;
    TextView addvalue4,addvalue5,addvalue6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wedding);

        FloatingActionButton add4 = findViewById(R.id.add4);
        FloatingActionButton add5 = findViewById(R.id.add5);
        FloatingActionButton add6 = findViewById(R.id.add6);
        FloatingActionButton minus1 = findViewById(R.id.minus1);
        FloatingActionButton minus2 = findViewById(R.id.minus2);
        FloatingActionButton minus3 = findViewById(R.id.minus3);
        addvalue4 = findViewById(R.id.addvalue4);
        addvalue5 = findViewById(R.id.addvalue5);
        addvalue6 = findViewById(R.id.addvalue6);

        add= findViewById(R.id.addWed);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cartClick();
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
        minus1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count4 > 0) {
                    count4--;
                    String num = (String.valueOf(count4));
                    addvalue4.setText(num);
                }
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
        minus2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count5 > 0) {
                    count5--;
                    String num = (String.valueOf(count5));
                    addvalue5.setText(num);
                }
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
        minus3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count6 > 0) {
                    count6--;
                    String num = (String.valueOf(count6));
                    addvalue6.setText(num);
                }
            }
        });

    }

    private void cartClick() {
        if((count4 > 0) ||(count5 >0) || (count6>0)) {
            Intent browseIntent = new Intent(getApplicationContext(), BrowsePics.class);
            if (count4 > 0) {
                order.add(new Order("Regular Frame 1", count4, 10.0));
            }
            if (count5 > 0) {
                order.add(new Order("Regular Frame 2", count5, 11.0));
            }
            if (count6 > 0) {
                order.add(new Order("Regular Frame 3", count6, 12.0));
            }

            browseIntent.putParcelableArrayListExtra("order", order);
            startActivity(browseIntent);
        }
    }
}
package com.example.picstudiolab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;

public class PlacedOrder extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Intent i;
    TextView placed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placed_order);

        placed = findViewById(R.id.placedPrice);
        i = getIntent();

        Log.d("place order",i.getStringExtra("name"));

        placed.setText(i.getStringExtra("name")+", your order has been placed with order Id: "
                +i.getStringExtra("docId")+ "\nTotal price: "+
                i.getDoubleExtra("totalPrice",0) +
                "\nYour order will be ready by "+i.getStringExtra("dateValue")+" "+
                i.getStringExtra("timeValue"));
    }
}
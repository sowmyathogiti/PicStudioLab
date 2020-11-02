package com.example.picstudiolab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Payment extends AppCompatActivity {
    Button placeOrder;
    ArrayList<Order> order = new ArrayList<>();
    Intent i;
    Switch Take, delivery;
    EditText customerValue,dateValue,timeValue;
    CheckBox agree;
    FirebaseAuth mAuth;
    Double totalPrice = 0.0;
    Date date, time;
    String docId;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        placeOrder = findViewById(R.id.placeOrder);
        customerValue = findViewById(R.id.customerValue);
        dateValue = findViewById(R.id.dateValue);
        timeValue = findViewById(R.id.timeValue);
        Take = findViewById(R.id.Take);
        delivery = findViewById(R.id.delivery);
        agree = findViewById(R.id.agree);

        Take.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Take.setText("OFF");
                if(Take.getText() == "OFF"){
                    Take.setText("ON");
                }
            }
        });

        delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delivery.setText("OFF");
                if(delivery.getText() == "OFF"){
                    delivery.setText("ON");
                }
            }
        });
        try {
            date = new SimpleDateFormat("MM/dd/yyyy").parse(dateValue.getText().toString());
            time = new SimpleDateFormat("hh:mm").parse(timeValue.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        i = getIntent();
        order = i.getParcelableArrayListExtra("order");

        for(Order o : order){
            totalPrice += o.getPrice()*o.getNoOfSelected();
        }

        placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Map<String, Object> finalOrder = new HashMap<>();
                finalOrder.put("Billing Name",customerValue.getText().toString());
                finalOrder.put("Total Price",totalPrice);
                Log.d("URL in payment",i.getStringExtra("image"));
                finalOrder.put("Image",i.getStringExtra("image"));
                finalOrder.put("Date",dateValue.getText().toString());
                finalOrder.put("Time",timeValue.getText().toString());
                finalOrder.put("Take",Take.getText().toString());
                finalOrder.put("Delivery",delivery.getText().toString());

                db.collection("orders")
                        .add(finalOrder)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(getApplicationContext(),"Added the order to Database",Toast.LENGTH_SHORT).show();
                                docId = documentReference.getId();

                                Intent done = new Intent(getApplicationContext(), PlacedOrder.class);
                                done.putExtra("name",customerValue.getText().toString());
                                done.putExtra("docId",docId);
                                done.putExtra("totalPrice",totalPrice);
                                done.putExtra("dateValue",dateValue.getText().toString());
                                done.putExtra("timeValue",timeValue.getText().toString());
                                startActivity(done);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(),"Fail to add to Database",Toast.LENGTH_SHORT).show();
                            }
                        });

            }
        });
    }
}
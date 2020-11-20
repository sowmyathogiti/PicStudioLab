package com.example.picstudiolab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Account extends AppCompatActivity {

    EditText firstNameEditText, lastNameEditText, middleNameEditText, ageEditText, emailEditText, phoneEditText, passwordEditText, confirmPasswordEditText, addressEditText;
    String firstNameString, lastNameString, middleNameString, ageString, emailString="em", phoneString, passwordString="pa", confirmPasswordString, customerID, addressString;
    Button registerButton;
    RadioGroup radioGroup;
    String genderChoice;
    FirebaseAuth mAuth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        firstNameEditText = findViewById(R.id.Updfirstnamevalue);
        lastNameEditText = findViewById(R.id.Updlastnamevalue);
        middleNameEditText = findViewById(R.id.Updmiddlenamevalue);
        ageEditText = findViewById(R.id.Updagevalue);
        emailEditText = findViewById(R.id.emailvalueAccount);
        phoneEditText = findViewById(R.id.Updphonevalue);
        passwordEditText = findViewById(R.id.pwdvalue1Account);
        confirmPasswordEditText = findViewById(R.id.pwdvalue2);
        registerButton = findViewById(R.id.UpdBtn);
        radioGroup = findViewById(R.id.genderGroup);
        addressEditText = findViewById(R.id.CreateAddressValue);


        mAuth = FirebaseAuth.getInstance();


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = findViewById(checkedId);
                genderChoice = radioButton.getText().toString();
                Log.d("gender","gender: "+genderChoice);
            }
        });



        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstNameString = firstNameEditText.getText().toString();
                lastNameString = lastNameEditText.getText().toString();
                middleNameString = middleNameEditText.getText().toString();
                ageString = ageEditText.getText().toString();
                emailString = emailEditText.getText().toString();
                phoneString = phoneEditText.getText().toString();
                passwordString = passwordEditText.getText().toString();
                confirmPasswordString = confirmPasswordEditText.getText().toString();
                addressString = addressEditText.getText().toString();
                Log.d("email","email: "+emailString);
                Log.d("password","password: "+passwordString);

                mAuth.createUserWithEmailAndPassword(emailEditText.getText().toString(),passwordEditText.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            customerID = mAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = db.collection("customers").document(customerID);
                            Map<String, Object> customer = new HashMap<>();
                            customer.put("Name", firstNameString+" "+middleNameString+" "+lastNameString);
                            customer.put("Phone",phoneString);
                            customer.put("gender",genderChoice);
                            customer.put("Age",ageString);
                            customer.put("EmailID",emailString);
                            customer.put("Password",passwordString);
                            customer.put("id",customerID);
                            customer.put("address",addressString);
                            documentReference.set(customer).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(getApplicationContext(),"Registered Successfully",Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getApplicationContext(),"Not Registered Successfully",Toast.LENGTH_SHORT).show();
                                }
                            });

                            mAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if(task.isSuccessful()){
                                        Toast.makeText(getApplicationContext(),"Sent a verification E-mail",Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                    }
                                    else{
                                        Toast.makeText(getApplicationContext(),"Something went wrong",Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }
                    }
                });


            }

        });


    }

    private boolean validations() {
        return true;
    }

}
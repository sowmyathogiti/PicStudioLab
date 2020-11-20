package com.example.picstudiolab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class SentReset extends AppCompatActivity {
    FirebaseAuth mAuth;
    Intent resendint;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sent_reset);
        mAuth = FirebaseAuth.getInstance();
        resendint = getIntent();
        Button resend = findViewById(R.id.resend);

        resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resndClick();
            }
        });

    }

    private void resndClick() {
        Intent resendIntent = new Intent(this, MainActivity.class);
        startActivity(resendIntent);
        String mail = resendint.getStringExtra("mailvalue");

        mAuth.sendPasswordResetEmail(mail).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful()){
                    Toast.makeText(SentReset.this,"Password reset link is sent to the given Email Id",Toast.LENGTH_SHORT);
                }
                else{
                    Toast.makeText(SentReset.this,task.getException().getMessage(),Toast.LENGTH_SHORT);
                }
            }
        });

    }
}
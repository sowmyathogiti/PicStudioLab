package com.example.picstudiolab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPassword extends AppCompatActivity {

    FirebaseAuth mAuth;
    EditText mailvalue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        mAuth = FirebaseAuth.getInstance();
        mailvalue = findViewById(R.id.mailvalue);
        TextView back = findViewById(R.id.back);
        Button reset = findViewById(R.id.reset);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               backScreen();
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetScreen();
                mAuth.sendPasswordResetEmail(mailvalue.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if(task.isSuccessful()){
                            Toast.makeText(ResetPassword.this,"Password reset link is sent to the given Email Id",Toast.LENGTH_SHORT);
                        }
                        else{
                            Toast.makeText(ResetPassword.this,task.getException().getMessage(),Toast.LENGTH_SHORT);
                        }
                    }
                });
            }
        });
    }

    private void resetScreen() {
        Intent resetIntent = new Intent(this, SentReset.class);
        resetIntent.putExtra("mailvalue",mailvalue.getText().toString());
        startActivity(resetIntent);
    }

    private void backScreen() {
        Intent back = new Intent(this, MainActivity.class);
        startActivity(back);
    }

}

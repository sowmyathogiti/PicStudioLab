package com.example.picstudiolab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Account extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        Button accountcreate = findViewById(R.id.accountcreate);
//        EditText firstnamevalue = findViewById(R.id.firstnamevalue);
        accountcreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText firstnamevalue = findViewById(R.id.firstnamevalue);
                if(firstnamevalue.getText().toString().length()==0)
                    firstnamevalue.setError( "First name is required!" );
                else
                createAccount();
            }
        });
    }

    private void createAccount() {
        Intent int2 = new Intent(this,MainActivity.class);
        startActivity(int2);
    }
}
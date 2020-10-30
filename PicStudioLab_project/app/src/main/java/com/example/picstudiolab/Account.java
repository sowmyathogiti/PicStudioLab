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

                EditText lastnamevalue = findViewById(R.id.lastnamevalue);
                if(lastnamevalue.getText().toString().length()==0)
                    lastnamevalue.setError( "Last name is required!" );
                else
                    createAccount();

                EditText emailvalue = findViewById(R.id.emailvalue);
                if(emailvalue.getText().toString().length()==0)
                    emailvalue.setError( "Email Id is required!" );
                else
                    createAccount();

                EditText phonevalue = findViewById(R.id.phonevalue);
                if(phonevalue.getText().toString().length()==0)
                    phonevalue.setError( "Phone number is required!" );
                else
                    createAccount();

                EditText pwdvalue1 = findViewById(R.id.pwdvalue1);
                EditText pwdvalue2 = findViewById(R.id.pwdvalue2);

                if(!pwdvalue1.getText().toString().equals(pwdvalue2.getText().toString())){
                    pwdvalue1.setError( "Passwords in two fields should match!" );
                    pwdvalue2.setError( "Passwords in two fields should match!" );}
                else
                    createAccount();
            }
        });
    }

    private void createAccount() {
        Intent int2 = new Intent(this,Account.class);
        startActivity(int2);
    }
}
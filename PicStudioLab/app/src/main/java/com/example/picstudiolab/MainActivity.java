package com.example.picstudiolab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView signup = findViewById(R.id.signup);
        Button fb = findViewById(R.id.fb);
        Button google = findViewById(R.id.google);
        TextView help = findViewById(R.id.help);
        Button login = findViewById(R.id.Login);

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                helpForgot();
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSingUp();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLogin();
            }
        });

        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri facebook = Uri.parse("https://www.facebook.com/login/");
                Intent fbIntent = new Intent(Intent.ACTION_VIEW, facebook);
                startActivity(fbIntent);
            }
        });

        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri googleLink = Uri.parse("https://accounts.google.com/signin/v2/identifier?continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&service=mail&sacu=1&rip=1&flowName=GlifWebSignIn&flowEntry=ServiceLogin");
                Intent googleIntent = new Intent(Intent.ACTION_VIEW, googleLink);
                startActivity(googleIntent);
            }
        });
    }

    private void openLogin() {
        Intent loginIntent = new Intent(this, FrameSetup.class);
        startActivity(loginIntent);
    }

    private void helpForgot() {
        Intent helpIntent = new Intent(this,ResetPassword.class);
        startActivity(helpIntent);
    }

    private void openSingUp() {
        Intent int1 = new Intent(this, Account.class);
        startActivity(int1);
    }


}

package com.example.picstudiolab;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;


public class MainActivity extends AppCompatActivity {

    Button framebtn;
    EditText UserName,Password;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView signup = findViewById(R.id.signup);
        Button fb = findViewById(R.id.fb);
        Button google = findViewById(R.id.google);
        TextView help = findViewById(R.id.help);
        Button login = findViewById(R.id.Login);
        framebtn = findViewById(R.id.framebtn);
        UserName = findViewById(R.id.UserName);
        Password = findViewById(R.id.Password);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (validations()) {

                        mAuth.signInWithEmailAndPassword(UserName.getText().toString(), Password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {
                                    if (mAuth.getCurrentUser().isEmailVerified()) {
                                        Toast.makeText(getApplicationContext(), "user: "+mAuth.getCurrentUser().getUid(), Toast.LENGTH_SHORT).show();
                                        Log.d("auth","user: "+mAuth.getCurrentUser().getUid());
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(getApplicationContext(), "Wrong credentials", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                }
                catch (Exception e){}
            }
        });

//
//        if(UserName.getText().toString().length()==0)
//            UserName.setError( "Email Id is required!" );
//        else
//            createLogin();
//
//        if(Password.getText().toString().length()==0)
//            Password.setError( "Password Id is required!" );
//        else
//            createLogin();

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
//        login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                openLogin();
//            }
//        });

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

    private void createLogin() {
        Intent main = new Intent(this,MainActivity.class);
        startActivity(main);
    }

    private void frame1() {
        Intent frame1Intent = new Intent(this, Home.class);
        startActivity(frame1Intent);
    }

    private void openLogin() {
        Intent loginIntent = new Intent(this, Home.class);
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


    private boolean validations() {
//        return true;
        if(UserName.getText().toString().length()==0 && Password.getText().toString().length()==0) {
            UserName.setError("Email Id is required!");
            Password.setError("Password is required");
            return false;
        }
        else
        {
            return true;
        }

    }
}

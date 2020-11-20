package com.example.picstudiolab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class UpdateProfile extends AppCompatActivity {

    Button UpdBtn;
    EditText Updfirstnamevalue,Updmiddlenamevalue,Updlastnamevalue,Updagevalue,Updphonevalue,UpdNewAddressValue;
    String UpdfirstnamevalueString,UpdlastnamevalueString,UpdmiddlenamevalueString,UpdagevalueString,UpdphonevalueString,UpdNewAddressValueString;

    FirebaseAuth mAuth;
    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        Updfirstnamevalue = findViewById(R.id.Updfirstnamevalue);
        Updmiddlenamevalue = findViewById(R.id.Updmiddlenamevalue);
        Updlastnamevalue = findViewById(R.id.Updlastnamevalue);
        Updagevalue = findViewById(R.id.Updagevalue);
        Updphonevalue = findViewById(R.id.Updphonevalue);
        UpdNewAddressValue = findViewById(R.id.UpdNewAddressValue);

        UpdBtn = findViewById(R.id.UpdBtn);
        UpdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdfirstnamevalueString = Updfirstnamevalue.getText().toString();
                UpdmiddlenamevalueString = Updmiddlenamevalue.getText().toString();
                UpdlastnamevalueString = Updlastnamevalue.getText().toString();
                UpdagevalueString = Updagevalue.getText().toString();
                UpdphonevalueString = Updphonevalue.getText().toString();
                UpdNewAddressValueString = UpdNewAddressValue.getText().toString();

                Log.d("email","email: "+UpdphonevalueString);
                DocumentReference documentReference = db.collection("customers").document(mAuth.getCurrentUser().getUid());
                Map<String, Object> map = new HashMap<>();
                map.put("Name",UpdfirstnamevalueString+" "+UpdmiddlenamevalueString+" "+UpdlastnamevalueString);
                map.put("Phone",UpdphonevalueString);
                map.put("Age",UpdagevalueString);
                map.put("address",UpdNewAddressValueString);
                documentReference.update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        //mAuth.signOut();
                        startActivity(new Intent(getApplicationContext(),Home.class));
                        Toast.makeText(getApplicationContext(),"updated",Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }
}
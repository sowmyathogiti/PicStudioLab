package com.example.picstudiolab;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;

import java.util.Collections;
import java.util.Set;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;

public class BrowsePics extends AppCompatActivity {

    Intent browseIntent;
    String pathFile= "";
    TextView fileNames;
    Button attach;
    Uri filePath;
    ArrayList<Order> order = new ArrayList<>();
    StorageReference storageReference, ref;
    FirebaseStorage storage;
    String imageUrl = "";
    //    public static final int REQUEST_CODE = 10;
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_pics);

        Button browse1 =findViewById(R.id.browse1);
//        Button browse2 =findViewById(R.id.browse2);
        Button checkout =findViewById(R.id.checkout);
        fileNames = findViewById(R.id.fileNames);
        attach = findViewById(R.id.attach);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference("Images");

        i = getIntent();
        order = i.getParcelableArrayListExtra("order");


        Log.d("order",order.toString());
        attach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intattach = new Intent(getApplicationContext(), ConfirmationPage.class);
                startActivity(intattach);
            }
        });

        browse1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectFile();

            }
        });
//
//        browse2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                browseIntent = new Intent(Intent.ACTION_GET_CONTENT);
//                browseIntent.setType("*/*");
//                startActivityForResult(browseIntent,20);
//            }
//        });

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    checkoutIntent();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private void uploadFile() {

        ref = storageReference.child(System.currentTimeMillis()+"."+getExtension(filePath));
        ref.putFile(filePath)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        Toast.makeText(getApplicationContext(),"uploaded the image",Toast.LENGTH_SHORT).show();

                        ref.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                                imageUrl = task.getResult().toString();
                                Log.d("URL",imageUrl);
                            }
                        });
                    }
                });
    }

    private String getExtension(Uri uri) {

        ContentResolver cr = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(cr.getType(uri));
    }

    private void checkoutIntent() throws InterruptedException {
        uploadFile();
        TimeUnit.SECONDS.sleep(3);
        Intent checkoutInt = new Intent(this, Payment.class);
        checkoutInt.putParcelableArrayListExtra("order",order);
        checkoutInt.putExtra("image",imageUrl);
        startActivity(checkoutInt);
    }

    private void selectFile() {
        browseIntent = new Intent(Intent.ACTION_GET_CONTENT);
        browseIntent.setType("image/*");
        startActivityForResult(browseIntent,1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1
                && resultCode == RESULT_OK
                && data !=null
                && data.getData() != null){

            filePath = data.getData();
        }
//        switch (requestCode) {
//            case 10:
//                if (resultCode == RESULT_OK) {
//                    String path = data.getData().getPath().toString();
//                    pathFile = pathFile.concat(path) + "\n";
//                    fileNames.setText(pathFile);
//                }
//                break;
//
//        }
    }

    public static class PlacedOrder extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_placed_order);
        }
    }
}
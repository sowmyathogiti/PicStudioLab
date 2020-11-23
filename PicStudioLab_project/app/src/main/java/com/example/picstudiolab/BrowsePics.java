package com.example.picstudiolab;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.squareup.picasso.Picasso;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.Set;

import android.widget.ImageView;
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

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    Intent browseIntent;
    String pathFile= "";
    TextView reviewFiles;
    Button attach;

    Uri filePath;
    ArrayList<Order> order = new ArrayList<>();
    StorageReference storageReference, ref;
    FirebaseStorage storage;
    String imageUrl = "";
    Bitmap bitmap;
    String dummyString="";

    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_pics);

        Button browse1 =findViewById(R.id.browse1);

        Button checkout =findViewById(R.id.checkout);
        reviewFiles = findViewById(R.id.reviewFiles);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference("Images");

        i = getIntent();
        order = i.getParcelableArrayListExtra("order");


        Log.d("order",order.toString());

        browse1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectFile();

            }
        });


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

    private void getPDF() {

            PdfDocument pdfdocument = new PdfDocument();
            PdfDocument.PageInfo pi = new PdfDocument.PageInfo.Builder(bitmap.getWidth(),bitmap.getHeight(),1).create();
            PdfDocument.Page page = pdfdocument.startPage(pi);

            Canvas  canvas = page.getCanvas();
            Paint paint = new Paint();
            paint.setColor(Color.parseColor("#FFFFFF"));
            canvas.drawPaint(paint);

            bitmap = Bitmap.createScaledBitmap(bitmap,bitmap.getWidth(),bitmap.getHeight(),true);
            paint.setColor(Color.BLUE);
            canvas.drawBitmap(bitmap,0,0,null);
            pdfdocument.finishPage(page);

            verifyStoragePermissions(this);
            File root = new File(Environment.getExternalStorageDirectory(),"/Frame.pdf");
            boolean success = true;
            if(!root.exists()){
                success = root.mkdir();
            }
            Log.d("suc: ",success + "");

            try{
                FileOutputStream fileOutputStream = new FileOutputStream(root);
                pdfdocument.writeTo(fileOutputStream);
                Log.d("path: ",Environment.getExternalStorageDirectory().getAbsolutePath());
            }catch (IOException e){
                e.printStackTrace();
            }
            pdfdocument.close();


    }

    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
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
        verifyStoragePermissions(this);
        browseIntent = new Intent(Intent.ACTION_GET_CONTENT);
        browseIntent.setType("*/*");
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
            ///pdf part
            dummyString = dummyString + data.getData().getPath()+"\n";
            reviewFiles.setText(dummyString);

        }

    }

    public static class PlacedOrder extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_placed_order);
        }
    }
}
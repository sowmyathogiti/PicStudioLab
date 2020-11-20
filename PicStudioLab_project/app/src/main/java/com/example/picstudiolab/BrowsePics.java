package com.example.picstudiolab;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
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

    Intent browseIntent;
    String pathFile= "";
    TextView reviewFiles;
    Button attach;
    ImageView ImageDisp;
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
//        fileNames = findViewById(R.id.fileNames);
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
//            reviewFiles.setText(data.getDataString());
//            String[] filepath = {MediaStore.Images.Media.DATA};
//            Cursor cursor = getContentResolver().query(filePath,filepath,null,null,null);
//            cursor.moveToFirst();
//            int columnIndex = cursor.getColumnIndex(filepath[0]);
//            String myPath = cursor.getString(columnIndex);
//            cursor.close();
//
//            Bitmap bitmap = BitmapFactory.decodeFile(myPath);
//            ImageDisp.setImageBitmap(bitmap);
//
//            PdfDocument pdfdocument = new PdfDocument();
//            PdfDocument.PageInfo pi = new PdfDocument.PageInfo.Builder(bitmap.getWidth(),bitmap.getHeight(),1).create();
//            PdfDocument.Page page = pdfdocument.startPage(pi);
//
//            Canvas  canvas = page.getCanvas();
//            Paint paint = new Paint();
//            paint.setColor(Color.parseColor("#FFFFFF"));
//            canvas.drawPaint(paint);
//
//            bitmap = Bitmap.createScaledBitmap(bitmap,bitmap.getWidth(),bitmap.getHeight(),true);
//            paint.setColor(Color.BLUE);
//            canvas.drawBitmap(bitmap,0,0,null);
//            pdfdocument.finishPage(page);
//
//            //saving image
//
//            File root = new File(Environment.getExternalStorageDirectory(),"PDF Folder");
//            if(!root.exists()){
//                root.mkdir();
//            }
//            File file = new File(root,"picture pdf");
//            try{
//                FileOutputStream fileOutputStream = new FileOutputStream(file);
//                pdfdocument.writeTo(fileOutputStream);
//                }catch (IOException e){
//                e.printStackTrace();
//            }
//            pdfdocument.close();
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
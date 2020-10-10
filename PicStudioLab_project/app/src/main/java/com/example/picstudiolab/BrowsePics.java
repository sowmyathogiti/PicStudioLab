package com.example.picstudiolab;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BrowsePics extends AppCompatActivity {

    Intent browseIntent;
//    public static final int REQUEST_CODE = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_pics);

        Button browse1 =findViewById(R.id.browse1);
        Button browse2 =findViewById(R.id.browse2);
        Button checkout =findViewById(R.id.checkout);
        browse1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                browseIntent = new Intent(Intent.ACTION_GET_CONTENT);
                browseIntent.setType("*/*");
                startActivityForResult(browseIntent,10);
            }
        });

        browse2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                browseIntent = new Intent(Intent.ACTION_GET_CONTENT);
                browseIntent.setType("*/*");
                startActivityForResult(browseIntent,20);
            }
        });

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkoutIntent();
            }
        });
    }

    private void checkoutIntent() {
        Intent checkoutInt = new Intent(this, Payment.class);
        startActivity(checkoutInt);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

//        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 10:
                if (requestCode == RESULT_OK) {
                    String path = data.getData().getPath();
                }
                break;
            case 20:
                if (requestCode == RESULT_OK) {
                    String path = data.getData().getPath();
                }
                break;
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
package com.example.picstudiolab;



import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(true);



        LatLng sydney = new LatLng(47.8302454, -122.2766181);
        mMap.addMarker(new MarkerOptions().position(new LatLng(47.8302454, -122.2766181)).title("PicStudioLab")).showInfoWindow();
        mMap.addMarker(new MarkerOptions().position(new LatLng(47.6096918, -122.6292841)).title("PicStudioLab")).showInfoWindow();
        mMap.addMarker(new MarkerOptions().position(new LatLng(48.2377742, -122.3311145)).title("PicStudioLab")).showInfoWindow();
        mMap.addMarker(new MarkerOptions().position(new LatLng(47.4540536, -120.3414017)).title("PicStudioLab")).showInfoWindow();
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 7));
    }

    public void createMarker(String title, Double latitude, Double longitude) {
        mMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)).title(title)).showInfoWindow();
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                return true;
            }
        });
    }
}
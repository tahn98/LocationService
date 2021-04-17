package com.example.locationtrackerdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import static com.example.locationtrackerdemo.Constants.REQUEST_PERMISSION_CODE;

public class MainActivity extends AppCompatActivity {
    private SwitchCompat switchCompat;
    private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        switchCompat = findViewById(R.id.swLocation);
        locationManager = LocationManager.getInstance(this);

        //set properties

        updateLocation();

        switchCompat.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
            }else{
            }
        });
    }

    private void updateLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationManager.startLocationUpdate();
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_BACKGROUND_LOCATION}, REQUEST_PERMISSION_CODE);
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_PERMISSION_CODE);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case REQUEST_PERMISSION_CODE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    updateLocation();
                } else {
                    finish();
                }
        }
    }
}
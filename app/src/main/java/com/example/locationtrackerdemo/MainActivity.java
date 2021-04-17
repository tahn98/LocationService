package com.example.locationtrackerdemo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import com.example.locationtrackerdemo.services.LocationReceiver;
import com.example.locationtrackerdemo.services.LocationTracker;

public class MainActivity extends AppCompatActivity {


    private SwitchCompat switchCompat;
    LocationTracker locationTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        switchCompat = findViewById(R.id.swLocation);
        locationTracker = new LocationTracker().setReceiver(new LocationReceiver()).start(this);
    }
}
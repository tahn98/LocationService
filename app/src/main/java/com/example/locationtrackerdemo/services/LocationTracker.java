package com.example.locationtrackerdemo.services;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import static com.example.locationtrackerdemo.utils.Utils.ACTION_BROADCAST;

public class LocationTracker {
    private static final int PERMISSIONS_FINE_LOCATION = 99;
    private static final int UPDATE_INTERVAL = 1;
    private static final int FAST_INTERVAL = 1;

    private LocationReceiver receiver;

    public LocationTracker() { }

    public LocationTracker setReceiver(LocationReceiver receiver){
        this.receiver = receiver;
        return this;
    }

    public LocationTracker start(Context context){
//        checkPermissions(context, appCompatActivity);
        startLocationService(context);

        if(receiver != null){
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(ACTION_BROADCAST);
            context.registerReceiver(this.receiver, intentFilter);
        }
        return this;
    }


    public void checkPermissions(Context context, AppCompatActivity appCompatActivity){
        if(ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){

        }else{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                appCompatActivity.requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_FINE_LOCATION);
            }
        }
    }

    public void startLocationService(Context context){
        Intent serviceIntent = new Intent(context, LocationService.class);
        context.startService(serviceIntent);
    }

}

package com.example.locationtrackerdemo;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.MainThread;
import androidx.annotation.RequiresApi;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.util.concurrent.TimeUnit;

import static android.Manifest.permission.ACCESS_BACKGROUND_LOCATION;
import static com.example.locationtrackerdemo.Constants.ACTION_PROCESS_UPDATE;

public class LocationManager {

    private Context context;
    @SuppressLint("StaticFieldLeak")
    public static LocationManager locationManager = null;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private LocationRequest locationRequest = LocationRequest.create();
    private PendingIntent locationUpdatePendingIntent;

    public static LocationManager getInstance(Context context){
        if(locationManager == null){
            locationManager = new LocationManager(context);
        }
        return locationManager;
    }

    public LocationManager(Context context){
        this.context = context;
        init();
    }

    public void init(){
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context);
        locationRequest.setInterval(TimeUnit.SECONDS.toMillis(1));
        locationRequest.setFastestInterval(TimeUnit.SECONDS.toMillis(1));
        locationRequest.setMaxWaitTime(TimeUnit.MINUTES.toMillis(5));
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    @MainThread
    public void startLocationUpdate(){
        Intent intent = new Intent(context, LocationUpdateBroadcastReceiver.class);
        intent.setAction(ACTION_PROCESS_UPDATE);
        locationUpdatePendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        if(!Utils.hasPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)) return;
        try{
            fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationUpdatePendingIntent);
        }catch (SecurityException securityException){
            throw securityException;
        }
    }

    @MainThread
    public void stopLocationUpdate(){
        fusedLocationProviderClient.removeLocationUpdates(locationUpdatePendingIntent);
    }
}

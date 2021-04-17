package com.example.locationtrackerdemo.services;

import android.Manifest;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import static com.example.locationtrackerdemo.utils.Utils.ACTION_BROADCAST;
import static com.example.locationtrackerdemo.utils.Utils.DATA_BROADCAST;
import static com.example.locationtrackerdemo.utils.Utils.PERMISSIONS_FINE_LOCATION;

public class LocationService extends Service {

    FusedLocationProviderClient fusedLocationProviderClient;
    private LocationRequest locationRequest;
    LocationCallback locationCallback;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        initLocationRequest();
        return START_STICKY;
    }

    public void initLocationRequest() {
        locationRequest = LocationRequest.create();
        locationRequest = LocationRequest.create();
        locationRequest.setInterval(1000 * 1);
        locationRequest.setFastestInterval(1000 * 1);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                super.onLocationResult(locationResult);

                Location location = locationResult.getLastLocation();
                sendLocationBroadcast(location);
            }

            @Override
            public void onLocationAvailability(@NonNull LocationAvailability locationAvailability) {
                super.onLocationAvailability(locationAvailability);
            }
        };

        updateGps();
    }

    private void updateGps() {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, null);
        }
    }

    private void sendLocationBroadcast(Location location){
        Intent intent = new Intent();
        intent.setAction(ACTION_BROADCAST);
        intent.putExtra(DATA_BROADCAST, location);
        sendBroadcast(intent);
    }
}

package com.example.locationtrackerdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.util.Log;

import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationResult;

import java.util.List;

import static com.example.locationtrackerdemo.Constants.ACTION_PROCESS_UPDATE;
import static com.example.locationtrackerdemo.Constants.TAG;

/**
 * Handling location update
 * PendingIntent and getBroadcast should be used when requesting location in app background
 */

public class LocationUpdateBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction() == ACTION_PROCESS_UPDATE){
//            Boolean isLocationAvailable = LocationAvailability.extractLocationAvailability(intent).isLocationAvailable();
//            if(!isLocationAvailable){
//                Log.d(TAG, "Location services are no longer available");
//            }

            if(LocationResult.extractResult(intent) != null){
                List<Location> location = LocationResult.extractResult(intent).getLocations();
                Log.d(TAG, location.toString());
            }
        }
    }
}

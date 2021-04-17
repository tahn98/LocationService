package com.example.locationtrackerdemo.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.util.Log;

import static com.example.locationtrackerdemo.utils.Utils.ACTION_BROADCAST;
import static com.example.locationtrackerdemo.utils.Utils.DATA_BROADCAST;

public class LocationReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent != null && intent.getAction().equals(ACTION_BROADCAST)){
            Location locationData = (Location) intent.getParcelableExtra(DATA_BROADCAST);
            Log.d("Location: ", "Latitude: " + locationData.getLatitude() + "Longitude:" + locationData.getLongitude());
        }
    }
}

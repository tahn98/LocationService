package com.example.locationtrackerdemo;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;

public class Utils {
    public static boolean hasPermission(Context context, String permission){
        if(permission.equals(Manifest.permission.ACCESS_BACKGROUND_LOCATION) && Build.VERSION.SDK_INT < Build.VERSION_CODES.Q){
            return true;
        }
        return ActivityCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }
}

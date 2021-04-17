package com.example.locationtrackerdemo.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.util.Log;

public class Utils {
    public static final String ACTION_BROADCAST = "broadcast.action.location";
    public static final String DATA_BROADCAST = "broadcast.data";
    public static final String TAHN = "com.tahn";
    public static final int PERMISSIONS_FINE_LOCATION = 99;

    public static boolean isServiceRunning(Context context, Class serviceClass) {
        if (context != null) {
            Log.d("", "contextIsNotNull: ");
        }
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (manager == null) {
            return false;
        }
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}

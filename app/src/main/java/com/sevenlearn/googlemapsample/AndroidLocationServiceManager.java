package com.sevenlearn.googlemapsample;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

/**
 * Created by user on 2/4/2018.
 */

public class AndroidLocationServiceManager {
    public static final int LOCATION_PERMISSION_REQUEST = 1001;
    private LocationManager locationManager;
    private Context context;
    private LocationListener locationListener;

    public AndroidLocationServiceManager(Context context, LocationListener locationListener) {
        this.locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        this.locationListener = locationListener;
        this.context = context;
    }

    public void requestLocationUpdate() {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (context instanceof Activity) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    ((Activity) context).requestPermissions(new String[]{
                            Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION,
                    }, LOCATION_PERMISSION_REQUEST);
                }
            }
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 5, locationListener);
    }

    public void handleRequestPermissionResult(int requestCode, String[] permission, int[] grantResults) {
        if (requestCode == LOCATION_PERMISSION_REQUEST) {
            if (grantResults[0] == 0) {
                requestLocationUpdate();
            } else {
                Toast.makeText(context, "You must grant permissions", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

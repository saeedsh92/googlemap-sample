package com.sevenlearn.googlemapsample;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import static android.telephony.CellLocation.requestLocationUpdate;
import static com.sevenlearn.googlemapsample.AndroidLocationServiceManager.LOCATION_PERMISSION_REQUEST;

/**
 * Created by user on 2/4/2018.
 */

public class PlayServiceLocationApiManager {
    private Context context;
    private GoogleApiClient googleApiClient;
    private LocationListener locationListener;

    public PlayServiceLocationApiManager(Context context, GoogleApiClient googleApiClient, LocationListener locationListener) {
        this.context = context;
        this.googleApiClient = googleApiClient;
        this.locationListener = locationListener;
    }

    public void requestLocationUpdates() {
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(1000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setFastestInterval(0);

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
        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient,
                locationRequest, locationListener);
    }

    public void handleRequestPermissionResult(int requestCode, String[] permission, int[] grantResults) {
        if (requestCode == LOCATION_PERMISSION_REQUEST) {
            if (grantResults[0] == 0) {
                requestLocationUpdates();
            } else {
                Toast.makeText(context, "You must grant permissions", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void cancelLocationUpdates() {
        LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, locationListener);
    }
}

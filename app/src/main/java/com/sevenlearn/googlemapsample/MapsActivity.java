package com.sevenlearn.googlemapsample;

import android.app.ProgressDialog;
import android.location.Location;
import android.location.LocationListener;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.JointType;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.sevenlearn.googlemapsample.direction.DirectionApiResponse;
import com.sevenlearn.googlemapsample.direction.Step;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener, GoogleApiClient.ConnectionCallbacks
        , GoogleApiClient.OnConnectionFailedListener, DirectionApiService.DirectionApiCallback {
    private static final String TAG = "MapsActivity";
    private GoogleMap googleMap;
    private Marker marker;
    private AndroidLocationServiceManager locationServiceManager;
    private GoogleApiClient googleApiClient;
    private PlayServiceLocationApiManager playServiceLocationApiManager;
    private View goToMyLocationButton;
    private Location currentLocation;
    private DirectionApiService directionApiService;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        directionApiService = new DirectionApiService(this, this);
        locationServiceManager = new AndroidLocationServiceManager(this, this);
        locationServiceManager.requestLocationUpdate();
        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        playServiceLocationApiManager = new PlayServiceLocationApiManager(this,
                googleApiClient, new com.google.android.gms.location.LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                currentLocation = location;
                Log.d(TAG, "onLocationChanged() called with: location = [" + location + "]");
            }
        });

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        goToMyLocationButton = findViewById(R.id.iv_maps_goToMyLocation);
        goToMyLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentLocation == null) return;

                CameraPosition cameraPosition = new CameraPosition.Builder()
                        .target(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()))
                        .bearing(90)
                        .zoom(17)
                        .tilt(70)
                        .build();

                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition), 1000, null);
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        googleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        playServiceLocationApiManager.cancelLocationUpdates();
        googleApiClient.disconnect();
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
    }

    @Override
    public void onLocationChanged(Location location) {
        if (googleMap == null)
            return;

        LatLng userLocationLatLng = new LatLng(location.getLatitude(), location.getLongitude());
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(userLocationLatLng)
                .zoom(18)
                .bearing(0)
                .tilt(0)
                .build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition), 2000, null);
        if (marker == null) {
            marker = googleMap.addMarker(new MarkerOptions().position(userLocationLatLng).title("Your Location"));
        } else {
            marker.setPosition(userLocationLatLng);
        }

        googleMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng destination) {
                if (currentLocation != null) {
                    LatLng origin = new LatLng(currentLocation.getLatitude(),
                            currentLocation.getLongitude());
                    directionApiService.getDirectionService(origin, destination);
                    progressDialog = new ProgressDialog(MapsActivity.this);
                    progressDialog.setTitle("در حال مسیریابی");
                    progressDialog.setIndeterminate(true);
                    progressDialog.setCancelable(false);
                    progressDialog.show();
                }
            }
        });

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        locationServiceManager.handleRequestPermissionResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        playServiceLocationApiManager.requestLocationUpdates();
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onResponse(final DirectionApiResponse response) {
        progressDialog.hide();
        if (googleMap != null && response.getRoutes() != null && response.getRoutes().size() > 0) {
            if (response.getRoutes().get(0).getLegs() != null &&
                    response.getRoutes().get(0).getLegs().size() > 0) {
                List<LatLng> latLngs = new ArrayList<>();
                for (int i = 0; i < response.getRoutes().get(0).getLegs().get(0).getSteps().size(); i++) {
                    Step step = response.getRoutes().get(0).getLegs().get(0).getSteps().get(i);
                    latLngs.addAll(decodePoly(step.getPolyline().getPoints()));
                }

                googleMap.addPolyline(new PolylineOptions()
                        .addAll(decodePoly(response.getRoutes().get(0).getOverviewPolyline().getPoints()))
                        .jointType(JointType.ROUND)
                        .color(ContextCompat.getColor(MapsActivity.this, R.color.colorAccent))
                        .width(30));
            }

            Button button = findViewById(R.id.button_map_showTripInfo);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TripInfoFragment tripInfoFragment = TripInfoFragment.newInstance(
                            response.getRoutes().get(0).getLegs().get(0).getDistance().getText(),
                            response.getRoutes().get(0).getLegs().get(0).getDuration().getText()

                    );
                    tripInfoFragment.show(getSupportFragmentManager(), null);
                }
            });
        }
    }

    @Override
    public void onError(String message) {
        progressDialog.hide();
        Toast.makeText(MapsActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    public static List<LatLng> decodePoly(String encoded) {

        List<LatLng> poly = new ArrayList<LatLng>();
        int index = 0, len = encoded.length();
        int lat = 0, lng = 0;

        while (index < len) {
            int b, shift = 0, result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            LatLng p = new LatLng((((double) lat / 1E5)),
                    (((double) lng / 1E5)));
            poly.add(p);
        }

        return poly;
    }
}

package com.sevenlearn.googlemapsample;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sevenlearn.googlemapsample.direction.DirectionApiResponse;

import org.json.JSONObject;

/**
 * Created by user on 2/8/2018.
 */

public class DirectionApiService {
    private Context context;
    private RequestQueue requestQueue;
    private DirectionApiCallback directionApiCallback;

    public DirectionApiService(Context context, DirectionApiCallback directionApiCallback) {
        this.context = context;
        this.directionApiCallback = directionApiCallback;
    }

    public void getDirectionService(LatLng origin, LatLng destination) {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                "https://maps.googleapis.com/maps/api/directions/json?"
                        + "mode=DRIVING&" + "origin=" + origin.latitude + "," + origin.longitude +
                        "&alternatives=false" + "&destination=" + destination.latitude +
                        "," + destination.longitude + "&units=metric&language=fa", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Gson gson = new Gson();
                    DirectionApiResponse directionApiResponse = gson.fromJson(response.toString(),
                            new TypeToken<DirectionApiResponse>() {
                            }.getType());
                    directionApiCallback.onResponse(directionApiResponse);
                } catch (Exception e) {
                    directionApiCallback.onError(e.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                directionApiCallback.onError(error.toString());
            }
        });
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context);
            requestQueue.add(request);
        } else
            requestQueue.add(request);
    }

    public interface DirectionApiCallback {
        void onResponse(DirectionApiResponse response);

        void onError(String message);
    }
}

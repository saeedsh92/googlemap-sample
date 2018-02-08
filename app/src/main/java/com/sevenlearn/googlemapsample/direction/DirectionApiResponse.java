
package com.sevenlearn.googlemapsample.direction;

import java.util.List;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class DirectionApiResponse {

    @SerializedName("geocoded_waypoints")
    private List<GeocodedWaypoint> GeocodedWaypoints;
    @SerializedName("routes")
    private List<Route> Routes;
    @SerializedName("status")
    private String Status;

    public List<GeocodedWaypoint> getGeocodedWaypoints() {
        return GeocodedWaypoints;
    }

    public void setGeocodedWaypoints(List<GeocodedWaypoint> geocodedWaypoints) {
        GeocodedWaypoints = geocodedWaypoints;
    }

    public List<Route> getRoutes() {
        return Routes;
    }

    public void setRoutes(List<Route> routes) {
        Routes = routes;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

}

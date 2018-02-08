
package com.sevenlearn.googlemapsample.direction;

import java.util.List;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Route {

    @SerializedName("bounds")
    private com.sevenlearn.googlemapsample.direction.Bounds Bounds;
    @SerializedName("copyrights")
    private String Copyrights;
    @SerializedName("legs")
    private List<Leg> Legs;
    @SerializedName("overview_polyline")
    private com.sevenlearn.googlemapsample.direction.OverviewPolyline OverviewPolyline;
    @SerializedName("summary")
    private String Summary;
    @SerializedName("warnings")
    private List<Object> Warnings;
    @SerializedName("waypoint_order")
    private List<Long> WaypointOrder;

    public com.sevenlearn.googlemapsample.direction.Bounds getBounds() {
        return Bounds;
    }

    public void setBounds(com.sevenlearn.googlemapsample.direction.Bounds bounds) {
        Bounds = bounds;
    }

    public String getCopyrights() {
        return Copyrights;
    }

    public void setCopyrights(String copyrights) {
        Copyrights = copyrights;
    }

    public List<Leg> getLegs() {
        return Legs;
    }

    public void setLegs(List<Leg> legs) {
        Legs = legs;
    }

    public com.sevenlearn.googlemapsample.direction.OverviewPolyline getOverviewPolyline() {
        return OverviewPolyline;
    }

    public void setOverviewPolyline(com.sevenlearn.googlemapsample.direction.OverviewPolyline overviewPolyline) {
        OverviewPolyline = overviewPolyline;
    }

    public String getSummary() {
        return Summary;
    }

    public void setSummary(String summary) {
        Summary = summary;
    }

    public List<Object> getWarnings() {
        return Warnings;
    }

    public void setWarnings(List<Object> warnings) {
        Warnings = warnings;
    }

    public List<Long> getWaypointOrder() {
        return WaypointOrder;
    }

    public void setWaypointOrder(List<Long> waypointOrder) {
        WaypointOrder = waypointOrder;
    }

}

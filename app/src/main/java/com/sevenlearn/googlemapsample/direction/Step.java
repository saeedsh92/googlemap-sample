
package com.sevenlearn.googlemapsample.direction;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Step {

    @SerializedName("distance")
    private com.sevenlearn.googlemapsample.direction.Distance Distance;
    @SerializedName("duration")
    private com.sevenlearn.googlemapsample.direction.Duration Duration;
    @SerializedName("end_location")
    private com.sevenlearn.googlemapsample.direction.EndLocation EndLocation;
    @SerializedName("html_instructions")
    private String HtmlInstructions;
    @SerializedName("polyline")
    private com.sevenlearn.googlemapsample.direction.Polyline Polyline;
    @SerializedName("start_location")
    private com.sevenlearn.googlemapsample.direction.StartLocation StartLocation;
    @SerializedName("travel_mode")
    private String TravelMode;

    public com.sevenlearn.googlemapsample.direction.Distance getDistance() {
        return Distance;
    }

    public void setDistance(com.sevenlearn.googlemapsample.direction.Distance distance) {
        Distance = distance;
    }

    public com.sevenlearn.googlemapsample.direction.Duration getDuration() {
        return Duration;
    }

    public void setDuration(com.sevenlearn.googlemapsample.direction.Duration duration) {
        Duration = duration;
    }

    public com.sevenlearn.googlemapsample.direction.EndLocation getEndLocation() {
        return EndLocation;
    }

    public void setEndLocation(com.sevenlearn.googlemapsample.direction.EndLocation endLocation) {
        EndLocation = endLocation;
    }

    public String getHtmlInstructions() {
        return HtmlInstructions;
    }

    public void setHtmlInstructions(String htmlInstructions) {
        HtmlInstructions = htmlInstructions;
    }

    public com.sevenlearn.googlemapsample.direction.Polyline getPolyline() {
        return Polyline;
    }

    public void setPolyline(com.sevenlearn.googlemapsample.direction.Polyline polyline) {
        Polyline = polyline;
    }

    public com.sevenlearn.googlemapsample.direction.StartLocation getStartLocation() {
        return StartLocation;
    }

    public void setStartLocation(com.sevenlearn.googlemapsample.direction.StartLocation startLocation) {
        StartLocation = startLocation;
    }

    public String getTravelMode() {
        return TravelMode;
    }

    public void setTravelMode(String travelMode) {
        TravelMode = travelMode;
    }

}

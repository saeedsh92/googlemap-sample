
package com.sevenlearn.googlemapsample.direction;

import java.util.List;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Leg {

    @SerializedName("distance")
    private com.sevenlearn.googlemapsample.direction.Distance Distance;
    @SerializedName("duration")
    private com.sevenlearn.googlemapsample.direction.Duration Duration;
    @SerializedName("end_address")
    private String EndAddress;
    @SerializedName("end_location")
    private com.sevenlearn.googlemapsample.direction.EndLocation EndLocation;
    @SerializedName("start_address")
    private String StartAddress;
    @SerializedName("start_location")
    private com.sevenlearn.googlemapsample.direction.StartLocation StartLocation;
    @SerializedName("steps")
    private List<Step> Steps;

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

    public String getEndAddress() {
        return EndAddress;
    }

    public void setEndAddress(String endAddress) {
        EndAddress = endAddress;
    }

    public com.sevenlearn.googlemapsample.direction.EndLocation getEndLocation() {
        return EndLocation;
    }

    public void setEndLocation(com.sevenlearn.googlemapsample.direction.EndLocation endLocation) {
        EndLocation = endLocation;
    }

    public String getStartAddress() {
        return StartAddress;
    }

    public void setStartAddress(String startAddress) {
        StartAddress = startAddress;
    }

    public com.sevenlearn.googlemapsample.direction.StartLocation getStartLocation() {
        return StartLocation;
    }

    public void setStartLocation(com.sevenlearn.googlemapsample.direction.StartLocation startLocation) {
        StartLocation = startLocation;
    }

    public List<Step> getSteps() {
        return Steps;
    }

    public void setSteps(List<Step> steps) {
        Steps = steps;
    }

}

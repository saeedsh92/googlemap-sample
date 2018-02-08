
package com.sevenlearn.googlemapsample.direction;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Polyline {

    @SerializedName("points")
    private String Points;

    public String getPoints() {
        return Points;
    }

    public void setPoints(String points) {
        Points = points;
    }

}


package com.sevenlearn.googlemapsample.direction;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Bounds {

    @SerializedName("northeast")
    private com.sevenlearn.googlemapsample.direction.Northeast Northeast;
    @SerializedName("southwest")
    private com.sevenlearn.googlemapsample.direction.Southwest Southwest;

    public com.sevenlearn.googlemapsample.direction.Northeast getNortheast() {
        return Northeast;
    }

    public void setNortheast(com.sevenlearn.googlemapsample.direction.Northeast northeast) {
        Northeast = northeast;
    }

    public com.sevenlearn.googlemapsample.direction.Southwest getSouthwest() {
        return Southwest;
    }

    public void setSouthwest(com.sevenlearn.googlemapsample.direction.Southwest southwest) {
        Southwest = southwest;
    }

}

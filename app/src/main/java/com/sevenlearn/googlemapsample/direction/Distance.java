
package com.sevenlearn.googlemapsample.direction;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Distance {

    @SerializedName("text")
    private String Text;
    @SerializedName("value")
    private Long Value;

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    public Long getValue() {
        return Value;
    }

    public void setValue(Long value) {
        Value = value;
    }

}

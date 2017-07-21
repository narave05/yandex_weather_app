package narek.example.com.yandex_weather_app.model.rest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CoordRes {

    @SerializedName("lon")
    @Expose
    public float lon;
    @SerializedName("lat")
    @Expose
    public float lat;

    @Override
    public String toString() {
        return "CoordRes{" +
                "lon=" + lon +
                ", lat=" + lat +
                '}';
    }
}

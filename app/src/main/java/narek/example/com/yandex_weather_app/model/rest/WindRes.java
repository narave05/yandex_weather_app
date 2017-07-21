package narek.example.com.yandex_weather_app.model.rest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WindRes {

    @SerializedName("speed")
    @Expose
    public float speed;
    @SerializedName("deg")
    @Expose
    public float deg;

    @Override
    public String toString() {
        return "WindRes{" +
                "speed=" + speed +
                ", deg=" + deg +
                '}';
    }
}

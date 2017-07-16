package narek.example.com.yandex_weather_app.model.rest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

class WeatherRes {

    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("main")
    @Expose
    public String main;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("icon")
    @Expose
    public String icon;

    @Override
    public String toString() {
        return "WeatherRes{" +
                "id=" + id +
                ", main='" + main + '\'' +
                ", description='" + description + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }
}

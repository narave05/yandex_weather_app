package narek.example.com.yandex_weather_app.model.rest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherDataRes {

    @SerializedName("coord")
    @Expose
    public CoordRes coordRes;
    @SerializedName("weather")
    @Expose
    public List<WeatherRes> weatherRes = null;
    @SerializedName("base")
    @Expose
    public String base;
    @SerializedName("main")
    @Expose
    public Main main;
    @SerializedName("visibility")
    @Expose
    public int visibility;
    @SerializedName("wind")
    @Expose
    public WindRes windRes;
    @SerializedName("clouds")
    @Expose
    public CloudsRes cloudsRes;
    @SerializedName("dt")
    @Expose
    public int dt;
    @SerializedName("sys")
    @Expose
    public Sys sys;
    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("cod")
    @Expose
    public int cod;

    @Override
    public String toString() {
        return "WeatherDataRes{" +
                "coordRes=" + coordRes +
                ", weatherRes=" + weatherRes +
                ", base='" + base + '\'' +
                ", main=" + main +
                ", visibility=" + visibility +
                ", windRes=" + windRes +
                ", cloudsRes=" + cloudsRes +
                ", dt=" + dt +
                ", sys=" + sys +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", cod=" + cod +
                '}';
    }

    public class Main {

        @SerializedName("temp")
        @Expose
        public float temp;
        @SerializedName("pressure")
        @Expose
        public int pressure;
        @SerializedName("humidity")
        @Expose
        public int humidity;
        @SerializedName("temp_min")
        @Expose
        public float tempMin;
        @SerializedName("temp_max")
        @Expose
        public float tempMax;

        @Override
        public String toString() {
            return "Main{" +
                    "temp=" + temp +
                    ", pressure=" + pressure +
                    ", humidity=" + humidity +
                    ", tempMin=" + tempMin +
                    ", tempMax=" + tempMax +
                    '}';
        }
    }

    public class Sys {

        @SerializedName("type")
        @Expose
        public int type;
        @SerializedName("id")
        @Expose
        public int id;
        @SerializedName("message")
        @Expose
        public float message;
        @SerializedName("country")
        @Expose
        public String country;
        @SerializedName("sunrise")
        @Expose
        public int sunrise;
        @SerializedName("sunset")
        @Expose
        public int sunset;

        @Override
        public String toString() {
            return "Sys{" +
                    "type=" + type +
                    ", id=" + id +
                    ", message=" + message +
                    ", country='" + country + '\'' +
                    ", sunrise=" + sunrise +
                    ", sunset=" + sunset +
                    '}';
        }
    }

}

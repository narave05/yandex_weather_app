package narek.example.com.yandex_weather_app.model.rest.forecast;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ForecastRes {

    @SerializedName("city")
    @Expose
    private CityForecast city;
    @SerializedName("cod")
    @Expose
    private String cod;
    @SerializedName("message")
    @Expose
    private double message;
    @SerializedName("cnt")
    @Expose
    private int cnt;
    @SerializedName("list")
    @Expose
    private List<ListForecast> list = null;

    public CityForecast getCity() {
        return city;
    }

    public void setCity(CityForecast city) {
        this.city = city;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public double getMessage() {
        return message;
    }

    public void setMessage(double message) {
        this.message = message;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public List<ListForecast> getList() {
        return list;
    }

    public void setList(List<ListForecast> list) {
        this.list = list;
    }

}

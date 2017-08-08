package narek.example.com.yandex_weather_app.util;


public class UnitsConverter {
    private static final float K = 273.15f;

    public int convertTemperature(float temp){
        return (int) (temp - K);
    }
}

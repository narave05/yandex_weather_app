package narek.example.com.yandex_weather_app.util;


public class UnitsConverter {
    private static final float K = 273.15f;

    public String convertTemperature(float temp){
        return "+" + String.valueOf((int)(temp - K));
    }
}

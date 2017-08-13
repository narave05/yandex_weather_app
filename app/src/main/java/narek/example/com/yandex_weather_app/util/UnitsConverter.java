package narek.example.com.yandex_weather_app.util;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class UnitsConverter {
    private static final float K = 273.15f;

    public String convertTemperature(float temp){
        if (temp - K > 0) {
            return "+" + String.valueOf((int)(temp - K));
        }else {
            return "-" + String.valueOf((int)(temp - K));
        }

    }

    public String convertTime(long time){
        Date dNow = new Date(time);
        SimpleDateFormat ft = new SimpleDateFormat("hh:mm");
        return ft.format(dNow);
    }
}

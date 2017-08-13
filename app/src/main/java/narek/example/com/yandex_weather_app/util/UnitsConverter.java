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

    public String convertTime(){
        Date dNow = new Date(System.currentTimeMillis());
        SimpleDateFormat ft = new SimpleDateFormat("hh:mm");
        return ft.format(dNow);
    }
}

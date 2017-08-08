package narek.example.com.yandex_weather_app.model.clean;

public class Forecasts {
    private long time;
    private double dayTemp;
    private double pressure;
    private int humidity;
    private double speed;
    private int degrees;
    private int clouds;
    private double rain;
    private int iconId;

    public Forecasts(long time, double dayTemp, double pressure, int humidity, double speed, int degrees, int clouds, double rain, int iconId) {
        this.time = time;
        this.dayTemp = dayTemp;
        this.pressure = pressure;
        this.humidity = humidity;
        this.speed = speed;
        this.degrees = degrees;
        this.clouds = clouds;
        this.rain = rain;
        this.iconId = iconId;
    }

    public double getDayTemp() {
        return dayTemp;
    }

    public void setDayTemp(double dayTemp) {
        this.dayTemp = dayTemp;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void setTemp(double temp) {
        this.dayTemp = temp;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setDegrees(int degrees) {
        this.degrees = degrees;
    }

    public void setClouds(int clouds) {
        this.clouds = clouds;
    }

    public void setRain(double rain) {
        this.rain = rain;
    }

    public long getTime() {
        return time;
    }

    public double getTemp() {
        return dayTemp;
    }

    public double getPressure() {
        return pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public double getSpeed() {
        return speed;
    }

    public int getDegrees() {
        return degrees;
    }

    public int getClouds() {
        return clouds;
    }

    public double getRain() {
        return rain;
    }

    public static class ForecastsBuilder{

        private long time;
        private double dayTemp;
        private double pressure;
        private int humidity;
        private double speed;
        private int degrees;
        private int clouds;
        private double rain;
        private int iconId;

        public ForecastsBuilder() {
        }

        public ForecastsBuilder time(long time){
            this.time = time;
            return this;
        }
        public ForecastsBuilder dayTemp(double dayTemp){
            this.dayTemp = dayTemp;
            return this;
        }
        public ForecastsBuilder pressure(double pressure){
            this.pressure = pressure;
            return this;
        }
        public ForecastsBuilder humidity(int humidity){
            this.humidity = humidity;
            return this;
        }
        public ForecastsBuilder speed(double speed){
            this.speed = speed;
            return this;
        }
        public ForecastsBuilder degrees(int degrees){
            this.degrees = degrees;
            return this;
        }
        public ForecastsBuilder clouds(int clouds){
            this.clouds = clouds;
            return this;
        }
        public ForecastsBuilder rain(double rain){
            this.rain = rain;
            return this;
        }
        public ForecastsBuilder iconId(int iconId){
            this.iconId = iconId;
            return this;
        }

        public Forecasts createForecasts (){
            return new Forecasts(time, dayTemp, pressure, humidity, speed, degrees, clouds, rain, iconId);
        }
    }
}

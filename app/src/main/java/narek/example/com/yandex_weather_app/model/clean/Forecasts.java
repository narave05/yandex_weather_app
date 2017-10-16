package narek.example.com.yandex_weather_app.model.clean;

public class Forecasts {
    private long time;
    private double dayTemp;
    private double nightTemp;
    private double pressure;
    private int humidity;
    private double speed;
    private int degrees;
    private int clouds;
    private double rain;
    private int iconId;

    public Forecasts(long time, double dayTemp, double nightTemp, double pressure, int humidity, double speed, int degrees, int clouds,
                     double rain, int iconId) {
        this.time = time;
        this.dayTemp = dayTemp;
        this.nightTemp = nightTemp;
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

    public double getNightTemp() {
        return nightTemp;
    }

    public void setNightTemp(double nightTemp) {
        this.nightTemp = nightTemp;
    }

    public double getRain() {
        return rain;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Forecasts forecasts = (Forecasts) o;

        if (time != forecasts.time) return false;
        if (Double.compare(forecasts.dayTemp, dayTemp) != 0) return false;
        if (Double.compare(forecasts.nightTemp, nightTemp) != 0) return false;
        if (Double.compare(forecasts.pressure, pressure) != 0) return false;
        if (humidity != forecasts.humidity) return false;
        if (Double.compare(forecasts.speed, speed) != 0) return false;
        if (degrees != forecasts.degrees) return false;
        if (clouds != forecasts.clouds) return false;
        if (Double.compare(forecasts.rain, rain) != 0) return false;
        return iconId == forecasts.iconId;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (time ^ (time >>> 32));
        temp = Double.doubleToLongBits(dayTemp);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(nightTemp);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(pressure);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + humidity;
        temp = Double.doubleToLongBits(speed);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + degrees;
        result = 31 * result + clouds;
        temp = Double.doubleToLongBits(rain);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + iconId;
        return result;
    }

    public static class ForecastsBuilder{

        private long time;
        private double dayTemp;
        private double nightTemp;
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
        public ForecastsBuilder nightTemp(double nightTemp){
            this.nightTemp = nightTemp;
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
            return new Forecasts(time, dayTemp, nightTemp, pressure, humidity, speed, degrees, clouds, rain, iconId);
        }
    }
}

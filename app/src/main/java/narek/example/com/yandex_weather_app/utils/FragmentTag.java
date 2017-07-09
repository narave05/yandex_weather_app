package narek.example.com.yandex_weather_app.utils;

public enum FragmentTag {

    WEATHER("Weather"),
    SETTINGS("Settings"),
    ABOUT("About");

    String name;

    FragmentTag(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}

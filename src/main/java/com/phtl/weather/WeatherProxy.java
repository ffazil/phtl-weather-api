package com.phtl.weather;

public interface WeatherProxy {
    Object findCurrentWeather(GeoCoordinate coordinate);
    Object findForecast(GeoCoordinate coordinate);
}

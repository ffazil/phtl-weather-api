package com.phtl.weather;

import com.phtl.weather.owm.daily.DailyForecastResult;
import com.phtl.weather.owm.forecast.ForecastResult;
import com.phtl.weather.owm.weather.WeatherResult;

public interface WeatherProxy {
    WeatherResult findCurrentWeather(GeoCoordinate coordinate);
    ForecastResult findForecast(GeoCoordinate coordinate);
    DailyForecastResult findDailyForecast(GeoCoordinate coordinate);
}

package com.phtl.weather.owm;

import com.phtl.weather.GeoCoordinate;
import com.phtl.weather.WeatherProxy;
import com.phtl.weather.owm.daily.DailyForecastResult;
import com.phtl.weather.owm.forecast.ForecastResult;
import com.phtl.weather.owm.weather.WeatherResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
@RequiredArgsConstructor
public class OWMWeatherProxy implements WeatherProxy {

    private final RestTemplate restTemplate;

    @Value("${owm.appid}")
    private String appId;

    @Value("${owm.weather.url}")
    private String owmWeatherUrl;

    @Value("${owm.forecast.url}")
    private String owmForecastUrl;

    @Value("${owm.daily.url}")
    private String owmDailyForecastUrl;



    @Override
    public WeatherResult findCurrentWeather(GeoCoordinate coordinate) {
        WeatherResult weatherResult = restTemplate.getForObject(owmWeatherUrl, WeatherResult.class, coordinate.getLatRounded(), coordinate.getLonRounded(), appId);
        return weatherResult;
    }

    @Override
    public ForecastResult findForecast(GeoCoordinate coordinate, Integer days) {
        ForecastResult forecastResult = restTemplate.getForObject(owmForecastUrl, ForecastResult.class, coordinate.getLatRounded(), coordinate.getLonRounded(), appId, days);
        return forecastResult;
    }

    @Override
    public DailyForecastResult findDailyForecast(GeoCoordinate coordinate, Integer days) {
        DailyForecastResult dailyForecastResult = restTemplate.getForObject(owmDailyForecastUrl, DailyForecastResult.class, coordinate.getLatRounded(), coordinate.getLonRounded(), appId, days);
        return dailyForecastResult;
    }
}

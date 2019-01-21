package com.phtl.weather.owm;

import com.phtl.weather.GeoCoordinate;
import com.phtl.weather.WeatherProxy;
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

    @Value("${owm.url}")
    private String owmUrl;

    @Value("${owm.appid}")
    private String appId;

    @Override
    public WeatherResult findWeather(GeoCoordinate coordinate) {
        WeatherResult weatherResult = restTemplate.getForObject(owmUrl, WeatherResult.class, coordinate.getLatRounded(), coordinate.getLonRounded(), appId);
        return weatherResult;
    }
}

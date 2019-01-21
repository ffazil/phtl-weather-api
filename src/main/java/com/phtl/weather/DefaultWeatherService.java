package com.phtl.weather;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultWeatherService implements WeatherService{

    private final WeatherProxy weatherProxy;

    @Override
    public Object findWeather(GeoCoordinate coordinate) {
        return weatherProxy.findWeather(coordinate);
    }
}

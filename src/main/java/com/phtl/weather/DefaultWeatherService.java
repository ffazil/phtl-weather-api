package com.phtl.weather;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames={"weather"})
public class DefaultWeatherService implements WeatherService{

    private final WeatherProxy weatherProxy;

    @Override
    @Cacheable
    public Object findWeather(GeoCoordinate coordinate) {
        return weatherProxy.findWeather(coordinate);
    }
}

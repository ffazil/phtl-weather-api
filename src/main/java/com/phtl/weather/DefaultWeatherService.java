package com.phtl.weather;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.time.temporal.ValueRange;

@Slf4j
@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames={"weather"})
public class DefaultWeatherService implements WeatherService{

    private static final ValueRange forecastRange = ValueRange.of(0, 6);

    private final WeatherProxy weatherProxy;
    private final TimeService timeService;

    @Override
    @Cacheable
    public Result findWeather(GeoCoordinate coordinate) {
        ZonedDateTime timeAtLocation = timeService.timeAtLocation(coordinate);
        log.info("Time at location: {}", timeAtLocation.toString());
        if(forecastRange.isValidValue(timeAtLocation.getHour())){
            return Result.from(weatherProxy.findForecast(coordinate));
        }
        return Result.from(weatherProxy.findCurrentWeather(coordinate));
    }
}

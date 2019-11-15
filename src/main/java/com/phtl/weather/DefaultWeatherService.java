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
    public Result findWeather(GeoCoordinate coordinate, Integer days) {
        if(days > 7){
            days = 7;
        }

        ZonedDateTime timeAtLocation = timeService.timeAtLocation(coordinate);
        Result result = Result.from(weatherProxy.findDailyForecast(coordinate, days), weatherProxy.findCurrentWeather(coordinate), timeAtLocation);
        return result;
        /*if(forecastRange.isValidValue(timeAtLocation.getHour())){
            return Result.from(weatherProxy.findDailyForecast(coordinate), weatherProxy.findCurrentWeather(coordinate), timeAtLocation);
        }
        return Result.from(weatherProxy.findCurrentWeather(coordinate));*/
    }
}

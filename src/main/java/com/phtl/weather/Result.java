package com.phtl.weather;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.phtl.weather.owm.daily.DailyForecastResult;
import com.phtl.weather.owm.daily.List;
import com.phtl.weather.owm.forecast.ForecastResult;
import com.phtl.weather.owm.forecast.Main;
import com.phtl.weather.owm.forecast.Weather;
import com.phtl.weather.owm.weather.WeatherResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.time.ZonedDateTime;

@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class Result {

    @JsonProperty("currentTemp")
    private final Double temp;
    @JsonProperty("minTemp")
    private final Double tempMin;
    @JsonProperty("maxTemp")
    private final Double tempMax;
    @JsonProperty("id")
    private final Integer id;
    @JsonProperty("city")
    private final String city;

    private Result(WeatherResult weatherResult){
        this.temp = weatherResult.getMain().getTemp();
        this.tempMin = weatherResult.getMain().getTempMin();
        this.tempMax = weatherResult.getMain().getTempMax();
        this.id = weatherResult.getWeather().stream().findFirst().get().getId();
        this.city = weatherResult.getName();
    }

    public static Result from(WeatherResult weatherResult){
        return new Result(weatherResult);
    }

    private Result(ForecastResult forecastResult, ZonedDateTime timeAtLocation){

        Main main = forecastResult.getList().stream()
                .findFirst().get().getMain();

        Weather weather = forecastResult.getList().stream()
                .findFirst().get().getWeather().stream()
                .findFirst().get();



        this.temp = main.getTemp();
        this.tempMin = main.getTempMin();
        this.tempMax = main.getTempMax();
        this.id = weather.getId();

        this.city = forecastResult.getCity().getName();

    }

    public static Result from(ForecastResult forecastResult, ZonedDateTime timeAtLocation){
        return new Result(forecastResult, timeAtLocation);
    }

    //TODO cleanup
    private Result(DailyForecastResult dailyForecastResult, WeatherResult weatherResult, ZonedDateTime timeAtLocation){
        log.info("Time at location: {}", timeAtLocation.toString());
        /*dailyForecastResult.getList().stream()
                .forEach(list -> {
                    ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(Instant.ofEpochSecond(list.getDt().longValue()), timeAtLocation.getZone());
                    log.info("Prediction: {}", zonedDateTime.toString());
                });*/

        /*List selected = dailyForecastResult.getList().stream()
                .filter(list -> timeAtLocation.isAfter(ZonedDateTime.ofInstant(Instant.ofEpochSecond(list.getDt().longValue()), timeAtLocation.getZone())))
                .findFirst()
                .get();*/




        java.util.List<List> forecasts = dailyForecastResult.getList();
        ZonedDateTime minForecastTime = ZonedDateTime.ofInstant(Instant.ofEpochSecond(forecasts.get(0).getDt().longValue()), timeAtLocation.getZone());
        /*ZonedDateTime maxForecastTime = ZonedDateTime.ofInstant(Instant.ofEpochSecond(forecasts.get(1).getDt().longValue()), timeAtLocation.getZone());*/

        List selected = null;
        if(timeAtLocation.isAfter(minForecastTime)){
            selected = forecasts.get(1);
        }
        else {
            selected = forecasts.get(0);
        }


        this.temp = weatherResult.getMain().getTemp();
        this.tempMin = selected.getTemp().getMin();
        this.tempMax = selected.getTemp().getMax();
        this.id = weatherResult.getWeather().stream().findFirst().get().getId();

        this.city = weatherResult.getName();

    }

    public static Result from(DailyForecastResult dailyForecastResult, WeatherResult weatherResult, ZonedDateTime timeAtLocation){
        return new Result(dailyForecastResult, weatherResult, timeAtLocation);
    }
}



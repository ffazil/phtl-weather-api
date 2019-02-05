package com.phtl.weather;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.phtl.weather.owm.forecast.ForecastResult;
import com.phtl.weather.owm.forecast.Main;
import com.phtl.weather.owm.forecast.Weather;
import com.phtl.weather.owm.weather.WeatherResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private Result(ForecastResult forecastResult){
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

    public static Result from(ForecastResult forecastResult){
        return new Result(forecastResult);
    }
}

package com.phtl.weather;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.phtl.weather.owm.forecast.ForecastResult;
import com.phtl.weather.owm.forecast.Main;
import com.phtl.weather.owm.weather.WeatherResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class Result {

    @JsonProperty("temp")
    private final Double temp;
    @JsonProperty("temp_min")
    private final Double tempMin;
    @JsonProperty("temp_max")
    private final Double tempMax;

    private Result(WeatherResult weatherResult){
        this.temp = weatherResult.getMain().getTemp();
        this.tempMin = weatherResult.getMain().getTempMin();
        this.tempMax = weatherResult.getMain().getTempMax();
    }

    public static Result from(WeatherResult weatherResult){
        return new Result(weatherResult);
    }

    private Result(ForecastResult forecastResult){
        Main main = forecastResult.getList().stream()
                .findFirst().get().getMain();

        this.temp = main.getTemp();
        this.tempMin = main.getTempMin();
        this.tempMax = main.getTempMax();
    }

    public static Result from(ForecastResult forecastResult){
        return new Result(forecastResult);
    }
}

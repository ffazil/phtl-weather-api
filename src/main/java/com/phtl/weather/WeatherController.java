package com.phtl.weather;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;


    @GetMapping(path = "/weather")
    public ResponseEntity<?> findWeather(@RequestParam("lat") double lat, @RequestParam("lon") double lon) {
        GeoCoordinate coordinate = GeoCoordinate.from(lat, lon);
        return ResponseEntity.ok(weatherService.findWeather(coordinate));
    }
}

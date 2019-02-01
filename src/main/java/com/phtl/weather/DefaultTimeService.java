package com.phtl.weather;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultTimeService implements TimeService{

    @Override
    public ZonedDateTime timeAtLocation(GeoCoordinate coordinate) {
        String timezoneText = TimezoneMapper.latLngToTimezoneString(coordinate.getLat(), coordinate.getLon());
        ZoneId zoneId = ZoneId.of(timezoneText);
        return ZonedDateTime.now(zoneId);
    }
}

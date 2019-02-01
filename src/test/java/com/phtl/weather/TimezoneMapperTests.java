package com.phtl.weather;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ValueRange;

@Slf4j
public class TimezoneMapperTests {

    @Test
    public void convertsLatLongtoTimezone(){
        String timezoneText = TimezoneMapper.latLngToTimezoneString(44.12345, -96.56789);
        ZoneId zoneId = ZoneId.of(timezoneText);
        ZonedDateTime dateTimeAtZone = ZonedDateTime.now(zoneId);

        ValueRange forecastRange = ValueRange.of(0, 6);
        boolean isForecastRange = forecastRange.isValidValue(dateTimeAtZone.getHour());
        log.info("Timezone: {} within forecast range: {}", timezoneText, isForecastRange);
    }
}

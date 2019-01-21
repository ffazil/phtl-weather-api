package com.phtl.weather;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

@Slf4j
public class GeoCoordinateMarshallingTests {

    private ObjectMapper mapper;

    @Before
    public void setup(){
        this.mapper = new ObjectMapper()
                .findAndRegisterModules();
    }

    @Test
    public void serializes() throws JsonProcessingException {
        GeoCoordinate coordinate = new GeoCoordinate(33.123456, -96.123456);
        String result = mapper.writeValueAsString(coordinate);
        log.info("Coordinate: {}", result);
        Assert.assertNotNull(result);
    }

    @Test
    public void deSerializes() throws IOException {
        String payload = "{\"lat\":33.123456,\"lon\":-96.123456}";
        GeoCoordinate coordinate = mapper.readValue(payload, GeoCoordinate.class);
        log.info("Geocoordinate = {}", coordinate);
        Assert.assertNotNull(coordinate);
    }
}

package com.phtl.weather;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.phtl.weather.owm.WeatherResult;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class OWMMarshallingTests {

    private ObjectMapper mapper;

    @Before
    public void setup(){
        this.mapper = new ObjectMapper()
                .findAndRegisterModules();
    }


    @Test
    public void deSerializes() throws IOException {
        String payload = "{\"coord\":{\"lon\":-96,\"lat\":33},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"clear sky\",\"icon\":\"01n\"}],\"base\":\"stations\",\"main\":{\"temp\":275.7,\"pressure\":1023,\"humidity\":74,\"temp_min\":273.85,\"temp_max\":277.95},\"visibility\":16093,\"wind\":{\"speed\":3.6,\"deg\":150},\"clouds\":{\"all\":1},\"dt\":1548056100,\"sys\":{\"type\":1,\"id\":4287,\"message\":0.0067,\"country\":\"US\",\"sunrise\":1548077107,\"sunset\":1548114357},\"id\":4680036,\"name\":\"Center Point\",\"cod\":200}";
        WeatherResult result = mapper.readValue(payload, WeatherResult.class);
        Assert.assertNotNull(result);
    }
}

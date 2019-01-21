package com.phtl.weather;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

/**
 * Realization of a geo-coordinate value object.
 */

@Value
@ToString
@EqualsAndHashCode
public class GeoCoordinate {

    final double lat; // Latitude
    final double lon; // Longitude

    @JsonCreator
    public GeoCoordinate(@JsonProperty("lat") double lat, @JsonProperty("lon") double lon) {

        if(!isValid(lat, lon)){
            throw new IllegalArgumentException("Invalid latitude/longitude");
        }

        this.lat = lat;
        this.lon = lon;


    }

    public static GeoCoordinate from(double lat, double lon){
        return new GeoCoordinate(lat, lon);
    }


    // Get rounded integer from latitude
    public int getLatRounded(){
        final int roundLat = (int) Math.round(this.lat);
        return roundLat;
    }

    //Get rounded integer from longitude
    public int getLonRounded(){
        final int roundLon = (int) Math.round(this.lon);
        return roundLon;
    }

    // Check if latitude and longitude are valid
    private boolean isValid(double lat, double lon){
        if ( lat > 90 || lat < -90 )
            return false;
        if ( lon > 180 || lon < -180 )
            return false;
        return true;
    }




}

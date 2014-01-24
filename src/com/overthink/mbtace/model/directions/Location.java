package com.overthink.mbtace.model.directions;

import java.io.Serializable;

/**
 * Represents a location of a transit stop by latitude and longitude
 */
public class Location implements Serializable{

    private double lat;
    private double lng;

    /**
     * @return latitude of the location
     */
    public double getLat() {
        return lat;
    }

    /**
     * @return longitude of the location
     */
    public double getLng() {
        return lng;
    }
}

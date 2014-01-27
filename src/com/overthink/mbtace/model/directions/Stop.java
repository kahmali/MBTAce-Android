package com.overthink.mbtace.model.directions;

import java.io.Serializable;

/**
 * Represents information about the stop/station for this part of the trip.
 */
public class Stop implements Serializable {
    private String name;
    private Location location;

    /**
     * @return the name of the transit station/stop. eg. "Union Square".
     */
    public String getName(){
        return name;
    }

    /**
     * @return the location of the transit station/stop, represented as a lat and lng field.
     */
    public Location getLocation(){
        return location;
    }
}

package com.overthink.mbtace.model.directions;

import java.io.Serializable;

/**
 * Represents an object holding a String of points that represent an approximate path of the resulting directions.
 */
public class Polyline implements Serializable {
    private String points;

    /*
     * @return String object with the set of points in this journey's path
     */
    public String getPoints(){
        return points;
    }
}

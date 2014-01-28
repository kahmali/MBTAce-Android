package com.overthink.mbtace.model.directions;

import java.io.Serializable;

/**
 * Represents the total duration of this leg of the trip
 */
public class Distance implements Serializable{
    String text;
    int value;

    /*
     * @return String representation of the distance, displayed in units as used at the origin.
     */
    public String getText() {
        return text;
    }

    /*
     * @return the distance in meters.
     */
    public int getValue() {
        return value;
    }
}
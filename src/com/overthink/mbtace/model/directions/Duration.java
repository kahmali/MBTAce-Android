package com.overthink.mbtace.model.directions;

import java.io.Serializable;

/**
 * Represents the total duration of this leg of the trip
 */
public class Duration implements Serializable{
    String text;
    int value;

    /*
     * @returns human-readable representation of the duration.
     */
    public String getText() {
        return text;
    }


    /*
     * @returns the duration of this leg of the trip in seconds.
     */
    public int getValue() {
        return value;
    }
}

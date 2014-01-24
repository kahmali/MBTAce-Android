package com.overthink.mbtace.model.directions;

import java.io.Serializable;

/**
 * Represents a Google Time object with
 */
public class Time implements Serializable{

    private String text;
    private String timeZone;
    private long value;

    /**
     * @return the time in with the following format 6:20am
     */
    public String getText() {
        return text;
    }

    /**
     * @return the time zone of the station in Olson String format
     */
    public String getTimeZone() {
        return timeZone;
    }

    /**
     * @return the time specified as a JavaScript Date object
     */
    public long getValue() {
        return value;
    }
}

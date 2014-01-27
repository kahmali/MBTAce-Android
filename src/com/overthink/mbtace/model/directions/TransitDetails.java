package com.overthink.mbtace.model.directions;

import com.google.gson.annotations.*;
import java.io.Serializable;

/**
 * Model class for Transit Details that contains additional info on the steps of the trip
 */
public class TransitDetails {
    private Stop arrivalStop,departureStop;
    private Time arrivalTime,departureTime;
    private String headsign;
    private Line line;
    private int headway,numStop;

    /**
     * @return
     */
    public int getNumStop() {
        return numStop;
    }

    /**
     * @return
     */
    public int getHeadway() {
        return headway;
    }

    /**
     * @return
     */
    public Line getLine() {
        return line;
    }

    /**
     * @return
     */
    public Stop getArrivalStop() {
        return arrivalStop;
    }

    /**
     * @return
     */
    public Stop getDepartureStop() {
        return departureStop;
    }

    /**
     * @return
     */
    public Time getArrivalTime() {
        return arrivalTime;
    }

    /**
     * @return
     */
    public Time getDepartureTime() {
        return departureTime;
    }

    /**
     * @return
     */
    public String getHeadsign() {
        return headsign;
    }





}

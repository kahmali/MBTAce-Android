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
     * @return arrival Stop object for this part of the trip.
     */
    public Stop getArrivalStop() {
        return arrivalStop;
    }


    /**
     * @return departure Stop object for this part of the trip.
     */
    public Stop getDepartureStop() {
        return departureStop;
    }


    /**
     * @return the arrival time for this leg of the journey
     */
    public Time getArrivalTime() {
        return arrivalTime;
    }


    /**
     * @return the departure time for this leg of the journey
     */
    public Time getDepartureTime() {
        return departureTime;
    }


    /**
     * @return the direction in which to travel on this line, usually will be the terminus station.
     */
    public String getHeadsign() {
        return headsign;
    }


    /**
     * @return the expected number of seconds between departures from the same stop at this time.
     */
    public int getHeadway() {
        return headway;
    }


    /**
     * @return the number of stops in this step, counting the arrival stop, but not the departure stop.
     */
    public int getNumStop() {
        return numStop;
    }


    /**
     * @return  information about the transit Line object used in this step
     */
    public Line getLine() {
        return line;
    }

}

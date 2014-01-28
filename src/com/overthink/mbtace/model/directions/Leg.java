package com.overthink.mbtace.model.directions;

import java.io.Serializable;
import java.util.List;

/**
 * Represents a single leg of the journey from the origin to the destination in the calculated route. For routes
 * that contain no waypoints, the route will consist of a single "leg," but for routes that define one or more
 * waypoints, the route will consist of one or more legs, corresponding to the specific legs of the journey.
 */
public class Leg implements Serializable {

    private List<Step> steps;
    private Distance distance;
    private Duration duration;
    private Time arrivalTime, departureTime;
    private Location startLocation, endLocation;
    private String startAddress, endAddress;

    /*
     * @return a List of steps denoting information about each separate step of the leg of the journey.
     */
    public List<Step> getSteps() {
        return steps;
    }

    /*
     * @return the Distance object representing total distance covered by this leg.
     */
    public Distance getDistance() {
        return distance;
    }

    /*
     * @return the Duration object representing the total duration of this leg.
     */
    public Duration getDuration() {
        return duration;
    }

    /*
     * @return the Time object with the estimated time of arrival for this leg.
     */
    public Time getArrivalTime() {
        return arrivalTime;
    }

    /*
     * @return the Time object with the estimated time of departure for this leg.
     */
    public Time getDepartureTime() {
        return departureTime;
    }

    /*
     * @return the latitude/longitude coordinates of the origin of this leg.
     */
    public Location getStartLocation() {
        return startLocation;
    }

    /*
     * @return the latitude/longitude coordinates of the given destination of this leg.
     */
    public Location getEndLocation() {
        return endLocation;
    }

    /*
     * @return the String address (typically a street address) reflecting the start location of this leg.
     */
    public String getStartAddress() {
        return startAddress;
    }

    /*
     * @return the String address (typically a street address) reflecting the end location of this leg.
     */
    public String getEndAddress() {
        return endAddress;
    }
}

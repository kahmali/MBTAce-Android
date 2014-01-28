package com.overthink.mbtace.model.directions;

import java.io.Serializable;
import java.util.List;

/**
 * Encapsulates a specific, single instruction on the journey. E.g. "Turn left at W. 4th St."
 */
public class Step implements Serializable {
    private String htmlInstructions;
    private Distance distance;
    private Duration duration;
    private Location startLocation,endLocation;
    private TransitDetails transitDetails;
    private List<Step> subSteps;
    private Polyline polyline;

    /*
     * @return formatted instructions for this step, presented as an HTML text string.
     */
    public String getHtmlInstructions() {
        return htmlInstructions;
    }

    /*
     * @return the distance covered by this step until the next step. If distance unknown, may be undefined.
     */
    public Distance getDistance() {
        return distance;
    }

    /*
     * @return the typical time needed to perform the step, until the next step. If duration unknown, may be undefined.
     */
    public Duration getDuration() {
        return duration;
    }

    /*
     * @return the location of the starting point of this step, as a single set of lat and lng fields.
     */
    public Location getStartLocation() {
        return startLocation;
    }

    /*
     * @return the location of the last point of this step, as a single set of lat and lng fields.
     */
    public Location getEndLocation() {
        return endLocation;
    }

    /*
     * @return transit specific information. This field is only returned with travel_mode is set to "transit".
     */
    public TransitDetails getTransitDetails() {
        return transitDetails;
    }

    /*
     * @return List of Step objects with directions for walking or driving steps in transit directions.
      * Substeps are only available when travel_mode is set to "transit".
     */
    public List<Step> getSubSteps() {
        return subSteps;
    }

    /*
     * @return Polyline object for this Step in the journey
     */
    public Polyline getPolyline() {
        return polyline;
    }
}

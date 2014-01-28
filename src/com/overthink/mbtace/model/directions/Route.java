package com.overthink.mbtace.model.directions;

import java.io.Serializable;
import java.util.List;

/**
 * Represents a single result from the specified origin and destination. This route may consist of one or more legs
 * depending on whether any waypoints were specified.
 */
public class Route implements Serializable {
    private String summary, copyrights;
    private Bounds bounds;
    private Polyline overviewPolyline;
    private List<Leg> legs;
    private List<String> warnings;

    /*
     * @return short textual description for the route, suitable for naming and
     * disambiguating the route from alternatives.
     */
    public String getSummary() {
        return summary;
    }

    /*
     * @return the viewport bounding box of the polyline encapsulated in a Bounds object.
     */
    public Bounds getBounds() {
        return bounds;
    }

    /*
     * @return a Polyline object holding an array of points that represent approximate path of the resulting directions.
     */
    public Polyline getOverviewPolyline() {
        return overviewPolyline;
    }

    /*
     * @return List of Leg objects the contain information on each leg of the route
     */
    public List<Leg> getLegs() {
        return legs;
    }

    /*
     * @return an array of warnings to be displayed when showing these directions.
     * You must handle and display these warnings yourself.
     */
    public List<String> getWarnings() {
        return warnings;
    }

    /*
     * @return the copyrights text to be displayed for this route.
     * You must handle and display this information yourself.
     */
    public String getCopyrights() {
        return copyrights;
    }
}

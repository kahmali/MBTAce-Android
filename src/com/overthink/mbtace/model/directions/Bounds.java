package com.overthink.mbtace.model.directions;

import java.io.Serializable;

/**
 * Represents the bounds of the polyline used to set the size of the viewed Map screen
 */
public class Bounds implements Serializable {
    private Location northeast;
    private Location southwest;

    /*
     * @return Location object for the northeast bound of polyline
     */
    public Location getNortheast() {
        return northeast;
    }

    /*
     * @return Location object for the southwest bound of polyline
     */
    public Location getSouthwest() {
        return southwest;
    }
}

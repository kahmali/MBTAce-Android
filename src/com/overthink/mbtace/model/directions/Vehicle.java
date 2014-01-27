package com.overthink.mbtace.model.directions;

import java.io.Serializable;
import java.net.URL;

/**
 * Represents the type of vehicle used on this line
 */
public class Vehicle implements Serializable{

    private String name, type;
    private URL icon;

    /**
     * @return the name of the vehicle on this line. eg. "Subway."
     */
    public String getName(){
        return name;
    }

    /**
     * @return the type of vehicle that runs on this line.
     */
    public String getType(){
        return type;
    }

    /**
     * @return the URL for an icon associated with this vehicle type.
     */
    public URL getIcon(){
        return icon;
    }
}

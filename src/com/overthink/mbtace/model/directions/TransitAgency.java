package com.overthink.mbtace.model.directions;

import java.io.Serializable;
import java.net.URL;

/**
 * Represents the transit agency responsible for the transportation, eg. MBTA
 */
public class TransitAgency implements Serializable{
    private String name,phone;
    private URL url;

    /**
     * @return name of transit agency, eg. MBTA
     */
    public String getName(){
        return name;
    }

    /**
     * @return URL of the transit agency
     */
    public URL getUrl(){
        return url;
    }

    /**
     * @return phone number of the transit agency
     */
    public String getPhone(){
        return phone;
    }

}

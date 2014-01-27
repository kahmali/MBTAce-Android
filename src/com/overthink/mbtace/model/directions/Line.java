package com.overthink.mbtace.model.directions;

import java.io.Serializable;
import java.net.URL;
import java.util.List;

/**
 * Represents information about the transit line used in this step
 */
public class Line implements Serializable{

    private String name,shortName,color,textColor;
    private URL url,icon;
    private Vehicle vehicle;
    private List<TransitAgency> agencies;

    /**
     * @return the full name of this transit line. eg. "7 Avenue Express".
     */
    public String getName(){
        return name;
    }

    /**
     * @return the short name of this transit line. This will normally be a line number, such as "M7" or "355".
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * @return the color commonly used in signage for this transit line.
     * The color will be specified as a hex string such as: #FF0033.
     */
    public String getColor() {
        return color;
    }

    /**
     * @return the color of text commonly used for signage of this line. The color will be specified as a hex string.
     */
    public String getTextColor() {
        return textColor;
    }

    /**
     * @return an array of TransitAgency objects that each provide information about the operator of the line
     */
    public List<TransitAgency> getAgencies() {
        return agencies;
    }

    /**
     * @return the URL for this transit line as provided by the transit agency.
     */
    public URL getUrl() {
        return url;
    }

    /**
     * @return  the URL for the icon associated with this line.
     */
    public URL getIcon() {
        return icon;
    }

    /**
     * @return Vehicle object that represents the type of vehicle used on this line.
     */
    public Vehicle getVehicle() {
        return vehicle;
    }


}

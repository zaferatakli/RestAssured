package model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Place {
    private String placeName;
    private String longitude;
    private String latitude;
    private String state;
    private String stateAbbreviation;

    public Place() {
    }

    public String getPlacename() {
        return placeName;
    }
    @JsonProperty("place name")
    public void setPlacename(String placeName) {
        this.placeName = this.placeName;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStateAbbreviation() {
        return stateAbbreviation;
    }
    @JsonProperty("state abbreviation")
    public void setStateAbbreviation(String stateAbbreviation) {
        this.stateAbbreviation = stateAbbreviation;
    }

    @Override
    public String toString() {
        return "Place{" + "placeName='" + placeName + '\'' + ", longitude='" + longitude + '\'' + ", latitude='" + latitude + '\'' + ", state='" + state + '\'' + ", stateAbbreviation='" + stateAbbreviation + '\'' + '}';
    }
}

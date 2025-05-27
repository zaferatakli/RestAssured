package model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class ZipCode {
    private String country;
    private String countryAbbreviation;
    private String postcode;
    private ArrayList<Place> places = new ArrayList<>();

    public ZipCode() {
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryAbbreviation() {
        return countryAbbreviation;
    }
    @JsonProperty("country abbreviation")
    public void setCountryAbbreviation(String countryAbbreviation) {
        this.countryAbbreviation = countryAbbreviation;
    }

    public String getPostcode() {
        return postcode;
    }
    @JsonProperty("post code")
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public ArrayList<Place> getPlaces() {
        return places;
    }

    public void setPlaces(ArrayList<Place> places) {
        this.places = places;
    }

    @Override
    public String toString() {
        return "ZipCode{" + "country='" + country + '\'' + ", countryAbbreviation='" + countryAbbreviation + '\'' + ", postcode='" + postcode + '\'' + ", places=" + places + '}';
    }
}

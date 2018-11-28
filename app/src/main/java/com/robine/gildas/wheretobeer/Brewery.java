package com.robine.gildas.wheretobeer;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.PropertyName;
import com.google.maps.android.clustering.ClusterItem;

import java.io.Serializable;

public class Brewery implements Serializable, ClusterItem {

    String brewery_id;
    @PropertyName("Brewer")
    String brewer;
    @PropertyName("Address")
    String address;
    @PropertyName("City")
    String city;
    @PropertyName("State")
    String state;
    @PropertyName("Country")
    String country;
    @PropertyName("Coordinates")
    String coordinates;

    public Brewery(){

    }

    public String getBrewer() {
        return brewer;
    }

    public void setBrewer(String brewer) {
        this.brewer = brewer;
    }

    public String getId() {
        return brewery_id;
    }

    public void setId(String id) {
        this.brewery_id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public LatLng getPosition() {
        String[] sepLatLng = coordinates.split(",");
        return new LatLng(Double.valueOf(sepLatLng[0]), Double.valueOf(sepLatLng[1]));
    }

    @Override
    public String getTitle() {
        return brewer;
    }

    @Override
    public String getSnippet() {
        return address;
    }
}

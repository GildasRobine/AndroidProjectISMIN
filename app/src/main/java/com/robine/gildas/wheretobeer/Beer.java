package com.robine.gildas.wheretobeer;

import com.google.firebase.database.PropertyName;


import java.io.Serializable;

public class Beer implements Serializable{
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCat_id() {
        return cat_id;
    }

    public void setCat_id(Long cat_id) {
        this.cat_id = cat_id;
    }

    public Long getAlcohol() {
        return alcohol;
    }

    /*public void setAlcohol(Long alcohol) {
        this.alcohol = alcohol;
    }*/

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getBrewery_id() {
        return brewery_id;
    }

    public void setBrewery_id(Long brewery_id) {
        this.brewery_id = brewery_id;
    }

    public String getBrewer() {
        return brewer;
    }

    public void setBrewer(String brewer) {
        this.brewer = brewer;
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

    @PropertyName("Name")
    private String name;
    @PropertyName("id")
    private Long id;
    @PropertyName("cat_id")
    private Long cat_id;
    @PropertyName("Alcohol By Volume")
    public Long alcohol;
    @PropertyName("Category")
    private String category;
    @PropertyName("brewery_id")
    private Long brewery_id;
    @PropertyName("Brewer")
    private String brewer;
    @PropertyName("Address")
    private String address;
    @PropertyName("City")
    private String city;
    @PropertyName("State")
    private String state;
    @PropertyName("Country")
    private String country;
    @PropertyName("Coordinates")
    private String coordinates;

    public Beer(){}

    Beer(String beerName, String brewName, String brewAdress, Long alcohol){
        name = beerName;
        brewer = brewName;
        address = brewAdress;
        alcohol = alcohol;
    }






}


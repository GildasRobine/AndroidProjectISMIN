package com.robine.gildas.wheretobeer;

import android.arch.persistence.room.Database;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.io.Serializable;
import java.util.HashMap;

public class Beer {
    String name;
    String id;
    String brewery_id;
    String cat_id;
    String alcohol;
    String categorie;
    String brewer;
    String address;
    String city;
    String state;
    String country;
    String coordinates;

    Beer(String beerName, String brewName, String brewAdress, String alRate){
        name = beerName;
        brewer = brewName;
        address = brewAdress;
        alcohol = alRate;
    }



    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getBrewery_id() {
        return brewery_id;
    }

    public String getCat_id() {
        return cat_id;
    }

    public String getAlcohol() {
        return alcohol;
    }

    public String getCategorie() {
        return categorie;
    }

    public String getBrewer() {
        return brewer;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public String getCoordinates() {
        return coordinates;
    }

}


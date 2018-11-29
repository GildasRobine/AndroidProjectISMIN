package com.robine.gildas.wheretobeer;

import android.arch.persistence.room.Database;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.PropertyName;


import java.io.Serializable;
import java.util.HashMap;

public class Beer {
    @PropertyName("Name")
    String name;
    @PropertyName("id")
    Long id;
    @PropertyName("cat_id")
    Long cat_id;
    @PropertyName("Alcohol By Volume")
    Long alcohol;
    @PropertyName("Category")
    String categorie;
    @PropertyName("brewery_id")
    Long brewery_id;
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

    public Beer(){}

    Beer(String beerName, String brewName, String brewAdress, Long alRate){
        name = beerName;
        brewer = brewName;
        address = brewAdress;
        alcohol = alRate;
    }






}


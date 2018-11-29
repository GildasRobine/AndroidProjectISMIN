package com.robine.gildas.wheretobeer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class DetailsInfoFrag extends Fragment {
    private DatabaseReference beerDatabase = FirebaseDatabase.getInstance().getReference("Beers");
    Map<String, Beer> beers = new HashMap<>();
    //Overriden method onCreateView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Returning the layout file after inflating
        //Change R.layout.tab1 in you classes
        View rootView = inflater.inflate(R.layout.frag_info_layout, container, false);

        Button addButton = rootView.findViewById(R.id.add_btn);
        final EditText beerName = rootView.findViewById(R.id.add_beer_name);
        final EditText brewName = rootView.findViewById(R.id.add_brew_name);
        final EditText brewAdress = rootView.findViewById(R.id.add_brew_address);
        final EditText alRate = rootView.findViewById(R.id.add_alcohol_rate);

        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String beerNa = beerName.getText().toString();
                String brewNa = brewName.getText().toString();
                String brewAd = brewAdress.getText().toString();
                Long rate = Long.valueOf(alRate.getText().toString());
                beers.put("999999", new Beer(beerNa,brewNa,brewAd,rate));
                beerName.getText().clear();
                brewName.getText().clear();
                brewAdress.getText().clear();
                alRate.getText().clear();

            }
        });

        return rootView;
    }
}

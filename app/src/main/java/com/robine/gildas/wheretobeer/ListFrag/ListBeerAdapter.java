package com.robine.gildas.wheretobeer.ListFrag;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.robine.gildas.wheretobeer.Beer;
import com.robine.gildas.wheretobeer.R;

import java.util.ArrayList;

public class ListBeerAdapter extends RecyclerView.Adapter<ListBeerViewHolder> {

    private ArrayList<Beer> beers;
    OnItemClickListener listener;

    public ListBeerAdapter (ArrayList<Beer> beers, OnItemClickListener listener){
        this.beers = beers ;
        this.listener = listener;
    }



    @NonNull
    @Override
    public ListBeerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.listbeer_row,parent,false);
        System.out.println("Nb bières : " +beers.size());
        return new ListBeerViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull ListBeerViewHolder holder, int position) {
        Beer beerToDisplay = this.beers.get(position);
        String abv = beerToDisplay.getAlcohol().toString();
        String category = beerToDisplay.getCategory();
        String brewery = beerToDisplay.getBrewer();
        if(abv.equals("0")){
            abv = "ABV inconnu";
        }else {abv += "%";}
        if(category.equals("")){
            category = "Catégorie inconnue";
        }
        if(brewery.equals("")){
            brewery = "Brasseur inconnu";
        }

        String snippet = abv + ", " + category + ", " + brewery +".";
        holder.subtitle.setText(snippet);
        holder.title.setText(beerToDisplay.getName());

        System.out.println("Row OK");

    }

    @Override
    public int getItemCount() {
        return this.beers.size();
    }
}

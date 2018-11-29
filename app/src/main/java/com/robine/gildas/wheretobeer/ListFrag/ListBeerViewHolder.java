package com.robine.gildas.wheretobeer.ListFrag;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.robine.gildas.wheretobeer.R;

public class ListBeerViewHolder extends RecyclerView.ViewHolder {

    public TextView beerName;
    public TextView alRate;
    public ListBeerViewHolder(View itemView) {
        super(itemView);
        this.beerName = itemView.findViewById(R.id.list_beer);
        this.alRate = itemView.findViewById(R.id.list_alRate);
    }
}

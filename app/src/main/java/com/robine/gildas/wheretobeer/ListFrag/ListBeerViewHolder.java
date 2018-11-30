package com.robine.gildas.wheretobeer.ListFrag;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.robine.gildas.wheretobeer.R;

public class ListBeerViewHolder extends RecyclerView.ViewHolder {

    public TextView title;
    public TextView subtitle;

    public ListBeerViewHolder(View itemView) {
        super(itemView);
        this.title = itemView.findViewById(R.id.titleRow);
        this.subtitle = itemView.findViewById(R.id.subtitle);


    }
}

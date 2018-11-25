package com.robine.gildas.wheretobeer.ListFrag;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.robine.gildas.wheretobeer.R;

public class ListBeerViewHolder extends RecyclerView.ViewHolder {

    public TextView txtName;
    public TextView txtPrice;
    public ListBeerViewHolder(View itemView) {
        super(itemView);
        this.txtName = itemView.findViewById(R.id.txtNameList);
        this.txtPrice = itemView.findViewById(R.id.textPriceList);
    }
}

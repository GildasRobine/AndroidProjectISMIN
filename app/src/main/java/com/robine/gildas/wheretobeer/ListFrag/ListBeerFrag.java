package com.robine.gildas.wheretobeer.ListFrag;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.robine.gildas.wheretobeer.Beer;
import com.robine.gildas.wheretobeer.R;

import java.util.ArrayList;


public class ListBeerFrag extends Fragment {
    //Overriden method onCreateView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Returning the layout file after inflating
        //Change R.layout.tab1 in you classes
        return inflater.inflate(R.layout.fragment_frag_obj1, container, false);
    }
}

package com.robine.gildas.wheretobeer;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.robine.gildas.wheretobeer.ListFrag.ListBeerFrag;

import java.util.ArrayList;

public class StatePagerAdapterFrag extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    ArrayList<Brewery> breweries;
    String camPOs;
    public StatePagerAdapterFrag(FragmentManager fm, int numOfTabs, ArrayList<Brewery> breweries,String camPos) {
        super(fm);
        this.mNumOfTabs = numOfTabs;
        this.breweries = breweries;
        this.camPOs = camPos;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0 :
                ListBeerFrag listBeerFrag = new ListBeerFrag();
                return listBeerFrag;
            case 1 :
                MapsFragment mapsFragment = MapsFragment.newInstance(breweries,camPOs);
                return mapsFragment;
            case 2 :
                AddBeerFrag addBeerFrag = new AddBeerFrag();
                return addBeerFrag;
            case 3 :
                InfoFrag infoFrag = new InfoFrag();
                return infoFrag;
            default :
                return null;
        }


    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}

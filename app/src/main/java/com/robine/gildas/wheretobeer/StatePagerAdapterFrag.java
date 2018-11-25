package com.robine.gildas.wheretobeer;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.robine.gildas.wheretobeer.ListFrag.ListBeerFrag;

public class StatePagerAdapterFrag extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    public StatePagerAdapterFrag(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.mNumOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0 :
                ListBeerFrag listBeerFrag = new ListBeerFrag();
                return listBeerFrag;
            case 1 :
                MapsFragment mapsFragment = new MapsFragment();
                return mapsFragment;
            case 2 :
                DetailsInfoFrag detailsInfoFrag = new DetailsInfoFrag();
                return detailsInfoFrag;
            default :
                return null;
        }


    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}

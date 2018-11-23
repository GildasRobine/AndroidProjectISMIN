package com.robine.gildas.wheretobeer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

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
                FragObj1 fragObj1 = new FragObj1();
                return  fragObj1;
            case 1 :
                MapsFragment mapsFragment = new MapsFragment();
                return mapsFragment;
            case 2 :
                return null;
            default :
                return null;
        }


    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}

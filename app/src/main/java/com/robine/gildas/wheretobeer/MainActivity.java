package com.robine.gildas.wheretobeer;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    StatePagerAdapterFrag statePagerAdapterFrag;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        statePagerAdapterFrag = new StatePagerAdapterFrag(getSupportFragmentManager(),2);
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(statePagerAdapterFrag);

    }
}

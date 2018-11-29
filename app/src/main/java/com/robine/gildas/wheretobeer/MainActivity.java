package com.robine.gildas.wheretobeer;

import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.support.v7.widget.Toolbar;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements  TabLayout.OnTabSelectedListener{
    private TabLayout tabLayout;
    private StatePagerAdapterFrag statePagerAdapterFrag;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //Ajouter la toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //Init tabLayout
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        //Ajout des onglets
        tabLayout.addTab(createTab("List",getDrawable(R.drawable.ic_cheers)));
        tabLayout.addTab(createTab("Map",getDrawable(R.drawable.ic_beerplace)));
        tabLayout.addTab(createTab("Add Beer", getDrawable(R.drawable.ic_information)));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //Init ViewPager
        viewPager = (ViewPager) findViewById(R.id.pager);
        //Create and setting pager adapter
        statePagerAdapterFrag = new StatePagerAdapterFrag(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(statePagerAdapterFrag);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(this);

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    private TabLayout.Tab createTab(String text, Drawable icon){
        TabLayout.Tab tab = tabLayout.newTab().setText(text).setIcon(icon).setCustomView(R.layout.tab_customview);

        // remove imageView bottom margin
        if (tab.getCustomView() != null){
            ImageView imageView = (ImageView) tab.getCustomView().findViewById(android.R.id.icon);
            ViewGroup.MarginLayoutParams lp = ((ViewGroup.MarginLayoutParams) imageView.getLayoutParams());
            lp.bottomMargin = 0;
            imageView.requestLayout();
        }

        return tab;
    }

}

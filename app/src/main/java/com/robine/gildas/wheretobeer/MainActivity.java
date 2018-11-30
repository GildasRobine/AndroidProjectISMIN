package com.robine.gildas.wheretobeer;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.support.v7.widget.Toolbar;
import android.app.SearchManager;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements  TabLayout.OnTabSelectedListener, MapsInterface{
    private TabLayout tabLayout;
    private StatePagerAdapterFrag statePagerAdapterFrag;
    private ViewPager viewPager;
    private ArrayList<Brewery> breweriesAL;
    private FragmentManager fragmentManager;

    //Firebase
    FirebaseDatabase database;
    DatabaseReference breweryRef;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        breweriesAL = new ArrayList<>();
        //Init Firebase
        database = FirebaseDatabase.getInstance();
        breweryRef=database.getReference("Breweries");
        breweryRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                GenericTypeIndicator<ArrayList<Brewery>> breweryListtype = new GenericTypeIndicator<ArrayList<Brewery>>() {};
                breweriesAL = (ArrayList<Brewery>) dataSnapshot.getValue(breweryListtype);
                Intent intent = getIntent();
                String pos  ="0,0";
                Beer beer = (Beer) intent.getSerializableExtra("beer");
                if (beer != null){
                    pos = beer.getCoordinates();

                }
                initLayout(pos);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("Error in updateBreweryList : " + databaseError.getMessage());
                Toast toast = Toast.makeText(getApplicationContext(),"Error in updateBreweryList",Toast.LENGTH_LONG);
                toast.show();


            }
        });





    }

    public void initLayout(String campPos){
        //Ajouter la toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //Init tabLayout
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        //Ajout des onglets
        tabLayout.addTab(createTab("List",getDrawable(R.drawable.ic_cheers)));
        tabLayout.addTab(createTab("Map",getDrawable(R.drawable.ic_beerplace)));
        tabLayout.addTab(createTab("Add Beer", getDrawable(R.drawable.ic_add)));
        tabLayout.addTab(createTab("Info", getDrawable(R.drawable.ic_information)));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //Init ViewPager
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setOffscreenPageLimit(1);
        //Create and setting pager adapter
        statePagerAdapterFrag = new StatePagerAdapterFrag(getSupportFragmentManager(),tabLayout.getTabCount(), breweriesAL,  campPos);
        viewPager.setAdapter(statePagerAdapterFrag);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(this);
        if(!campPos.equals("0,0")){
            viewPager.setCurrentItem(1);
        }
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


    public void toMapsFrag(Beer beer){
        String coordinates = beer.getCoordinates();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        MapsFragment mapsFragment = MapsFragment.newInstance(breweriesAL,coordinates);
        fragmentTransaction.replace(R.id.container, mapsFragment);
        fragmentTransaction.commit();
        System.out.println("Maps Frag Committed");
    }



}

package com.robine.gildas.wheretobeer;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements  TabLayout.OnTabSelectedListener{
    private TabLayout tabLayout;
    private StatePagerAdapterFrag statePagerAdapterFrag;
    private ViewPager viewPager;
    private ArrayList<Brewery> breweriesAL;

    //Firebase
    FirebaseDatabase database;
    DatabaseReference breweryRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        breweriesAL = new ArrayList<>();
        //Init Firebase
        database = FirebaseDatabase.getInstance();
        breweryRef=database.getReference("Breweries");
        breweryRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                GenericTypeIndicator<ArrayList<Brewery>> breweryListtype = new GenericTypeIndicator<ArrayList<Brewery>>() {};
                breweriesAL = (ArrayList<Brewery>) dataSnapshot.getValue(breweryListtype);
                System.out.println("Firebase OK : "+ breweriesAL.size());
                initLayout();
                Toast toast = Toast.makeText(getApplicationContext(),breweriesAL.size()+"",Toast.LENGTH_LONG);
                toast.show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("Error in updateBreweryList : " + databaseError.getMessage());
                Toast toast = Toast.makeText(getApplicationContext(),"Error in updateBreweryList",Toast.LENGTH_LONG);
                toast.show();


            }
        });





    }

    public void initLayout(){
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
        viewPager.setOffscreenPageLimit(1);
        //Create and setting pager adapter
        statePagerAdapterFrag = new StatePagerAdapterFrag(getSupportFragmentManager(),tabLayout.getTabCount(), breweriesAL);
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

    public void updateBreweryList(){
        breweryRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot brewerySnapShot: dataSnapshot.getChildren()){
                    Brewery brewery = brewerySnapShot.getValue(Brewery.class);
                    breweriesAL.add(brewery);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("Error in updateBreweryList : " + databaseError.getMessage());
                 Toast toast = Toast.makeText(getApplicationContext(),"Error in updateBreweryList",Toast.LENGTH_LONG);
                 toast.show();


            }
        });
    }

}

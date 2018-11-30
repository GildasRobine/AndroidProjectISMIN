package com.robine.gildas.wheretobeer.ListFrag;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.robine.gildas.wheretobeer.Beer;
import com.robine.gildas.wheretobeer.DetailsActivity;
import com.robine.gildas.wheretobeer.R;

import java.util.ArrayList;


public class ListBeerFrag extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "list";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private ArrayList<Beer> beers = new ArrayList<>();
    private RecyclerView recyclerView;
    private ListBeerAdapter listBeerAdapter;
    private DatabaseReference beerDatabase = FirebaseDatabase.getInstance().getReference("Beers");
    private int taille;
    private int currentPos;
    public ListBeerFrag() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_frag_obj1, container, false);
        recyclerView = rootView.findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManagerManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManagerManager);
        taille = 20;
        currentPos = 0;
        recyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(linearLayoutManagerManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                taille += 20;
                currentPos +=20;
                loadBeers(taille);

            }
        });


        loadBeers(taille); //On charge les taille premières bières
        this.configureOnClickRV();
        return  rootView;
    }

    public void loadList(){
        listBeerAdapter = new ListBeerAdapter(beers, new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(),DetailsActivity.class);
                intent.putExtra("beer",beers.get(position));
                startActivity(intent);
            }
        }) ;

        listBeerAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(listBeerAdapter);
        recyclerView.scrollToPosition(currentPos);

    }

    public void loadBeers(int taille){

        beerDatabase.limitToFirst(taille).
                addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot){
                        GenericTypeIndicator<ArrayList<Beer>> beerListtype = new GenericTypeIndicator<ArrayList<Beer>>() {};

                        beers = (ArrayList<Beer>) dataSnapshot.getValue(beerListtype);
                        loadList();
                    }



                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }});
    }

    private void configureOnClickRV(){
        ItemClickSupport.addTo(recyclerView, R.layout.listbeer_row)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        Toast.makeText(getContext(), "Position :" + position, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getActivity(), DetailsActivity.class);
                        intent.putExtra("beer",beers.get(position));
                        startActivity(intent);
                    }
                });
    }



}

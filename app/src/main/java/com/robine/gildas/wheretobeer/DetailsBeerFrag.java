package com.robine.gildas.wheretobeer;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MapsInterface} interface
 * to handle interaction events.
 * Use the {@link DetailsBeerFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailsBeerFrag extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "beer";

    // TODO: Rename and change types of parameters
    private Beer beer;
    private TextView beerName;
    private TextView breweryName;
    private TextView category;
    private TextView abv;
    private TextView brewerInfo;
    private ImageButton toMapButton;


    private MapsInterface mListener;

    public DetailsBeerFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param beer Beer.
     * @return A new instance of fragment DetailsBeerFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailsBeerFrag newInstance(Beer beer) {
        DetailsBeerFrag fragment = new DetailsBeerFrag();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, beer);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            beer = (Beer) getArguments().getSerializable(ARG_PARAM1);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_details_beer, container, false);
        beerName = rootView.findViewById(R.id.beerName);
        breweryName = rootView.findViewById(R.id.brewerybeer);
        category = rootView.findViewById(R.id.categorybeer);
        abv = rootView.findViewById(R.id.abvdetails);
        brewerInfo = rootView.findViewById(R.id.brewerinfo);
        toMapButton = (ImageButton) rootView.findViewById(R.id.imageButton);

        if (beer != null){
            beerName.setText(beer.getName());
            breweryName.setText(beer.getBrewer());
            category.setText(beer.getCategory());
            abv.setText(beer.getAlcohol()+"%");
            String brewerInfoStr = "On peut trouver la brasserie " + beer.getBrewer() + " à l'adresse "
                    + beer.getAddress() + ", "
                    + beer.getCity() + ", "
                    +beer.getCountry() +".";
            brewerInfo.setText(brewerInfoStr);
            toMapButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent  intent = new Intent(getActivity(), MainActivity.class);
                    intent.putExtra("beer", beer);
                    startActivity(intent);
                }
            });
        }else{
            Toast.makeText(getContext(),"Beer is null !",Toast.LENGTH_LONG).show();
        }



        return rootView;
    }



   /* @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MapsInterface) {
            mListener = (MapsInterface) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }*/

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

}

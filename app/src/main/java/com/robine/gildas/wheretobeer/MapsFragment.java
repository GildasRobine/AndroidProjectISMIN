package com.robine.gildas.wheretobeer;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.ClusterManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MapsFragment extends Fragment {
    CameraPosition cameraPosition;
    MapView mMapView;
    private GoogleMap googleMap;
    ArrayList<Brewery> breweries;
    private String cameraPos ;
    private static final String ARG_PARAM1 ="breweries";
    private static final String ARG_PARAM2 ="camPosStr";
    private ClusterManager<Brewery> breweryClusterManager;

    //Override method onCreateView
    public static MapsFragment newInstance(ArrayList<Brewery> breweries) {

        Bundle args = new Bundle();
        args.putSerializable("breweries",breweries);
        args.putSerializable(ARG_PARAM2,"0,0");
        MapsFragment fragment = new MapsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static MapsFragment newInstance(ArrayList<Brewery> breweries, String cameraPos) {

        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM2,breweries);
        args.putSerializable(ARG_PARAM2,cameraPos);
        MapsFragment fragment = new MapsFragment();
        fragment.setArguments(args);
        return fragment;
    }

        @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Serializable s = getArguments().getSerializable(ARG_PARAM1);
            breweries = (ArrayList<Brewery>) s ;
            cameraPos = getArguments().getString(ARG_PARAM2);
            System.out.println("Maps instantiate, number of breweries : " + breweries.size());
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.mapsfragment_layout, container, false);

        mMapView = (MapView) rootView.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);
        System.out.println("Maps instantiate, number of breweries : " + breweries.size());
        mMapView.onResume();

        try{
            MapsInitializer.initialize(getActivity().getApplicationContext());
        }catch( Exception e){
            e.printStackTrace();
        }

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;
                setUpClusterer(cameraPos);
                breweryClusterManager.cluster();



                //Marker par défaut

               /* LatLng aix = new LatLng(43.529742, 5.447427 );
                googleMap.addMarker(new MarkerOptions().position(aix).title("Aix en Provence").snippet("Il y fait beau (mais pas tout le temps"));


                // For dropping a marker at a point on the Map
                if (breweries.size() !=0) {

                    for (Brewery brewery : breweries) {
                        String coordinates = brewery.coordinates;

                        if (coordinates != null) {
                            String[] sepLatLng = brewery.coordinates.split(",");
                            LatLng breweryLatLng = new LatLng(Double.valueOf(sepLatLng[0]), Double.valueOf(sepLatLng[1]));
                            MarkerOptions breweryMarker = new MarkerOptions().position(breweryLatLng).title(brewery.brewer).snippet(brewery.address + ", " + brewery.city);
                            googleMap.addMarker(breweryMarker);
                        }
                    }

                }

               // permissions
                if  (ActivityCompat.checkSelfPermission(getContext(),
                        android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                        ActivityCompat.checkSelfPermission(getContext(),
                                android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                mMap.setMyLocationEnabled(true);

                // For zooming automatically to the location of the marker
                cameraPosition = new CameraPosition.Builder().target(aix).zoom(8).build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition)); */
            }
        });

        //Returning the layout file after inflating
        //Change R.layout.tab1 in you classes
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    private void setUpClusterer(String camPos) {
        // Position the map.
        if(!camPos.equals("0,0")){
            String[] sepLatLng = camPos.split(",");
            LatLng camLatLng = new LatLng(Double.valueOf(sepLatLng[0]), Double.valueOf(sepLatLng[1]));
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(camLatLng,10));
        }else{
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(43.529742, 5.447427), 10));
        }


        // Initialize the manager with the context and the map.
        // (Activity extends context, so we can pass 'this' in the constructor.)
        breweryClusterManager = new ClusterManager<Brewery>(getContext(), googleMap);

        // Point the map's listeners at the listeners implemented by the cluster
        // manager.
        googleMap.setOnCameraIdleListener(breweryClusterManager);
        googleMap.setOnMarkerClickListener(breweryClusterManager);
        googleMap.setOnInfoWindowClickListener(breweryClusterManager);
        // Add cluster items (markers) to the cluster manager.
        addItems();
    }

    private void addItems() {
        breweryClusterManager.addItems(breweries);
    }

}

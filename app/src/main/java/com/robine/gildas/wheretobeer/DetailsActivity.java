package com.robine.gildas.wheretobeer;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DetailsActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private Beer beer;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        fragmentManager = getSupportFragmentManager();
        intent = getIntent();
        beer = (Beer) intent.getSerializableExtra("beer");
        toDetailsFrag(beer);

    }


    public void toDetailsFrag(Beer beer){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        DetailsBeerFrag detailsBeerFrag = DetailsBeerFrag.newInstance(beer);
        fragmentTransaction.replace(R.id.container,detailsBeerFrag);
        fragmentTransaction.commit();
    }


}

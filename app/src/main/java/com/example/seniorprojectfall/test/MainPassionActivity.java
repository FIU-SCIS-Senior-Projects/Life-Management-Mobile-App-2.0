package com.example.seniorprojectfall.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.Button;
import java.util.ArrayList;
import it.moondroid.coverflow.components.ui.containers.FeatureCoverFlow;

public class MainPassionActivity extends AppCompatActivity {

    private FeatureCoverFlow coverFlow;
    private CoverFlowAdapterPassion adapter;
    private ArrayList<Passion> activitiesPassion;
    ImageButton androidRightArrowButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_passion);

        androidRightArrowButton = (ImageButton) findViewById(R.id.imageButtonActivityPassion);

        coverFlow = (FeatureCoverFlow) findViewById(R.id.coverflow);

        settingDummyData();
        adapter = new CoverFlowAdapterPassion(this, activitiesPassion);
        coverFlow.setAdapter(adapter);
        coverFlow.setOnScrollPositionListener(onScrollListener());


        androidRightArrowButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "It works",Toast.LENGTH_LONG).show();
                Intent i = new Intent(MainPassionActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        //zoom animation
        Animation zoomAnimation = AnimationUtils.loadAnimation(this,R.anim.zoom);
        androidRightArrowButton.startAnimation(zoomAnimation);
    }

    private FeatureCoverFlow.OnScrollPositionListener onScrollListener() {
        return new FeatureCoverFlow.OnScrollPositionListener() {
            @Override
            public void onScrolledToPosition(int position) {
                Log.v("onScrolledPosition", "position: " + position);
            }

            @Override
            public void onScrolling() {
                Log.i("onScrolling", "scrolling");
            }
        };
    }

    private void settingDummyData() {
        activitiesPassion = new ArrayList<>();
        activitiesPassion.add(new Passion(R.drawable.passion_nature, "Nature"));
        activitiesPassion.add(new Passion(R.drawable.passion_realestate, "Real Estate"));
        activitiesPassion.add(new Passion(R.drawable.passion_learning, "Learning"));


    }
}

package com.example.seniorprojectfall.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import java.util.ArrayList;
import java.util.List;
import it.moondroid.coverflow.components.ui.containers.FeatureCoverFlow;

public class MainGivingBackActivity extends AppCompatActivity {

    private FeatureCoverFlow coverFlow;
    private MainGivingBackAdapter actGivingbackAdapter;
    private List<GivingBack> actGivingbackList = new ArrayList<>();
    static ImageButton nextButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giving_back);

        coverFlow = (FeatureCoverFlow) findViewById(R.id.coverFlowGivingBack);

        initData();

        // Set Adapter for Cover Flow
        actGivingbackAdapter = new MainGivingBackAdapter(this, actGivingbackList);
        coverFlow.setAdapter(actGivingbackAdapter);
        coverFlow.setOnScrollPositionListener(onScrollListener());

        nextButton = (ImageButton) findViewById(R.id.nextToDashboard);
        //Go to sprint setting activity when click on button
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(MainGivingBackActivity.this, SprintSettingActivity.class);
                startActivity(intent);
            }
        });
        nextButton.setVisibility(View.GONE);

        //zoom animation
        Animation zoomAnimation = AnimationUtils.loadAnimation(this,R.anim.zoom);
        nextButton.startAnimation(zoomAnimation);
    }

    private FeatureCoverFlow.OnScrollPositionListener onScrollListener() {
        return new FeatureCoverFlow.OnScrollPositionListener() {
            @Override
            public void onScrolledToPosition(int position) {
                Log.v("GivingBackActivity", "position: " + position);
            }

            @Override
            public void onScrolling(){
                Log.i("GivingBackActivity", "scrolling");
            }
        };
    }


    private void initData() {


        actGivingbackList.add(new GivingBack("Volunteering",R.drawable.give_volunteering));
        actGivingbackList.add(new GivingBack("Teaching",R.drawable.give_teaching));
        actGivingbackList.add(new GivingBack("Counseling",R.drawable.give_counseling));
        actGivingbackList.add(new GivingBack("Mentoring",R.drawable.give_mentoring));
        actGivingbackList.add(new GivingBack("Helping",R.drawable.give_helping));
        actGivingbackList.add(new GivingBack("Nonprofit",R.drawable.give_nonprofit));
        actGivingbackList.add(new GivingBack("Coaching",R.drawable.give_coaching));
        actGivingbackList.add(new GivingBack("Donating",R.drawable.give_donating));

    }
}


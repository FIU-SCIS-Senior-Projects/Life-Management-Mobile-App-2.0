package com.example.natalia.lifemanagementfirst;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

import it.moondroid.coverflow.components.ui.containers.FeatureCoverFlow;

public class GivingBackActivity extends AppCompatActivity {

    private FeatureCoverFlow coverFlow;
    private ActGivingbackAdapter actGivingbackAdapter;
    private List<ActGivingback> actGivingbackList = new ArrayList<>();
    ImageButton nextButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giving_back);

        coverFlow = (FeatureCoverFlow) findViewById(R.id.coverFlowGivingBack);

        initData();

        // Set Adapter for Cover Flow
        actGivingbackAdapter = new ActGivingbackAdapter(this, actGivingbackList);
        coverFlow.setAdapter(actGivingbackAdapter);
        coverFlow.setOnScrollPositionListener(onScrollListener());

        nextButton = (ImageButton) findViewById(R.id.nextToDashboard);

        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(GivingBackActivity.this, TestActivity.class);
                startActivity(intent);
            }
        });
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


        actGivingbackList.add(new ActGivingback("Volunteering",R.drawable.give_volunteering2));
        actGivingbackList.add(new ActGivingback("Teaching",R.drawable.give_teaching));
        actGivingbackList.add(new ActGivingback("Counseling",R.drawable.give_counseling));
        actGivingbackList.add(new ActGivingback("Mentoring",R.drawable.give_mentoring));
        actGivingbackList.add(new ActGivingback("Helping",R.drawable.give_helping));
        actGivingbackList.add(new ActGivingback("Nonprofit",R.drawable.give_nonprofit));
        actGivingbackList.add(new ActGivingback("Coaching",R.drawable.give_coaching));
        actGivingbackList.add(new ActGivingback("Donating",R.drawable.give_donating));

    }
}

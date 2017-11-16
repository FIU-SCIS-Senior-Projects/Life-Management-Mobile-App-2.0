package com.example.natalia.lifemanagementfirst;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class currentCycleActivity extends AppCompatActivity {


    BottomNavigationView navigation;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigation_joy:

                    populatecurrentJoy();
                    item.getIcon().setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN);
                    return true;
                case R.id.navigation_passion:
                    item.getIcon().setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN);
                    populatecurrentPassion();
                    return true;
                case R.id.navigation_contribution:
                    item.getIcon().setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN);
                    populatecurrentContribution();
                    return true;
            }
            return false;
        }



    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_cycle);

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        TextView g = (TextView) findViewById(R.id.textViewcurrentcycle);

        g.setText("\n\n\n"
                + " Activity Name: " +  Dashboard.userActivityJoyid1.activityName + "\n"
                + " Activity Score: " + Dashboard.userActivityJoyid1.activityScore + "\n"
                + " Actual Points:  " + Dashboard.userActivityJoyid1.actualPoints + "\n"
                + " Target Points:  " + Dashboard.userActivityJoyid1.targetPoints + "\n"
                + " Starting Date:  " + Dashboard.startingDateFixed + "\n"
                + " Ending Date:    " +  Dashboard.endingDateFixed + "\n\n"
                + " Activity #2 Name: " +  Dashboard.userActivityJoyid2.activityName + "\n"
                + " Activity #2 Score: " + Dashboard.userActivityJoyid2.activityScore + "\n"
                + " Actual Points:  " + Dashboard.userActivityJoyid2.actualPoints + "\n"
                + " Target Points:  " + Dashboard.userActivityJoyid2.targetPoints + "\n"
                + " Starting Date:  " + Dashboard.startingDateFixed + "\n"
                + " Ending Date:    " +  Dashboard.endingDateFixed);



        ImageButton goBack = (ImageButton) findViewById(R.id.imageButton_currentcycle);

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    } // end of oncreate


    public void populatecurrentJoy(){

        TextView g = (TextView) findViewById(R.id.textViewcurrentcycle);


        g.setText("\n\n\n"
                + " Activity Name: " +  Dashboard.userActivityJoyid1.activityName + "\n"
                + " Activity Score: " + Dashboard.userActivityJoyid1.activityScore + "\n"
                + " Actual Points:  " + Dashboard.userActivityJoyid1.actualPoints + "\n"
                + " Target Points:  " + Dashboard.userActivityJoyid1.targetPoints + "\n"
                + " Starting Date:  " + Dashboard.startingDateFixed + "\n"
                + " Ending Date:    " +  Dashboard.endingDateFixed + "\n\n"
                + " Activity #2 Name: " +  Dashboard.userActivityJoyid2.activityName + "\n"
                + " Activity #2 Score: " + Dashboard.userActivityJoyid2.activityScore + "\n"
                + " Actual Points:  " + Dashboard.userActivityJoyid2.actualPoints + "\n"
                + " Target Points:  " + Dashboard.userActivityJoyid2.targetPoints + "\n"
                + " Starting Date:  " + Dashboard.startingDateFixed + "\n"
                + " Ending Date:    " +  Dashboard.endingDateFixed);

    } //end of method



    public void populatecurrentPassion(){

        TextView g = (TextView) findViewById(R.id.textViewcurrentcycle);

        g.setText("\n\n\n"
                + " Activity Name: " +  Dashboard.userActivityPassionid1.activityName + "\n"
                + " Activity Score: " + Dashboard.userActivityPassionid1.activityScore + "\n"
                + " Actual Points:  " + Dashboard.userActivityPassionid1.actualPoints + "\n"
                + " Target Points:  " + Dashboard.userActivityPassionid1.targetPoints + "\n"
                + " Starting Date:  " + Dashboard.startingDateFixed_passion + "\n"
                + " Ending Date:    " +  Dashboard.endingDateFixed_passion + "\n\n"
                + " Activity #2 Name: " +  Dashboard.userActivityPassionid2.activityName + "\n"
                + " Activity #2 Score: " + Dashboard.userActivityPassionid2.activityScore + "\n"
                + " Actual Points:  " + Dashboard.userActivityPassionid2.actualPoints + "\n"
                + " Target Points:  " + Dashboard.userActivityPassionid2.targetPoints + "\n"
                + " Starting Date:  " + Dashboard.startingDateFixed_passion + "\n"
                + " Ending Date:    " +  Dashboard.endingDateFixed_passion);

    } //end of method

    public void populatecurrentContribution(){

        TextView g = (TextView) findViewById(R.id.textViewcurrentcycle);

        g.setText("\n\n\n"
                + " Activity Name: " +  Dashboard.userActivityContributionid1.activityName + "\n"
                + " Activity Score: " + Dashboard.userActivityContributionid1.activityScore + "\n"
                + " Actual Points:  " + Dashboard.userActivityContributionid1.actualPoints + "\n"
                + " Target Points:  " + Dashboard.userActivityContributionid1.targetPoints + "\n"
                + " Starting Date:  " + Dashboard.startingDateFixed_contribution + "\n"
                + " Ending Date:    " +  Dashboard.endingDateFixed_contribution + "\n\n"
                + " Activity #2 Name: " +  Dashboard.userActivityContributionid2.activityName + "\n"
                + " Activity #2 Score: " + Dashboard.userActivityContributionid2.activityScore + "\n"
                + " Actual Points:  " + Dashboard.userActivityContributionid2.actualPoints + "\n"
                + " Target Points:  " + Dashboard.userActivityContributionid2.targetPoints + "\n"
                + " Starting Date:  " + Dashboard.startingDateFixed_contribution + "\n"
                + " Ending Date:    " +  Dashboard.endingDateFixed_contribution);

    } //end of method

    @Override
    public void onBackPressed() {

        super.onBackPressed();

    }

}

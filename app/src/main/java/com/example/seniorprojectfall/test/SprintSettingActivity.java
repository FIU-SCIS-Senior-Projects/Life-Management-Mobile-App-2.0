package com.example.seniorprojectfall.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class SprintSettingActivity extends FragmentActivity {



    /**
     * Created by Natalia on 10/5/2017.
     */


        private static final int NUM_PAGES = 2; //number of pages to show in Sprint Settings

        private ViewPager mPager;

        private PagerAdapter mPagerAdapter;

        static DatabaseReference databaseReference; // contains reference to "Categories"
        static DatabaseReference databaseRefActivities;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_sprint_setting);

            mPager = (ViewPager) findViewById(R.id.sprintSettingPager);
            mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
            mPager.setAdapter(mPagerAdapter);

            //databaseReference = FirebaseDatabase.getInstance().getReference().child("Categories");
            databaseReference = FirebaseDatabase.getInstance().getReference().child("Categories");
            databaseRefActivities = FirebaseDatabase.getInstance().getReference().child("Activities");

        }

        public static void saveSettings(){

            String userId = "-KwfEoK2VE5o5_yF2pzs";

            //generating unique keys for Categories
            String categoriesId = databaseReference.push().getKey();

            // write to db 2 new objects for Joy activities:

            //generating unique keys for Joy activity 1
            String activities1joyId = databaseRefActivities.push().getKey();
            databaseRefActivities.child(activities1joyId).setValue(null);


            String act1JoyTargetPoints = SprintSettingPage2Fragment.joyAct1goal.getText().toString();
            String act1JoyName = CoverFlowAdapter.n.get(0);

            // set value
            Map<String,String> arAct1Joy = new HashMap<>();
            arAct1Joy.put("activityScore","");
            arAct1Joy.put("actualPoints","");
            arAct1Joy.put("categoryId",categoriesId);
            arAct1Joy.put("name",act1JoyName);
            arAct1Joy.put("sprintDailyPoints","0000000");  // if sprint period 1 week. check sprint period first (1, 2, or 3 weeks)
            arAct1Joy.put("targetPoints",act1JoyTargetPoints);
            arAct1Joy.put("userId",userId);

            databaseRefActivities.child(activities1joyId).setValue(arAct1Joy);





            // write to db 3 new objects for Categories

            databaseReference.child(categoriesId).setValue(null);

            // set value to children one by one
            databaseReference.child(categoriesId).child("userId").setValue(userId);

            //String contribSprintId = databaseReference.child(categoriesId).child("ContributionSprints").push().getKey();

            //ContributionSprint contribSprint = new ContributionSprint (categoriesId,"","","","","","","","","","",userId);

            //String sprintPeriod = SprintSettingPageFragment.sprintPeriod.getText().toString();
            String sprintPeriod = SprintSettingPageFragment.sprintPeriod;

            String startingDate = SprintSettingPageFragment.sprintStartDate.getText().toString();
            startingDate = startingDate.substring(0,2)+ startingDate.substring(3,5) + startingDate.substring(6);

            String endingDate = SprintSettingPageFragment.sprintEndDate.getText().toString();
            endingDate = endingDate.substring(0,2)+ endingDate.substring(3,5) + endingDate.substring(6);

            Map<String,String> arContrSprint = new HashMap<>();
            arContrSprint.put("categoryId",categoriesId);
            arContrSprint.put("endingDate", endingDate);
            arContrSprint.put("goal1","");
            arContrSprint.put("goal2","");
            arContrSprint.put("goal3","");
            arContrSprint.put("goal4","");
            arContrSprint.put("numberOfWeeks",sprintPeriod);
            arContrSprint.put("sprintActivityId1","");
            arContrSprint.put("sprintActivityId2","");
            arContrSprint.put("sprintOverallScore","");
            arContrSprint.put("startingDate", startingDate);
            arContrSprint.put("userId",userId);

            databaseReference.child(categoriesId).child("ContributionSprints").push().setValue(arContrSprint);


            Map<String,String> arJoySprint = new HashMap<>();
            arJoySprint.put("categoryId",categoriesId);
            arJoySprint.put("endingDate", endingDate);
            arJoySprint.put("goal1","");
            arJoySprint.put("goal2","");
            arJoySprint.put("goal3","");
            arJoySprint.put("goal4","");
            arJoySprint.put("numberOfWeeks",sprintPeriod);
            arJoySprint.put("sprintActivityId1",activities1joyId);
            arJoySprint.put("sprintActivityId2","");
            arJoySprint.put("sprintOverallScore","");
            arJoySprint.put("startingDate", startingDate);
            arJoySprint.put("userId",userId);

            databaseReference.child(categoriesId).child("JoySprints").push().setValue(arJoySprint);


            Map<String,String> arPassionSprint = new HashMap<>();
            arPassionSprint.put("categoryId",categoriesId);
            arPassionSprint.put("endingDate", endingDate);
            arPassionSprint.put("goal1","");
            arPassionSprint.put("goal2","");
            arPassionSprint.put("goal3","");
            arPassionSprint.put("goal4","");
            arPassionSprint.put("numberOfWeeks",sprintPeriod);
            arPassionSprint.put("sprintActivityId1","");
            arPassionSprint.put("sprintActivityId2","");
            arPassionSprint.put("sprintOverallScore","");
            arPassionSprint.put("startingDate", startingDate);
            arPassionSprint.put("userId",userId);

            databaseReference.child(categoriesId).child("PassionSprints").push().setValue(arPassionSprint);






            //databaseReference.child(categoriesId).setValue(null, null, null, userId);

            //User currentUser = new User("n@gmail.com","nat1111n","Nat","Natnat","09/09/1990","Natnat1111",false,false,id);

            //databaseReference.child(id).setValue(currentUser);


            //set value
            //databaseReference.child(id).setValue(null);
            //databaseReference.child(id).child("firstName").setValue("Nat");
            //databaseReference.child(id).child("lastName").setValue("Natnat");
            //databaseReference.child(id).child("id").setValue(userId);

            // then update individual children (or set value currentUser to overwrite null)
        }

        @Override
        public void onBackPressed(){
            if (mPager.getCurrentItem() == 0){
                super.onBackPressed();
            }
            else{
                mPager.setCurrentItem(mPager.getCurrentItem()-1);
            }
        }

        private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter{
            public ScreenSlidePagerAdapter(FragmentManager fm){
                super(fm);
            }

            @Override
            public android.support.v4.app.Fragment getItem(int position){

                switch (position){
                    case 0:
                        return new SprintSettingPageFragment();
                    case 1:
                        return new SprintSettingPage2Fragment();

                }
                return null;




            }

            @Override
            public int getCount(){
                return NUM_PAGES;
            }

        }



    }

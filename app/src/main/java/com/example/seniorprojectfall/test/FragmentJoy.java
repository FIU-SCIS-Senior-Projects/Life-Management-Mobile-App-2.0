package com.example.seniorprojectfall.test;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import java.lang.String;
import java.util.*;
import com.firebase.client.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.sccomponents.gauges.ScArcGauge;
import com.sccomponents.gauges.ScGauge;

public class FragmentJoy extends Fragment implements View.OnClickListener{

    public static String test;

    private Button submitGoalbtn;
    private EditText q1;
    private EditText q2;
    private EditText q3;
    private EditText q4;
    DatabaseReference databaseUpdateCategories;


        /**
         * Called to have the fragment instantiate its user interface view.
         * This is optional, and non-graphical fragments can return null (which
         * is the default implementation).  This will be called between
         * {@link #onCreate(Bundle)} and {@link #onActivityCreated(Bundle)}.
         * <p>
         * <p>If you return a View from here, you will later be called in
         * {@link #onDestroyView} when the view is being released.
         *
         * @param inflater           The LayoutInflater object that can be used to inflate
         *                           any views in the fragment,
         * @param container          If non-null, this is the parent view that the fragment's
         *                           UI should be attached to.  The fragment should not add the view itself,
         *                           but this can be used to generate the LayoutParams of the view.
         * @param savedInstanceState If non-null, this fragment is being re-constructed
         *                           from a previous saved state as given here.
         * @return Return the View for the fragment's UI, or null.
         */
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_joy,container,false);



            /*
            for(int i=0;i<d;i++){


                System.out.println("ss4 gol2 " + Dashboard.activitiesJoyMap.get(i).activityScore);
                System.out.println("ss4 gol3 " + Dashboard.activitiesJoyMap.get(i).actualPoints);
                System.out.println("ss4 gol4 " + Dashboard.activitiesJoyMap.get(i).categoryId);
                System.out.println("ss4 sprintid1 " + Dashboard.activitiesJoyMap.get(i).activityName);
                System.out.println("ss4 sprintid2 " + Dashboard.activitiesJoyMap.get(i).sprintDailyPoints);
                System.out.println("ss4 overallscoe " + Dashboard.activitiesJoyMap.get(i).targetPoints);
                System.out.println("ss4 starting" + Dashboard.activitiesJoyMap.get(i).userId);


            }
              */

            final ScrollView scroll = (ScrollView)view.findViewById(R.id.scrollViewId);
            scroll.post(new Runnable() {
               @Override
                public void run() {
                   scroll.fullScroll(ScrollView.FOCUS_UP);
                }
            });

            TextView textStartingDate = (TextView)view.findViewById(R.id.startingDateBtn);
            //System.out.println("DATEEE444 " + test);
            textStartingDate.setText(Dashboard.startingDate);

            TextView textEndingDate = (TextView)view.findViewById(R.id.endingDateBtn);
            textEndingDate.setText(Dashboard.endingDate);

            TextView textAct1 = (TextView)view.findViewById(R.id.textViewAct1);
            // Assign variable textAct1 the value of static variable activity1Joy (from TestActivity.java)
            textAct1.setText(Dashboard.activity1Joy);
            //textAct1.setText("Inner Peace");

            TextView textAct2 = (TextView)view.findViewById(R.id.textViewAct2);
            textAct2.setText("Programming");

            TextView textCategScore = (TextView)view.findViewById(R.id.textViewCategScore);
            textCategScore.setText("Joy Score");

            //Buttons for Activity #1
            final Button btCalendarDay1 = (Button)view.findViewById(R.id.bt1);

            btCalendarDay1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){

                    view.setBackgroundColor(Color.GREEN);

                    //onButtonClick((Button) view);
                }
            });

            Button btCalendarDay2 = (Button)view.findViewById(R.id.bt2);
            btCalendarDay2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    onButtonClick((Button) view);
                }
            });
            Button btCalendarDay3 = (Button)view.findViewById(R.id.bt3);
            btCalendarDay3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    onButtonClick((Button) view);
                }
            });
            Button btCalendarDay4 = (Button)view.findViewById(R.id.bt4);
            btCalendarDay4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    onButtonClick((Button) view);
                }
            });
            Button btCalendarDay5 = (Button)view.findViewById(R.id.bt5);
            btCalendarDay5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    onButtonClick((Button) view);
                }
            });
            Button btCalendarDay6 = (Button)view.findViewById(R.id.bt6);
            btCalendarDay6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    onButtonClick((Button) view);
                }
            });
            Button btCalendarDay7 = (Button)view.findViewById(R.id.bt7);
            btCalendarDay7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    onButtonClick((Button) view);
                }
            });
            Button btCalendarDay8 = (Button)view.findViewById(R.id.bt8);
            btCalendarDay8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    onButtonClick((Button) view);
                }
            });
            Button btCalendarDay9 = (Button)view.findViewById(R.id.bt9);
            btCalendarDay9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    onButtonClick((Button) view);
                }
            });
            Button btCalendarDay10 = (Button)view.findViewById(R.id.bt10);
            btCalendarDay10.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    onButtonClick((Button) view);
                }
            });
            Button btCalendarDay11 = (Button)view.findViewById(R.id.bt11);
            btCalendarDay11.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    onButtonClick((Button) view);
                }
            });
            Button btCalendarDay12 = (Button)view.findViewById(R.id.bt12);
            btCalendarDay12.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    onButtonClick((Button) view);
                }
            });
            Button btCalendarDay13 = (Button)view.findViewById(R.id.bt13);
            btCalendarDay13.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    onButtonClick((Button) view);
                }
            });
            Button btCalendarDay14 = (Button)view.findViewById(R.id.bt14);
            btCalendarDay14.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    onButtonClick((Button) view);
                }
            });

            //Remove buttons(15-21) if sprint is 2 weeks
            Button btCalendarDay15 = (Button)view.findViewById(R.id.bt15);
            btCalendarDay15.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    onButtonClick((Button) view);
                }
            });


            Button btCalendarDay16 = (Button)view.findViewById(R.id.bt16);
            btCalendarDay16.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    onButtonClick((Button) view);
                }
            });


            Button btCalendarDay17 = (Button)view.findViewById(R.id.bt17);
            btCalendarDay17.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    onButtonClick((Button) view);
                }
            });


            Button btCalendarDay18 = (Button)view.findViewById(R.id.bt18);
            btCalendarDay18.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    onButtonClick((Button) view);
                }
            });


            Button btCalendarDay19 = (Button)view.findViewById(R.id.bt19);
            btCalendarDay19.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    onButtonClick((Button) view);
                }
            });


            Button btCalendarDay20 = (Button)view.findViewById(R.id.bt20);
            btCalendarDay20.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    onButtonClick((Button) view);
                }
            });


            Button btCalendarDay21 = (Button)view.findViewById(R.id.bt21);
            btCalendarDay21.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    onButtonClick((Button) view);
                }
            });

            // Buttons for Activity #2
            Button btCalendarDay22 = (Button)view.findViewById(R.id.bt22);
            btCalendarDay22.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    onButtonClick((Button) view);
                }
            });
            Button btCalendarDay23 = (Button)view.findViewById(R.id.bt23);
            btCalendarDay23.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    onButtonClick((Button) view);
                }
            });
            Button btCalendarDay24 = (Button)view.findViewById(R.id.bt24);
            btCalendarDay24.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    onButtonClick((Button) view);
                }
            });
            Button btCalendarDay25 = (Button)view.findViewById(R.id.bt25);
            btCalendarDay25.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    onButtonClick((Button) view);
                }
            });
            Button btCalendarDay26 = (Button)view.findViewById(R.id.bt26);
            btCalendarDay26.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    onButtonClick((Button) view);
                }
            });
            Button btCalendarDay27 = (Button)view.findViewById(R.id.bt27);
            btCalendarDay27.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    onButtonClick((Button) view);
                }
            });
            Button btCalendarDay28 = (Button)view.findViewById(R.id.bt28);
            btCalendarDay28.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    onButtonClick((Button) view);
                }
            });
            Button btCalendarDay29 = (Button)view.findViewById(R.id.bt29);
            btCalendarDay29.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    onButtonClick((Button) view);
                }
            });
            Button btCalendarDay30 = (Button)view.findViewById(R.id.bt30);
            btCalendarDay30.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    onButtonClick((Button) view);
                }
            });
            Button btCalendarDay31 = (Button)view.findViewById(R.id.bt31);
            btCalendarDay31.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    onButtonClick((Button) view);
                }
            });
            Button btCalendarDay32 = (Button)view.findViewById(R.id.bt32);
            btCalendarDay32.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    onButtonClick((Button) view);
                }
            });
            Button btCalendarDay33 = (Button)view.findViewById(R.id.bt33);
            btCalendarDay33.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    onButtonClick((Button) view);
                }
            });
            Button btCalendarDay34 = (Button)view.findViewById(R.id.bt34);
            btCalendarDay34.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    onButtonClick((Button) view);
                }
            });
            Button btCalendarDay35 = (Button)view.findViewById(R.id.bt35);
            btCalendarDay35.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    onButtonClick((Button) view);
                }
            });

            //Remove buttons (36-42) if sprint is 2 weeks
            Button btCalendarDay36 = (Button)view.findViewById(R.id.bt36);
            btCalendarDay36.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    onButtonClick((Button) view);
                }
            });


            Button btCalendarDay37 = (Button)view.findViewById(R.id.bt37);
            btCalendarDay37.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    onButtonClick((Button) view);
                }
            });


            Button btCalendarDay38 = (Button)view.findViewById(R.id.bt38);
            btCalendarDay38.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    onButtonClick((Button) view);
                }
            });


            Button btCalendarDay39 = (Button)view.findViewById(R.id.bt39);
            btCalendarDay39.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    onButtonClick((Button) view);
                }
            });


            Button btCalendarDay40 = (Button)view.findViewById(R.id.bt40);
            btCalendarDay40.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    onButtonClick((Button) view);
                }
            });


            Button btCalendarDay41 = (Button)view.findViewById(R.id.bt41);
            btCalendarDay41.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    onButtonClick((Button) view);
                }
            });


            Button btCalendarDay42 = (Button)view.findViewById(R.id.bt42);
            btCalendarDay42.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    onButtonClick((Button) view);
                }
            });


            int d = Dashboard.activitiesJoyMap.size();  //d must be 2


            int numberOfweeks = Integer.parseInt(Dashboard.numberOfWeeksStatic);

            if(numberOfweeks == 1){

                btCalendarDay8.setVisibility(View.GONE);
                btCalendarDay9.setVisibility(View.GONE);
                btCalendarDay10.setVisibility(View.GONE);
                btCalendarDay11.setVisibility(View.GONE);
                btCalendarDay12.setVisibility(View.GONE);
                btCalendarDay13.setVisibility(View.GONE);
                btCalendarDay14.setVisibility(View.GONE);
                btCalendarDay15.setVisibility(View.GONE);
                btCalendarDay16.setVisibility(View.GONE);
                btCalendarDay17.setVisibility(View.GONE);
                btCalendarDay18.setVisibility(View.GONE);
                btCalendarDay19.setVisibility(View.GONE);
                btCalendarDay20.setVisibility(View.GONE);
                btCalendarDay21.setVisibility(View.GONE);

                //update the current value from start of the week
                String temp = textStartingDate.getText().toString();
                int counter = Integer.parseInt(temp.substring(3,5));

                if(counter>9) {
                    btCalendarDay1.setText(counter + "");
                }else{
                    btCalendarDay1.setText("0" + counter);
                }

                ++counter;
                if(counter>9) {
                    btCalendarDay2.setText(counter + "");
                }else{
                    btCalendarDay2.setText("0" + counter);
                }

                ++counter;
                if(counter>9) {
                    btCalendarDay3.setText(counter + "");
                }else{
                    btCalendarDay3.setText("0" + counter);
                }

                ++counter;
                if(counter>9) {
                    btCalendarDay4.setText(counter + "");
                }else{
                    btCalendarDay4.setText("0" + counter);
                }

                ++counter;
                if(counter>9) {
                    btCalendarDay5.setText(counter + "");
                }else{
                    btCalendarDay5.setText("0" + counter);
                }

                ++counter;
                if(counter>9) {
                    btCalendarDay6.setText(counter + "");
                }else{
                    btCalendarDay6.setText("0" + counter);
                }

                ++counter;
                if(counter>9) {
                    btCalendarDay7.setText(counter + "");
                }else{
                    btCalendarDay7.setText("0" + counter);
                }

                //put the selected days in dashboard
                String sprintJoyDailypoints = Dashboard.activitiesJoyMap.get(0).sprintDailyPoints;

                for(int i=0;i<7;i++){

                    if(sprintJoyDailypoints.substring(0,1).equals("1") && i==0){
                        btCalendarDay1.setBackgroundColor(Color.GREEN);
                    }else if(sprintJoyDailypoints.substring(1,2).equals("1") && i==1){
                        btCalendarDay2.setBackgroundColor(Color.GREEN);
                    }else if(sprintJoyDailypoints.substring(2,3).equals("1") && i==2){
                        btCalendarDay3.setBackgroundColor(Color.GREEN);
                    }else if(sprintJoyDailypoints.substring(3,4).equals("1") && i==3){
                        btCalendarDay4.setBackgroundColor(Color.GREEN);
                    }else if(sprintJoyDailypoints.substring(4,5).equals("1") && i==4){
                        btCalendarDay5.setBackgroundColor(Color.GREEN);
                    }else if(sprintJoyDailypoints.substring(5,6).equals("1") && i==5){
                        btCalendarDay6.setBackgroundColor(Color.GREEN);
                    }else if(sprintJoyDailypoints.substring(6,7).equals("1") && i==6){
                        btCalendarDay7.setBackgroundColor(Color.GREEN);
                    }

                } //end of for

                //second activity
                btCalendarDay29.setVisibility(View.GONE);
                btCalendarDay30.setVisibility(View.GONE);
                btCalendarDay31.setVisibility(View.GONE);
                btCalendarDay32.setVisibility(View.GONE);
                btCalendarDay33.setVisibility(View.GONE);
                btCalendarDay34.setVisibility(View.GONE);
                btCalendarDay35.setVisibility(View.GONE);
                btCalendarDay36.setVisibility(View.GONE);
                btCalendarDay37.setVisibility(View.GONE);
                btCalendarDay38.setVisibility(View.GONE);
                btCalendarDay39.setVisibility(View.GONE);
                btCalendarDay40.setVisibility(View.GONE);
                btCalendarDay41.setVisibility(View.GONE);
                btCalendarDay42.setVisibility(View.GONE);

                //update the current value from start of the week

                counter = Integer.parseInt(temp.substring(3,5));

                if(counter>9) {
                    btCalendarDay22.setText(counter + "");
                }else{
                    btCalendarDay22.setText("0" + counter);
                }

                ++counter;
                if(counter>9) {
                    btCalendarDay23.setText(counter + "");
                }else{
                    btCalendarDay23.setText("0" + counter);
                }

                ++counter;
                if(counter>9) {
                    btCalendarDay24.setText(counter + "");
                }else{
                    btCalendarDay24.setText("0" + counter);
                }

                ++counter;
                if(counter>9) {
                    btCalendarDay25.setText(counter + "");
                }else{
                    btCalendarDay25.setText("0" + counter);
                }

                ++counter;
                if(counter>9) {
                    btCalendarDay26.setText(counter + "");
                }else{
                    btCalendarDay26.setText("0" + counter);
                }

                ++counter;
                if(counter>9) {
                    btCalendarDay27.setText(counter + "");
                }else{
                    btCalendarDay27.setText("0" + counter);
                }

                ++counter;
                if(counter>9) {
                    btCalendarDay28.setText(counter + "");
                }else{
                    btCalendarDay28.setText("0" + counter);
                }

                String sprintJoyDailypoints2 = Dashboard.activitiesJoyMap.get(1).sprintDailyPoints;

                for(int i=0;i<7;i++){

                    if(sprintJoyDailypoints2.substring(0,1).equals("1") && i==0){
                        btCalendarDay22.setBackgroundColor(Color.GREEN);
                    }else if(sprintJoyDailypoints2.substring(1,2).equals("1") && i==1){
                        btCalendarDay23.setBackgroundColor(Color.GREEN);
                    }else if(sprintJoyDailypoints2.substring(2,3).equals("1") && i==2){
                        btCalendarDay24.setBackgroundColor(Color.GREEN);
                    }else if(sprintJoyDailypoints2.substring(3,4).equals("1") && i==3){
                        btCalendarDay25.setBackgroundColor(Color.GREEN);
                    }else if(sprintJoyDailypoints2.substring(4,5).equals("1") && i==4){
                        btCalendarDay26.setBackgroundColor(Color.GREEN);
                    }else if(sprintJoyDailypoints2.substring(5,6).equals("1") && i==5){
                        btCalendarDay27.setBackgroundColor(Color.GREEN);
                    }else if(sprintJoyDailypoints2.substring(6,7).equals("1") && i==6){
                        btCalendarDay28.setBackgroundColor(Color.GREEN);
                    }

                } //end of for



            }else if(numberOfweeks==2){

                btCalendarDay15.setVisibility(View.GONE);
                btCalendarDay16.setVisibility(View.GONE);
                btCalendarDay17.setVisibility(View.GONE);
                btCalendarDay18.setVisibility(View.GONE);
                btCalendarDay19.setVisibility(View.GONE);
                btCalendarDay20.setVisibility(View.GONE);
                btCalendarDay21.setVisibility(View.GONE);

                //update the current value from start of the week
                String temp = textStartingDate.getText().toString();
                int counter = Integer.parseInt(temp.substring(3,5));

                if(counter>9) {
                    btCalendarDay1.setText(counter + "");
                }else{
                    btCalendarDay1.setText("0" + counter);
                }

                ++counter;
                if(counter>9) {
                    btCalendarDay2.setText(counter + "");
                }else{
                    btCalendarDay2.setText("0" + counter);
                }

                ++counter;
                if(counter>9) {
                    btCalendarDay3.setText(counter + "");
                }else{
                    btCalendarDay3.setText("0" + counter);
                }

                ++counter;
                if(counter>9) {
                    btCalendarDay4.setText(counter + "");
                }else{
                    btCalendarDay4.setText("0" + counter);
                }

                ++counter;
                if(counter>9) {
                    btCalendarDay5.setText(counter + "");
                }else{
                    btCalendarDay5.setText("0" + counter);
                }

                ++counter;
                if(counter>9) {
                    btCalendarDay6.setText(counter + "");
                }else{
                    btCalendarDay6.setText("0" + counter);
                }

                ++counter;
                if(counter>9) {
                    btCalendarDay7.setText(counter + "");
                }else{
                    btCalendarDay7.setText("0" + counter);
                }

                ++counter;
                if(counter>9) {
                    btCalendarDay8.setText(counter + "");
                }else{
                    btCalendarDay8.setText("0" + counter);
                }

                ++counter;
                if(counter>9) {
                    btCalendarDay9.setText(counter + "");
                }else{
                    btCalendarDay9.setText("0" + counter);
                }

                ++counter;
                if(counter>9) {
                    btCalendarDay10.setText(counter + "");
                }else{
                    btCalendarDay10.setText("0" + counter);
                }

                ++counter;
                if(counter>9) {
                    btCalendarDay11.setText(counter + "");
                }else{
                    btCalendarDay11.setText("0" + counter);
                }

                ++counter;
                if(counter>9) {
                    btCalendarDay12.setText(counter + "");
                }else{
                    btCalendarDay12.setText("0" + counter);
                }

                ++counter;
                if(counter>9) {
                    btCalendarDay13.setText(counter + "");
                }else{
                    btCalendarDay13.setText("0" + counter);
                }

                ++counter;
                if(counter>9) {
                    btCalendarDay14.setText(counter + "");
                }else{
                    btCalendarDay14.setText("0" + counter);
                }


                //put the selected days in dashboard
                String sprintJoyDailypoints = Dashboard.activitiesJoyMap.get(0).sprintDailyPoints;
                sprintJoyDailypoints = "11001001100100";
                for(int i=0;i<14;i++){

                    if(sprintJoyDailypoints.substring(0,1).equals("1") && i==0){
                        btCalendarDay1.setBackgroundColor(Color.GREEN);
                    }else if(sprintJoyDailypoints.substring(1,2).equals("1") && i==1){
                        btCalendarDay2.setBackgroundColor(Color.GREEN);
                    }else if(sprintJoyDailypoints.substring(2,3).equals("1") && i==2){
                        btCalendarDay3.setBackgroundColor(Color.GREEN);
                    }else if(sprintJoyDailypoints.substring(3,4).equals("1") && i==3){
                        btCalendarDay4.setBackgroundColor(Color.GREEN);
                    }else if(sprintJoyDailypoints.substring(4,5).equals("1") && i==4){
                        btCalendarDay5.setBackgroundColor(Color.GREEN);
                    }else if(sprintJoyDailypoints.substring(5,6).equals("1") && i==5){
                        btCalendarDay6.setBackgroundColor(Color.GREEN);
                    }else if(sprintJoyDailypoints.substring(6,7).equals("1") && i==6){
                        btCalendarDay7.setBackgroundColor(Color.GREEN);
                    }else if(sprintJoyDailypoints.substring(7,8).equals("1") && i==7){
                        btCalendarDay8.setBackgroundColor(Color.GREEN);
                    }else if(sprintJoyDailypoints.substring(8,9).equals("1") && i==8){
                        btCalendarDay9.setBackgroundColor(Color.GREEN);
                    }else if(sprintJoyDailypoints.substring(9,10).equals("1") && i==9){
                        btCalendarDay10.setBackgroundColor(Color.GREEN);
                    }else if(sprintJoyDailypoints.substring(10,11).equals("1") && i==10){
                        btCalendarDay11.setBackgroundColor(Color.GREEN);
                    }else if(sprintJoyDailypoints.substring(11,12).equals("1") && i==11){
                        btCalendarDay12.setBackgroundColor(Color.GREEN);
                    }else if(sprintJoyDailypoints.substring(12,13).equals("1") && i==12){
                        btCalendarDay13.setBackgroundColor(Color.GREEN);
                    }else if(sprintJoyDailypoints.substring(13,14).equals("1") && i==13){
                        btCalendarDay14.setBackgroundColor(Color.GREEN);
                    }

                } //end of for


                btCalendarDay36.setVisibility(View.GONE);
                btCalendarDay37.setVisibility(View.GONE);
                btCalendarDay38.setVisibility(View.GONE);
                btCalendarDay39.setVisibility(View.GONE);
                btCalendarDay40.setVisibility(View.GONE);
                btCalendarDay41.setVisibility(View.GONE);
                btCalendarDay42.setVisibility(View.GONE);

                counter = Integer.parseInt(temp.substring(3,5));

                if(counter>9) {
                    btCalendarDay22.setText(counter + "");
                }else{
                    btCalendarDay22.setText("0" + counter);
                }

                ++counter;
                if(counter>9) {
                    btCalendarDay23.setText(counter + "");
                }else{
                    btCalendarDay23.setText("0" + counter);
                }

                ++counter;
                if(counter>9) {
                    btCalendarDay24.setText(counter + "");
                }else{
                    btCalendarDay24.setText("0" + counter);
                }

                ++counter;
                if(counter>9) {
                    btCalendarDay25.setText(counter + "");
                }else{
                    btCalendarDay25.setText("0" + counter);
                }

                ++counter;
                if(counter>9) {
                    btCalendarDay26.setText(counter + "");
                }else{
                    btCalendarDay26.setText("0" + counter);
                }

                ++counter;
                if(counter>9) {
                    btCalendarDay27.setText(counter + "");
                }else{
                    btCalendarDay27.setText("0" + counter);
                }

                ++counter;
                if(counter>9) {
                    btCalendarDay28.setText(counter + "");
                }else{
                    btCalendarDay28.setText("0" + counter);
                }


                ++counter;
                if(counter>9) {
                    btCalendarDay29.setText(counter + "");
                }else{
                    btCalendarDay29.setText("0" + counter);
                }

                ++counter;
                if(counter>9) {
                    btCalendarDay30.setText(counter + "");
                }else{
                    btCalendarDay30.setText("0" + counter);
                }

                ++counter;
                if(counter>9) {
                    btCalendarDay31.setText(counter + "");
                }else{
                    btCalendarDay31.setText("0" + counter);
                }

                ++counter;
                if(counter>9) {
                    btCalendarDay32.setText(counter + "");
                }else{
                    btCalendarDay32.setText("0" + counter);
                }

                ++counter;
                if(counter>9) {
                    btCalendarDay33.setText(counter + "");
                }else{
                    btCalendarDay33.setText("0" + counter);
                }

                ++counter;
                if(counter>9) {
                    btCalendarDay34.setText(counter + "");
                }else{
                    btCalendarDay34.setText("0" + counter);
                }

                ++counter;
                if(counter>9) {
                    btCalendarDay35.setText(counter + "");
                }else{
                    btCalendarDay35.setText("0" + counter);
                }

                //put the selected days in dashboard
                String sprintJoyDailypoints2 = Dashboard.activitiesJoyMap.get(1).sprintDailyPoints;
                sprintJoyDailypoints2 = "11001001100100";
                for(int i=0;i<14;i++){

                    if(sprintJoyDailypoints2.substring(0,1).equals("1") && i==0){
                        btCalendarDay22.setBackgroundColor(Color.GREEN);
                    }else if(sprintJoyDailypoints2.substring(1,2).equals("1") && i==1){
                        btCalendarDay23.setBackgroundColor(Color.GREEN);
                    }else if(sprintJoyDailypoints2.substring(2,3).equals("1") && i==2){
                        btCalendarDay24.setBackgroundColor(Color.GREEN);
                    }else if(sprintJoyDailypoints2.substring(3,4).equals("1") && i==3){
                        btCalendarDay25.setBackgroundColor(Color.GREEN);
                    }else if(sprintJoyDailypoints2.substring(4,5).equals("1") && i==4){
                        btCalendarDay26.setBackgroundColor(Color.GREEN);
                    }else if(sprintJoyDailypoints2.substring(5,6).equals("1") && i==5){
                        btCalendarDay27.setBackgroundColor(Color.GREEN);
                    }else if(sprintJoyDailypoints2.substring(6,7).equals("1") && i==6){
                        btCalendarDay28.setBackgroundColor(Color.GREEN);
                    }else if(sprintJoyDailypoints2.substring(7,8).equals("1") && i==7){
                        btCalendarDay29.setBackgroundColor(Color.GREEN);
                    }else if(sprintJoyDailypoints2.substring(8,9).equals("1") && i==8){
                        btCalendarDay30.setBackgroundColor(Color.GREEN);
                    }else if(sprintJoyDailypoints2.substring(9,10).equals("1") && i==9){
                        btCalendarDay31.setBackgroundColor(Color.GREEN);
                    }else if(sprintJoyDailypoints2.substring(10,11).equals("1") && i==10){
                        btCalendarDay32.setBackgroundColor(Color.GREEN);
                    }else if(sprintJoyDailypoints2.substring(11,12).equals("1") && i==11){
                        btCalendarDay33.setBackgroundColor(Color.GREEN);
                    }else if(sprintJoyDailypoints2.substring(12,13).equals("1") && i==12){
                        btCalendarDay34.setBackgroundColor(Color.GREEN);
                    }else if(sprintJoyDailypoints2.substring(13,14).equals("1") && i==13){
                        btCalendarDay35.setBackgroundColor(Color.GREEN);
                    }

                } //end of for



            }else{
                //display all of them

                //update the current value from start of the week
                String temp = textStartingDate.getText().toString();
                int counter = Integer.parseInt(temp.substring(3,5));

                if(counter>9) {
                    btCalendarDay1.setText(counter + "");
                }else{
                    btCalendarDay1.setText("0" + counter);
                }

                ++counter;
                if(counter>9) {
                    btCalendarDay2.setText(counter + "");
                }else{
                    btCalendarDay2.setText("0" + counter);
                }

                ++counter;
                if(counter>9) {
                    btCalendarDay3.setText(counter + "");
                }else{
                    btCalendarDay3.setText("0" + counter);
                }

                ++counter;
                if(counter>9) {
                    btCalendarDay4.setText(counter + "");
                }else{
                    btCalendarDay4.setText("0" + counter);
                }

                ++counter;
                if(counter>9) {
                    btCalendarDay5.setText(counter + "");
                }else{
                    btCalendarDay5.setText("0" + counter);
                }

                ++counter;
                if(counter>9) {
                    btCalendarDay6.setText(counter + "");
                }else{
                    btCalendarDay6.setText("0" + counter);
                }

                ++counter;
                if(counter>9) {
                    btCalendarDay7.setText(counter + "");
                }else{
                    btCalendarDay7.setText("0" + counter);
                }


                ++counter;
                if(counter>9) {
                    btCalendarDay8.setText(counter + "");
                }else{
                    btCalendarDay8.setText("0" + counter);
                }

                ++counter;
                if(counter>9) {
                    btCalendarDay9.setText(counter + "");
                }else{
                    btCalendarDay9.setText("0" + counter);
                }

                ++counter;
                if(counter>9) {
                    btCalendarDay10.setText(counter + "");
                }else{
                    btCalendarDay10.setText("0" + counter);
                }

                ++counter;
                if(counter>9) {
                    btCalendarDay11.setText(counter + "");
                }else{
                    btCalendarDay11.setText("0" + counter);
                }

                ++counter;
                if(counter>9) {
                    btCalendarDay12.setText(counter + "");
                }else{
                    btCalendarDay12.setText("0" + counter);
                }

                ++counter;
                if(counter>9) {
                    btCalendarDay13.setText(counter + "");
                }else{
                    btCalendarDay13.setText("0" + counter);
                }

                ++counter;
                if(counter>9) {
                    btCalendarDay14.setText(counter + "");
                }else{
                    btCalendarDay14.setText("0" + counter);
                }


                ++counter;
                if(counter>9) {
                    btCalendarDay15.setText(counter + "");
                }else{
                    btCalendarDay15.setText("0" + counter);
                }

                ++counter;
                if(counter>9) {
                    btCalendarDay16.setText(counter + "");
                }else{
                    btCalendarDay16.setText("0" + counter);
                }

                ++counter;
                if(counter>9) {
                    btCalendarDay17.setText(counter + "");
                }else{
                    btCalendarDay17.setText("0" + counter);
                }

                ++counter;
                if(counter>9) {
                    btCalendarDay18.setText(counter + "");
                }else{
                    btCalendarDay18.setText("0" + counter);
                }

                ++counter;
                if(counter>9) {
                    btCalendarDay19.setText(counter + "");
                }else{
                    btCalendarDay19.setText("0" + counter);
                }

                ++counter;
                if(counter>9) {
                    btCalendarDay20.setText(counter + "");
                }else{
                    btCalendarDay20.setText("0" + counter);
                }

                ++counter;
                if(counter>9) {
                    btCalendarDay21.setText(counter + "");
                }else{
                    btCalendarDay21.setText("0" + counter);
                }


                //second activity


                counter = Integer.parseInt(temp.substring(3,5));

                if(counter>9) {
                    btCalendarDay22.setText(counter + "");
                }else{
                    btCalendarDay22.setText("0" + counter);
                }

                ++counter;
                if(counter>9) {
                    btCalendarDay23.setText(counter + "");
                }else{
                    btCalendarDay23.setText("0" + counter);
                }

                ++counter;
                if(counter>9) {
                    btCalendarDay24.setText(counter + "");
                }else{
                    btCalendarDay24.setText("0" + counter);
                }

                ++counter;
                if(counter>9) {
                    btCalendarDay25.setText(counter + "");
                }else{
                    btCalendarDay25.setText("0" + counter);
                }

                ++counter;
                if(counter>9) {
                    btCalendarDay26.setText(counter + "");
                }else{
                    btCalendarDay26.setText("0" + counter);
                }

                ++counter;
                if(counter>9) {
                    btCalendarDay27.setText(counter + "");
                }else{
                    btCalendarDay27.setText("0" + counter);
                }

                ++counter;
                if(counter>9) {
                    btCalendarDay28.setText(counter + "");
                }else{
                    btCalendarDay28.setText("0" + counter);
                }


                ++counter;
                if(counter>9) {
                    btCalendarDay29.setText(counter + "");
                }else{
                    btCalendarDay29.setText("0" + counter);
                }

                ++counter;
                if(counter>9) {
                    btCalendarDay30.setText(counter + "");
                }else{
                    btCalendarDay30.setText("0" + counter);
                }

                ++counter;
                if(counter>9) {
                    btCalendarDay31.setText(counter + "");
                }else{
                    btCalendarDay31.setText("0" + counter);
                }

                ++counter;
                if(counter>9) {
                    btCalendarDay32.setText(counter + "");
                }else{
                    btCalendarDay32.setText("0" + counter);
                }

                ++counter;
                if(counter>9) {
                    btCalendarDay33.setText(counter + "");
                }else{
                    btCalendarDay33.setText("0" + counter);
                }

                ++counter;
                if(counter>9) {
                    btCalendarDay34.setText(counter + "");
                }else{
                    btCalendarDay34.setText("0" + counter);
                }

                ++counter;
                if(counter>9) {
                    btCalendarDay35.setText(counter + "");
                }else{
                    btCalendarDay35.setText("0" + counter);
                }

                ++counter;
                if(counter>9) {
                    btCalendarDay36.setText(counter + "");
                }else{
                    btCalendarDay36.setText("0" + counter);
                }


                ++counter;
                if(counter>9) {
                    btCalendarDay37.setText(counter + "");
                }else{
                    btCalendarDay37.setText("0" + counter);
                }

                ++counter;
                if(counter>9) {
                    btCalendarDay38.setText(counter + "");
                }else{
                    btCalendarDay38.setText("0" + counter);
                }

                ++counter;
                if(counter>9) {
                    btCalendarDay39.setText(counter + "");
                }else{
                    btCalendarDay39.setText("0" + counter);
                }

                ++counter;
                if(counter>9) {
                    btCalendarDay40.setText(counter + "");
                }else{
                    btCalendarDay40.setText("0" + counter);
                }

                ++counter;
                if(counter>9) {
                    btCalendarDay41.setText(counter + "");
                }else{
                    btCalendarDay41.setText("0" + counter);
                }

                ++counter;
                if(counter>9) {
                    btCalendarDay42.setText(counter + "");
                }else{
                    btCalendarDay42.setText("0" + counter);
                }


            }



            // Find the components for gaugeJoyActivity1
            final TextView counterJoyActivity1 = (TextView) view.findViewById(R.id.counter_joy_activity1);
            ScArcGauge gaugeJoyActivity1 = (ScArcGauge) view.findViewById(R.id.gauge_joy_activity1);

            // Set the features stroke cap style to rounded
            gaugeJoyActivity1.findFeature(ScArcGauge.BASE_IDENTIFIER)
                    .getPainter().setStrokeCap(Paint.Cap.ROUND);
            gaugeJoyActivity1.findFeature(ScArcGauge.PROGRESS_IDENTIFIER)
                    .getPainter().setStrokeCap(Paint.Cap.ROUND);

            // If you set the value from the xml that not produce an event so I will change the
            // value from code.
            gaugeJoyActivity1.setHighValue(60);

            // Each time I will change the value I must write it inside the counter text.
            gaugeJoyActivity1.setOnEventListener(new ScGauge.OnEventListener() {
                @Override
                public void onValueChange(float lowValue, float highValue) {
                    counterJoyActivity1.setText((int) highValue + "%");

                }
            });

            // Find the components for gaugeJoyActivity1
            final TextView counterJoyActivity2 = (TextView) view.findViewById(R.id.counter_joy_activity2);
            ScArcGauge gaugeJoyActivity2 = (ScArcGauge) view.findViewById(R.id.gauge_joy_activity2);

            // Set the features stroke cap style to rounded
            gaugeJoyActivity2.findFeature(ScArcGauge.BASE_IDENTIFIER)
                    .getPainter().setStrokeCap(Paint.Cap.ROUND);
            gaugeJoyActivity2.findFeature(ScArcGauge.PROGRESS_IDENTIFIER)
                    .getPainter().setStrokeCap(Paint.Cap.ROUND);

            // If you set the value from the xml that not produce an event so I will change the
            // value from code.
            gaugeJoyActivity2.setHighValue(60);

            // Each time I will change the value I must write it inside the counter text.
            gaugeJoyActivity2.setOnEventListener(new ScGauge.OnEventListener() {
                @Override
                public void onValueChange(float lowValue, float highValue) {
                    counterJoyActivity2.setText((int) highValue + "%");

                }
            });

            // Find the components for gaugeJoyScore
            final TextView counterJoyScore = (TextView) view.findViewById(R.id.counter_joy_score);
            ScArcGauge gaugeJoyScore = (ScArcGauge) view.findViewById(R.id.gauge_joy_score);

            // Set the features stroke cap style to rounded
            gaugeJoyScore.findFeature(ScArcGauge.BASE_IDENTIFIER)
                    .getPainter().setStrokeCap(Paint.Cap.ROUND);
            gaugeJoyScore.findFeature(ScArcGauge.PROGRESS_IDENTIFIER)
                    .getPainter().setStrokeCap(Paint.Cap.ROUND);

            // If you set the value from the xml that not produce an event so I will change the
            // value from code.
            gaugeJoyScore.setHighValue(60);

            // Each time I will change the value I must write it inside the counter text.
            gaugeJoyScore.setOnEventListener(new ScGauge.OnEventListener() {
                @Override
                public void onValueChange(float lowValue, float highValue) {
                    counterJoyScore.setText((int) highValue + "%");

                }
            });

            // Find the components for gaugeLifeScore
            final TextView counterLifeScore = (TextView) view.findViewById(R.id.counter_life_score);
            ScArcGauge gaugeLifeScore = (ScArcGauge) view.findViewById(R.id.gauge_life_score);

            // Set the features stroke cap style to rounded
            gaugeLifeScore.findFeature(ScArcGauge.BASE_IDENTIFIER)
                    .getPainter().setStrokeCap(Paint.Cap.ROUND);
            gaugeLifeScore.findFeature(ScArcGauge.PROGRESS_IDENTIFIER)
                    .getPainter().setStrokeCap(Paint.Cap.ROUND);

            // If you set the value from the xml that not produce an event so I will change the
            // value from code.
            gaugeLifeScore.setHighValue(60);

            // Each time I will change the value I must write it inside the counter text.
            gaugeLifeScore.setOnEventListener(new ScGauge.OnEventListener() {
                @Override
                public void onValueChange(float lowValue, float highValue) {
                    counterLifeScore.setText((int) highValue + "%");
                }
            });


            //GOALS (questionaire)


            q1 = (EditText)view.findViewById(R.id.q1Goals);
            q2 = (EditText)view.findViewById(R.id.q2Goals);
            q3 = (EditText)view.findViewById(R.id.q3Goals);
            q4 = (EditText)view.findViewById(R.id.q4Goals);


            submitGoalbtn = (Button)view.findViewById(R.id.submitButtonGoals);

            submitGoalbtn.setOnClickListener(this);

            return view;

        }

        public void onButtonClick(Button view){
            view.setBackgroundColor(Color.GREEN);
        }

        @Override
        public void onClick(View view) {

            if(view == submitGoalbtn){
                checkAnswers();
            }

        } //end of onClick



        public void checkAnswers(){

            String answer1 = q1.getText().toString();
            String answer2 = q2.getText().toString();
            String answer3 = q3.getText().toString();
            String answer4 = q4.getText().toString();


            if(answer1.length()>180){
                Toast.makeText(getActivity().getApplicationContext(),"Your first answer has " +
                        answer1.length() + "words. Answers must contain at most 180 characters",Toast.LENGTH_LONG).show();
                        return;
            } else if(answer2.length()>180){
                Toast.makeText(getActivity().getApplicationContext(),"Your second answer have " +
                        answer2.length() + "words. Answers must contain at most 180 characters",Toast.LENGTH_LONG).show();
                        return;
            }else if(answer3.length()>180){
                Toast.makeText(getActivity().getApplicationContext(),"Your third answer have " +
                        answer3.length() + "words. Answers must contain at most 180 characters",Toast.LENGTH_LONG).show();
                        return;
            }else if(answer4.length()>180){

                Toast.makeText(getActivity().getApplicationContext(),"Your last answer has " +
                        answer4.length() + "words. Answers must contain at most 180 characters",Toast.LENGTH_LONG).show();
                        return;
            }else{

                //good to go save to db
                Toast.makeText(getActivity().getApplicationContext(),"Goal Submitted",Toast.LENGTH_LONG).show();

                databaseUpdateCategories = FirebaseDatabase.getInstance().getReference("Categories");

                databaseUpdateCategories.child(Dashboard.currentcategoryid).child("JoySprints").child(Dashboard.sprintJoyid).child("goal1").setValue(q1.getText().toString());
                databaseUpdateCategories.child(Dashboard.currentcategoryid).child("JoySprints").child(Dashboard.sprintJoyid).child("goal2").setValue(q2.getText().toString());
                databaseUpdateCategories.child(Dashboard.currentcategoryid).child("JoySprints").child(Dashboard.sprintJoyid).child("goal3").setValue(q3.getText().toString());
                databaseUpdateCategories.child(Dashboard.currentcategoryid).child("JoySprints").child(Dashboard.sprintJoyid).child("goal4").setValue(q4.getText().toString());

            }

         }


        /**
         * Called immediately after {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}
         * has returned, but before any saved state has been restored in to the view.
         * This gives subclasses a chance to initialize themselves once
         * they know their view hierarchy has been completely created.  The fragment's
         * view hierarchy is not however attached to its parent at this point.
         *
         * @param view               The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
         * @param savedInstanceState If non-null, this fragment is being re-constructed
         */
        @Override
        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

        }


    }

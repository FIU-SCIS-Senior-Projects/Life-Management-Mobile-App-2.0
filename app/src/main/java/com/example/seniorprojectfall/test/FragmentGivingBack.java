package com.example.seniorprojectfall.test;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sccomponents.gauges.ScArcGauge;
import com.sccomponents.gauges.ScGauge;


public class FragmentGivingBack extends Fragment implements View.OnClickListener{


    private Button submitGoalbtn_Contribution;
    private EditText q1_contribution;
    private EditText q2_contribution;
    private EditText q3_contribution;
    private EditText q4_contribution;
    DatabaseReference databaseUpdateCategories;
    DatabaseReference databaseUpdateActivities;

    TextView textActual1;
    TextView textActual2;

    /**
     * Created by Natalia on 9/20/2017.
     */

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

            final ScrollView scroll = (ScrollView)view.findViewById(R.id.scrollViewId);
            scroll.post(new Runnable() {
                @Override
                public void run() {
                    scroll.fullScroll(ScrollView.FOCUS_UP);
                }
            });

            TextView textStartingDate = (TextView)view.findViewById(R.id.startingDateBtn);
            //System.out.println("DATEEE444 " + test);
            textStartingDate.setText(Dashboard.startingDateFixed_contribution);

            TextView textEndingDate = (TextView)view.findViewById(R.id.endingDateBtn);
            textEndingDate.setText(Dashboard.endingDateFixed_contribution);

            TextView textAct1 = (TextView)view.findViewById(R.id.textViewAct1);
            // Assign variable textAct1 the value of static variable activity1Joy (from TestActivity.java)
            textAct1.setText(Dashboard.userActivityContributionid1.activityName);
            //textAct1.setText("Inner Peace");

            TextView textAct2 = (TextView)view.findViewById(R.id.textViewAct2);
            textAct2.setText(Dashboard.userActivityContributionid2.activityName);

            TextView textCategScore = (TextView)view.findViewById(R.id.textViewCategScore);
            textCategScore.setText("Joy Score");

            textActual1 = (TextView)view.findViewById(R.id.textViewActual1);
            textActual1.setText(Dashboard.userActivityContributionid1.actualPoints);

            textActual2 = (TextView)view.findViewById(R.id.textViewActual2);
            textActual2.setText(Dashboard.userActivityContributionid2.actualPoints);

            TextView textTarget1 = (TextView)view.findViewById(R.id.textViewTarget1);
            textTarget1.setText(Dashboard.userActivityContributionid1.targetPoints);

            TextView textTarget2 = (TextView)view.findViewById(R.id.textViewTarget2);
            textTarget2.setText(Dashboard.userActivityContributionid2.targetPoints);



            //Buttons for Activity #1
            final Button btCalendarDay1 = (Button)view.findViewById(R.id.bt1);

            btCalendarDay1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){

                    String temp = Dashboard.userActivityContributionid1.sprintDailyPoints;

                    int color = 0;
                    Drawable backgroundcolor = btCalendarDay1.getBackground();
                    if (backgroundcolor instanceof ColorDrawable) {
                        color = ((ColorDrawable)backgroundcolor).getColor();
                    }

                    if(color == Color.GREEN){

                        //means its already green so the user wants to DESELECT so update the database
                        String modify1 = "0"+temp.substring(1);

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid1.activityid).child("sprintDailyPoints").setValue(modify1);
                        btCalendarDay1.setBackgroundColor(Color.LTGRAY);

                        Dashboard.userActivityContributionid1.sprintDailyPoints = modify1; //maintain a copy global instead of accessing to database

                        String Modifystr = modify1+"";

                        int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                        Dashboard.userActivityContributionid1.actualPoints = countOnes+"";
                        setTextView(countOnes+"");

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid1.activityid).child("actualPoints").setValue(countOnes+"");


                    }else{

                        //means the user click this button for first time
                        String modify = "1"+temp.substring(1);

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid1.activityid).child("sprintDailyPoints").setValue(modify);
                        btCalendarDay1.setBackgroundColor(Color.GREEN);

                        Dashboard.userActivityContributionid1.sprintDailyPoints = modify;

                        String Modifystr = modify+"";

                        int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                        Dashboard.userActivityContributionid1.actualPoints = countOnes+"";
                        setTextView(countOnes+"");

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid1.activityid).child("actualPoints").setValue(countOnes+"");
                    }

                    //11001001100100
                }
            });

            final Button btCalendarDay2 = (Button)view.findViewById(R.id.bt2);
            btCalendarDay2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){

                    String temp = Dashboard.userActivityContributionid1.sprintDailyPoints;

                    int color = 0;
                    Drawable backgroundcolor = btCalendarDay2.getBackground();
                    if (backgroundcolor instanceof ColorDrawable) {
                        color = ((ColorDrawable)backgroundcolor).getColor();
                    }

                    //11001001100100
                    if(color == Color.GREEN){

                        //means its already green so the user wants to DESELECT so update the database
                        String modify1 = temp.substring(0,1)+"0"+temp.substring(2);

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid1.activityid).child("sprintDailyPoints").setValue(modify1);
                        btCalendarDay2.setBackgroundColor(Color.LTGRAY);
                        Dashboard.userActivityContributionid1.sprintDailyPoints = modify1;

                        String Modifystr = modify1+"";

                        int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                        Dashboard.userActivityContributionid1.actualPoints = countOnes+"";
                        setTextView(countOnes+"");

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid1.activityid).child("actualPoints").setValue(countOnes+"");


                    }else{

                        //means the user click this button for first time
                        String modify = temp.substring(0,1)+"1"+temp.substring(2);

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid1.activityid).child("sprintDailyPoints").setValue(modify);
                        btCalendarDay2.setBackgroundColor(Color.GREEN);
                        Dashboard.userActivityContributionid1.sprintDailyPoints = modify;

                        String Modifystr = modify+"";

                        int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                        Dashboard.userActivityContributionid1.actualPoints = countOnes+"";
                        setTextView(countOnes+"");

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid1.activityid).child("actualPoints").setValue(countOnes+"");
                    }

                }
            });
            final Button btCalendarDay3 = (Button)view.findViewById(R.id.bt3);
            btCalendarDay3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){

                    String temp = Dashboard.userActivityContributionid1.sprintDailyPoints;

                    int color = 0;
                    Drawable backgroundcolor = btCalendarDay3.getBackground();
                    if (backgroundcolor instanceof ColorDrawable) {
                        color = ((ColorDrawable)backgroundcolor).getColor();
                    }

                    //11001001100100
                    if(color == Color.GREEN){

                        //means its already green so the user wants to DESELECT so update the database
                        String modify1 = temp.substring(0,2)+"0"+temp.substring(3);

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid1.activityid).child("sprintDailyPoints").setValue(modify1);
                        btCalendarDay3.setBackgroundColor(Color.LTGRAY);
                        Dashboard.userActivityContributionid1.sprintDailyPoints = modify1;

                        String Modifystr = modify1+"";

                        int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                        Dashboard.userActivityContributionid1.actualPoints = countOnes+"";
                        setTextView(countOnes+"");

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid1.activityid).child("actualPoints").setValue(countOnes+"");


                    }else{

                        //means the user click this button for first time
                        String modify = temp.substring(0,2)+"1"+temp.substring(3);

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid1.activityid).child("sprintDailyPoints").setValue(modify);
                        btCalendarDay3.setBackgroundColor(Color.GREEN);
                        Dashboard.userActivityContributionid1.sprintDailyPoints = modify;

                        String Modifystr = modify+"";

                        int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                        Dashboard.userActivityContributionid1.actualPoints = countOnes+"";
                        setTextView(countOnes+"");

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid1.activityid).child("actualPoints").setValue(countOnes+"");
                    }



                }
            });
            final Button btCalendarDay4 = (Button)view.findViewById(R.id.bt4);
            btCalendarDay4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){

                    String temp = Dashboard.userActivityContributionid1.sprintDailyPoints;

                    int color = 0;
                    Drawable backgroundcolor = btCalendarDay4.getBackground();
                    if (backgroundcolor instanceof ColorDrawable) {
                        color = ((ColorDrawable)backgroundcolor).getColor();
                    }

                    //11001001100100
                    if(color == Color.GREEN){

                        //means its already green so the user wants to DESELECT so update the database
                        String modify1 = temp.substring(0,3)+"0"+temp.substring(4);

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid1.activityid).child("sprintDailyPoints").setValue(modify1);
                        btCalendarDay4.setBackgroundColor(Color.LTGRAY);
                        Dashboard.userActivityContributionid1.sprintDailyPoints = modify1;

                        String Modifystr = modify1+"";

                        int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                        Dashboard.userActivityContributionid1.actualPoints = countOnes+"";
                        setTextView(countOnes+"");

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid1.activityid).child("actualPoints").setValue(countOnes+"");


                    }else{

                        //means the user click this button for first time
                        String modify = temp.substring(0,3)+"1"+temp.substring(4);

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid1.activityid).child("sprintDailyPoints").setValue(modify);
                        btCalendarDay4.setBackgroundColor(Color.GREEN);
                        Dashboard.userActivityContributionid1.sprintDailyPoints = modify;

                        String Modifystr = modify+"";

                        int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                        Dashboard.userActivityContributionid1.actualPoints = countOnes+"";
                        setTextView(countOnes+"");

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid1.activityid).child("actualPoints").setValue(countOnes+"");
                    }



                }
            });
            final Button btCalendarDay5 = (Button)view.findViewById(R.id.bt5);
            btCalendarDay5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){

                    String temp = Dashboard.userActivityContributionid1.sprintDailyPoints;

                    int color = 0;
                    Drawable backgroundcolor = btCalendarDay5.getBackground();
                    if (backgroundcolor instanceof ColorDrawable) {
                        color = ((ColorDrawable)backgroundcolor).getColor();
                    }

                    //11001001100100
                    if(color == Color.GREEN){

                        //means its already green so the user wants to DESELECT so update the database
                        String modify1 = temp.substring(0,4)+"0"+temp.substring(5);

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid1.activityid).child("sprintDailyPoints").setValue(modify1);
                        btCalendarDay5.setBackgroundColor(Color.LTGRAY);
                        Dashboard.userActivityContributionid1.sprintDailyPoints = modify1;

                        String Modifystr = modify1+"";

                        int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                        Dashboard.userActivityContributionid1.actualPoints = countOnes+"";
                        setTextView(countOnes+"");

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid1.activityid).child("actualPoints").setValue(countOnes+"");


                    }else{

                        //means the user click this button for first time
                        String modify = temp.substring(0,4)+"1"+temp.substring(5);

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid1.activityid).child("sprintDailyPoints").setValue(modify);
                        btCalendarDay5.setBackgroundColor(Color.GREEN);
                        Dashboard.userActivityContributionid1.sprintDailyPoints = modify;

                        String Modifystr = modify+"";

                        int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                        Dashboard.userActivityContributionid1.actualPoints = countOnes+"";
                        setTextView(countOnes+"");

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid1.activityid).child("actualPoints").setValue(countOnes+"");
                    }


                }
            });
            final Button btCalendarDay6 = (Button)view.findViewById(R.id.bt6);
            btCalendarDay6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){


                    String temp = Dashboard.userActivityContributionid1.sprintDailyPoints;

                    int color = 0;
                    Drawable backgroundcolor = btCalendarDay6.getBackground();
                    if (backgroundcolor instanceof ColorDrawable) {
                        color = ((ColorDrawable)backgroundcolor).getColor();
                    }

                    //11001001100100
                    if(color == Color.GREEN){

                        //means its already green so the user wants to DESELECT so update the database
                        String modify1 = temp.substring(0,5)+"0"+temp.substring(6);

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid1.activityid).child("sprintDailyPoints").setValue(modify1);
                        btCalendarDay6.setBackgroundColor(Color.LTGRAY);
                        Dashboard.userActivityContributionid1.sprintDailyPoints = modify1;

                        String Modifystr = modify1+"";

                        int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                        Dashboard.userActivityContributionid1.actualPoints = countOnes+"";
                        setTextView(countOnes+"");

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid1.activityid).child("actualPoints").setValue(countOnes+"");


                    }else{

                        //means the user click this button for first time
                        String modify = temp.substring(0,5)+"1"+temp.substring(6);

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid1.activityid).child("sprintDailyPoints").setValue(modify);
                        btCalendarDay6.setBackgroundColor(Color.GREEN);
                        Dashboard.userActivityContributionid1.sprintDailyPoints = modify;

                        String Modifystr = modify+"";

                        int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                        Dashboard.userActivityContributionid1.actualPoints = countOnes+"";
                        setTextView(countOnes+"");

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid1.activityid).child("actualPoints").setValue(countOnes+"");
                    }


                }
            });
            final Button btCalendarDay7 = (Button)view.findViewById(R.id.bt7);
            btCalendarDay7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){

                    String temp = Dashboard.userActivityContributionid1.sprintDailyPoints;

                    int color = 0;
                    Drawable backgroundcolor = btCalendarDay7.getBackground();
                    if (backgroundcolor instanceof ColorDrawable) {
                        color = ((ColorDrawable)backgroundcolor).getColor();
                    }

                    //11001001100100
                    if(color == Color.GREEN){

                        //means its already green so the user wants to DESELECT so update the database
                        String modify1 = temp.substring(0,6)+"0"+temp.substring(7);

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid1.activityid).child("sprintDailyPoints").setValue(modify1);
                        btCalendarDay7.setBackgroundColor(Color.LTGRAY);
                        Dashboard.userActivityContributionid1.sprintDailyPoints = modify1;

                        String Modifystr = modify1+"";

                        int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                        Dashboard.userActivityContributionid1.actualPoints = countOnes+"";
                        setTextView(countOnes+"");

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid1.activityid).child("actualPoints").setValue(countOnes+"");

                    }else{

                        //means the user click this button for first time
                        String modify = temp.substring(0,6)+"1"+temp.substring(7);

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid1.activityid).child("sprintDailyPoints").setValue(modify);
                        btCalendarDay7.setBackgroundColor(Color.GREEN);
                        Dashboard.userActivityContributionid1.sprintDailyPoints = modify;

                        String Modifystr = modify+"";

                        int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                        Dashboard.userActivityContributionid1.actualPoints = countOnes+"";
                        setTextView(countOnes+"");

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid1.activityid).child("actualPoints").setValue(countOnes+"");
                    }
                }
            });
            final Button btCalendarDay8 = (Button)view.findViewById(R.id.bt8);
            btCalendarDay8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){

                    String temp = Dashboard.userActivityContributionid1.sprintDailyPoints;

                    int color = 0;
                    Drawable backgroundcolor = btCalendarDay8.getBackground();
                    if (backgroundcolor instanceof ColorDrawable) {
                        color = ((ColorDrawable)backgroundcolor).getColor();
                    }

                    //11001001100100
                    if(color == Color.GREEN){

                        //means its already green so the user wants to DESELECT so update the database
                        String modify1 = temp.substring(0,7)+"0"+temp.substring(8);

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid1.activityid).child("sprintDailyPoints").setValue(modify1);
                        btCalendarDay8.setBackgroundColor(Color.LTGRAY);
                        Dashboard.userActivityContributionid1.sprintDailyPoints = modify1;

                        String Modifystr = modify1+"";

                        int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                        Dashboard.userActivityContributionid1.actualPoints = countOnes+"";
                        setTextView(countOnes+"");

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid1.activityid).child("actualPoints").setValue(countOnes+"");

                    }else{

                        //means the user click this button for first time
                        String modify = temp.substring(0,7)+"1"+temp.substring(8);

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid1.activityid).child("sprintDailyPoints").setValue(modify);
                        btCalendarDay8.setBackgroundColor(Color.GREEN);
                        Dashboard.userActivityContributionid1.sprintDailyPoints = modify;

                        String Modifystr = modify+"";

                        int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                        Dashboard.userActivityContributionid1.actualPoints = countOnes+"";
                        setTextView(countOnes+"");

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid1.activityid).child("actualPoints").setValue(countOnes+"");
                    }

                }
            });
            final Button btCalendarDay9 = (Button)view.findViewById(R.id.bt9);
            btCalendarDay9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){

                    String temp = Dashboard.userActivityContributionid1.sprintDailyPoints;

                    int color = 0;
                    Drawable backgroundcolor = btCalendarDay9.getBackground();
                    if (backgroundcolor instanceof ColorDrawable) {
                        color = ((ColorDrawable)backgroundcolor).getColor();
                    }

                    //11001001100100
                    if(color == Color.GREEN){

                        //means its already green so the user wants to DESELECT so update the database
                        String modify1 = temp.substring(0,8)+"0"+temp.substring(9);

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid1.activityid).child("sprintDailyPoints").setValue(modify1);
                        btCalendarDay9.setBackgroundColor(Color.LTGRAY);
                        Dashboard.userActivityContributionid1.sprintDailyPoints = modify1;

                        String Modifystr = modify1+"";

                        int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                        Dashboard.userActivityContributionid1.actualPoints = countOnes+"";
                        setTextView(countOnes+"");

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid1.activityid).child("actualPoints").setValue(countOnes+"");

                    }else{

                        //means the user click this button for first time
                        String modify = temp.substring(0,8)+"1"+temp.substring(9);

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid1.activityid).child("sprintDailyPoints").setValue(modify);
                        btCalendarDay9.setBackgroundColor(Color.GREEN);
                        Dashboard.userActivityContributionid1.sprintDailyPoints = modify;

                        String Modifystr = modify+"";

                        int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                        Dashboard.userActivityContributionid1.actualPoints = countOnes+"";
                        setTextView(countOnes+"");

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid1.activityid).child("actualPoints").setValue(countOnes+"");
                    }
                }
            });
            final Button btCalendarDay10 = (Button)view.findViewById(R.id.bt10);
            btCalendarDay10.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){

                    String temp = Dashboard.userActivityContributionid1.sprintDailyPoints;

                    int color = 0;
                    Drawable backgroundcolor = btCalendarDay10.getBackground();
                    if (backgroundcolor instanceof ColorDrawable) {
                        color = ((ColorDrawable)backgroundcolor).getColor();
                    }

                    //11001001100100
                    if(color == Color.GREEN){

                        //means its already green so the user wants to DESELECT so update the database
                        String modify1 = temp.substring(0,9)+"0"+temp.substring(10);

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid1.activityid).child("sprintDailyPoints").setValue(modify1);
                        btCalendarDay10.setBackgroundColor(Color.LTGRAY);
                        Dashboard.userActivityContributionid1.sprintDailyPoints = modify1;

                        String Modifystr = modify1+"";

                        int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                        Dashboard.userActivityContributionid1.actualPoints = countOnes+"";
                        setTextView(countOnes+"");

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid1.activityid).child("actualPoints").setValue(countOnes+"");

                    }else{

                        //means the user click this button for first time
                        String modify = temp.substring(0,9)+"1"+temp.substring(10);

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid1.activityid).child("sprintDailyPoints").setValue(modify);
                        btCalendarDay10.setBackgroundColor(Color.GREEN);
                        Dashboard.userActivityContributionid1.sprintDailyPoints = modify;

                        String Modifystr = modify+"";

                        int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                        Dashboard.userActivityContributionid1.actualPoints = countOnes+"";
                        setTextView(countOnes+"");

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid1.activityid).child("actualPoints").setValue(countOnes+"");
                    }
                }
            });
            final Button btCalendarDay11 = (Button)view.findViewById(R.id.bt11);
            btCalendarDay11.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){

                    String temp = Dashboard.userActivityContributionid1.sprintDailyPoints;

                    int color = 0;
                    Drawable backgroundcolor = btCalendarDay11.getBackground();
                    if (backgroundcolor instanceof ColorDrawable) {
                        color = ((ColorDrawable)backgroundcolor).getColor();
                    }

                    //11001001100100
                    if(color == Color.GREEN){

                        //means its already green so the user wants to DESELECT so update the database
                        String modify1 = temp.substring(0,10)+"0"+temp.substring(11);

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid1.activityid).child("sprintDailyPoints").setValue(modify1);
                        btCalendarDay11.setBackgroundColor(Color.LTGRAY);
                        Dashboard.userActivityContributionid1.sprintDailyPoints = modify1;

                        String Modifystr = modify1+"";

                        int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                        Dashboard.userActivityContributionid1.actualPoints = countOnes+"";
                        setTextView(countOnes+"");

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid1.activityid).child("actualPoints").setValue(countOnes+"");

                    }else{

                        //means the user click this button for first time
                        String modify = temp.substring(0,10)+"1"+temp.substring(11);

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid1.activityid).child("sprintDailyPoints").setValue(modify);
                        btCalendarDay11.setBackgroundColor(Color.GREEN);
                        Dashboard.userActivityContributionid1.sprintDailyPoints = modify;

                        String Modifystr = modify+"";

                        int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                        Dashboard.userActivityContributionid1.actualPoints = countOnes+"";
                        setTextView(countOnes+"");

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid1.activityid).child("actualPoints").setValue(countOnes+"");
                    }
                }
            });
            final Button btCalendarDay12 = (Button)view.findViewById(R.id.bt12);
            btCalendarDay12.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){

                    String temp = Dashboard.userActivityContributionid1.sprintDailyPoints;

                    int color = 0;
                    Drawable backgroundcolor = btCalendarDay12.getBackground();
                    if (backgroundcolor instanceof ColorDrawable) {
                        color = ((ColorDrawable)backgroundcolor).getColor();
                    }

                    //11001001100100
                    if(color == Color.GREEN){

                        //means its already green so the user wants to DESELECT so update the database
                        String modify1 = temp.substring(0,11)+"0"+temp.substring(12);

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid1.activityid).child("sprintDailyPoints").setValue(modify1);
                        btCalendarDay12.setBackgroundColor(Color.LTGRAY);
                        Dashboard.userActivityContributionid1.sprintDailyPoints = modify1;

                        String Modifystr = modify1+"";

                        int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                        Dashboard.userActivityContributionid1.actualPoints = countOnes+"";
                        setTextView(countOnes+"");

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid1.activityid).child("actualPoints").setValue(countOnes+"");

                    }else{

                        //means the user click this button for first time
                        String modify = temp.substring(0,11)+"1"+temp.substring(12);

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid1.activityid).child("sprintDailyPoints").setValue(modify);
                        btCalendarDay12.setBackgroundColor(Color.GREEN);
                        Dashboard.userActivityContributionid1.sprintDailyPoints = modify;

                        String Modifystr = modify+"";

                        int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                        Dashboard.userActivityContributionid1.actualPoints = countOnes+"";
                        setTextView(countOnes+"");

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid1.activityid).child("actualPoints").setValue(countOnes+"");
                    }
                }
            });
            final Button btCalendarDay13 = (Button)view.findViewById(R.id.bt13);
            btCalendarDay13.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){

                    String temp = Dashboard.userActivityContributionid1.sprintDailyPoints;

                    int color = 0;
                    Drawable backgroundcolor = btCalendarDay13.getBackground();
                    if (backgroundcolor instanceof ColorDrawable) {
                        color = ((ColorDrawable)backgroundcolor).getColor();
                    }

                    //11001001100100
                    if(color == Color.GREEN){

                        //means its already green so the user wants to DESELECT so update the database
                        String modify1 = temp.substring(0,12)+"0"+temp.substring(13);

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid1.activityid).child("sprintDailyPoints").setValue(modify1);
                        btCalendarDay13.setBackgroundColor(Color.LTGRAY);
                        Dashboard.userActivityContributionid1.sprintDailyPoints = modify1;

                        String Modifystr = modify1+"";

                        int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                        Dashboard.userActivityContributionid1.actualPoints = countOnes+"";
                        setTextView(countOnes+"");

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid1.activityid).child("actualPoints").setValue(countOnes+"");

                    }else{

                        //means the user click this button for first time
                        String modify = temp.substring(0,12)+"1"+temp.substring(13);

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid1.activityid).child("sprintDailyPoints").setValue(modify);
                        btCalendarDay13.setBackgroundColor(Color.GREEN);
                        Dashboard.userActivityContributionid1.sprintDailyPoints = modify;

                        String Modifystr = modify+"";

                        int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                        Dashboard.userActivityContributionid1.actualPoints = countOnes+"";
                        setTextView(countOnes+"");

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid1.activityid).child("actualPoints").setValue(countOnes+"");
                    }
                }
            });
            final Button btCalendarDay14 = (Button)view.findViewById(R.id.bt14);
            btCalendarDay14.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){


                    String temp = Dashboard.userActivityContributionid1.sprintDailyPoints;

                    int color = 0;
                    Drawable backgroundcolor = btCalendarDay14.getBackground();
                    if (backgroundcolor instanceof ColorDrawable) {
                        color = ((ColorDrawable)backgroundcolor).getColor();
                    }

                    //11001001100100
                    if(color == Color.GREEN){

                        //means its already green so the user wants to DESELECT so update the database
                        String modify1 = temp.substring(0,13)+"0"+temp.substring(14);

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid1.activityid).child("sprintDailyPoints").setValue(modify1);
                        btCalendarDay14.setBackgroundColor(Color.LTGRAY);
                        Dashboard.userActivityContributionid1.sprintDailyPoints = modify1;

                        String Modifystr = modify1+"";

                        int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                        Dashboard.userActivityContributionid1.actualPoints = countOnes+"";
                        setTextView(countOnes+"");

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid1.activityid).child("actualPoints").setValue(countOnes+"");

                    }else{

                        //means the user click this button for first time
                        String modify = temp.substring(0,13)+"1"+temp.substring(14);

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid1.activityid).child("sprintDailyPoints").setValue(modify);
                        btCalendarDay14.setBackgroundColor(Color.GREEN);
                        Dashboard.userActivityContributionid1.sprintDailyPoints = modify;

                        String Modifystr = modify+"";

                        int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                        Dashboard.userActivityContributionid1.actualPoints = countOnes+"";
                        setTextView(countOnes+"");

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid1.activityid).child("actualPoints").setValue(countOnes+"");
                    }

                }
            });

            //Remove buttons(15-21) if sprint is 2 weeks
            final Button btCalendarDay15 = (Button)view.findViewById(R.id.bt15);
            btCalendarDay15.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){


                    String temp = Dashboard.userActivityContributionid1.sprintDailyPoints;

                    int color = 0;
                    Drawable backgroundcolor = btCalendarDay15.getBackground();
                    if (backgroundcolor instanceof ColorDrawable) {
                        color = ((ColorDrawable)backgroundcolor).getColor();
                    }

                    //11001001100100
                    if(color == Color.GREEN){

                        //means its already green so the user wants to DESELECT so update the database
                        String modify1 = temp.substring(0,14)+"0"+temp.substring(15);

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid1.activityid).child("sprintDailyPoints").setValue(modify1);
                        btCalendarDay15.setBackgroundColor(Color.LTGRAY);
                        Dashboard.userActivityContributionid1.sprintDailyPoints = modify1;

                        String Modifystr = modify1+"";

                        int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                        Dashboard.userActivityContributionid1.actualPoints = countOnes+"";
                        setTextView(countOnes+"");

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid1.activityid).child("actualPoints").setValue(countOnes+"");

                    }else{

                        //means the user click this button for first time
                        String modify = temp.substring(0,14)+"1"+temp.substring(15);

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid1.activityid).child("sprintDailyPoints").setValue(modify);
                        btCalendarDay15.setBackgroundColor(Color.GREEN);
                        Dashboard.userActivityContributionid1.sprintDailyPoints = modify;

                        String Modifystr = modify+"";

                        int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                        Dashboard.userActivityContributionid1.actualPoints = countOnes+"";
                        setTextView(countOnes+"");

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid1.activityid).child("actualPoints").setValue(countOnes+"");
                    }
                }
            });


            final Button btCalendarDay16 = (Button)view.findViewById(R.id.bt16);
            btCalendarDay16.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){

                    String temp = Dashboard.userActivityContributionid1.sprintDailyPoints;

                    int color = 0;
                    Drawable backgroundcolor = btCalendarDay16.getBackground();
                    if (backgroundcolor instanceof ColorDrawable) {
                        color = ((ColorDrawable)backgroundcolor).getColor();
                    }

                    //11001001100100
                    if(color == Color.GREEN){

                        //means its already green so the user wants to DESELECT so update the database
                        String modify1 = temp.substring(0,15)+"0"+temp.substring(16);

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid1.activityid).child("sprintDailyPoints").setValue(modify1);
                        btCalendarDay16.setBackgroundColor(Color.LTGRAY);
                        Dashboard.userActivityContributionid1.sprintDailyPoints = modify1;

                        String Modifystr = modify1+"";

                        int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                        Dashboard.userActivityContributionid1.actualPoints = countOnes+"";
                        setTextView(countOnes+"");

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid1.activityid).child("actualPoints").setValue(countOnes+"");

                    }else{

                        //means the user click this button for first time
                        String modify = temp.substring(0,15)+"1"+temp.substring(16);

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid1.activityid).child("sprintDailyPoints").setValue(modify);
                        btCalendarDay16.setBackgroundColor(Color.GREEN);
                        Dashboard.userActivityContributionid1.sprintDailyPoints = modify;

                        String Modifystr = modify+"";

                        int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                        Dashboard.userActivityContributionid1.actualPoints = countOnes+"";
                        setTextView(countOnes+"");

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid1.activityid).child("actualPoints").setValue(countOnes+"");
                    }
                }
            });


            final Button btCalendarDay17 = (Button)view.findViewById(R.id.bt17);
            btCalendarDay17.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){

                    String temp = Dashboard.userActivityContributionid1.sprintDailyPoints;

                    int color = 0;
                    Drawable backgroundcolor = btCalendarDay17.getBackground();
                    if (backgroundcolor instanceof ColorDrawable) {
                        color = ((ColorDrawable)backgroundcolor).getColor();
                    }

                    //110010011001001001011
                    if(color == Color.GREEN){

                        //means its already green so the user wants to DESELECT so update the database
                        String modify1 = temp.substring(0,16)+"0"+temp.substring(17);

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid1.activityid).child("sprintDailyPoints").setValue(modify1);
                        btCalendarDay17.setBackgroundColor(Color.LTGRAY);
                        Dashboard.userActivityContributionid1.sprintDailyPoints = modify1;

                        String Modifystr = modify1+"";

                        int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                        Dashboard.userActivityContributionid1.actualPoints = countOnes+"";
                        setTextView(countOnes+"");

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid1.activityid).child("actualPoints").setValue(countOnes+"");

                    }else{

                        //means the user click this button for first time
                        String modify = temp.substring(0,16)+"1"+temp.substring(17);

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid1.activityid).child("sprintDailyPoints").setValue(modify);
                        btCalendarDay17.setBackgroundColor(Color.GREEN);
                        Dashboard.userActivityContributionid1.sprintDailyPoints = modify;

                        String Modifystr = modify+"";

                        int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                        Dashboard.userActivityContributionid1.actualPoints = countOnes+"";
                        setTextView(countOnes+"");

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid1.activityid).child("actualPoints").setValue(countOnes+"");
                    }
                }
            });


            final Button btCalendarDay18 = (Button)view.findViewById(R.id.bt18);
            btCalendarDay18.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){


                    String temp = Dashboard.userActivityContributionid1.sprintDailyPoints;

                    int color = 0;
                    Drawable backgroundcolor = btCalendarDay18.getBackground();
                    if (backgroundcolor instanceof ColorDrawable) {
                        color = ((ColorDrawable)backgroundcolor).getColor();
                    }

                    //110010011001001001011
                    if(color == Color.GREEN){

                        //means its already green so the user wants to DESELECT so update the database
                        String modify1 = temp.substring(0,17)+"0"+temp.substring(18);

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid1.activityid).child("sprintDailyPoints").setValue(modify1);
                        btCalendarDay18.setBackgroundColor(Color.LTGRAY);
                        Dashboard.userActivityContributionid1.sprintDailyPoints = modify1;

                        String Modifystr = modify1+"";

                        int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                        Dashboard.userActivityContributionid1.actualPoints = countOnes+"";
                        setTextView(countOnes+"");

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid1.activityid).child("actualPoints").setValue(countOnes+"");

                    }else{

                        //means the user click this button for first time
                        String modify = temp.substring(0,17)+"1"+temp.substring(18);

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid1.activityid).child("sprintDailyPoints").setValue(modify);
                        btCalendarDay18.setBackgroundColor(Color.GREEN);
                        Dashboard.userActivityContributionid1.sprintDailyPoints = modify;

                        String Modifystr = modify+"";

                        int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                        Dashboard.userActivityContributionid1.actualPoints = countOnes+"";
                        setTextView(countOnes+"");

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid1.activityid).child("actualPoints").setValue(countOnes+"");
                    }
                }
            });


            final Button btCalendarDay19 = (Button)view.findViewById(R.id.bt19);
            btCalendarDay19.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){

                    String temp = Dashboard.userActivityContributionid1.sprintDailyPoints;

                    int color = 0;
                    Drawable backgroundcolor = btCalendarDay19.getBackground();
                    if (backgroundcolor instanceof ColorDrawable) {
                        color = ((ColorDrawable)backgroundcolor).getColor();
                    }

                    //110010011001001001011
                    if(color == Color.GREEN){

                        //means its already green so the user wants to DESELECT so update the database
                        String modify1 = temp.substring(0,18)+"0"+temp.substring(19);

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid1.activityid).child("sprintDailyPoints").setValue(modify1);
                        btCalendarDay19.setBackgroundColor(Color.LTGRAY);
                        Dashboard.userActivityContributionid1.sprintDailyPoints = modify1;

                        String Modifystr = modify1+"";

                        int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                        Dashboard.userActivityContributionid1.actualPoints = countOnes+"";
                        setTextView(countOnes+"");

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid1.activityid).child("actualPoints").setValue(countOnes+"");

                    }else{

                        //means the user click this button for first time
                        String modify = temp.substring(0,18)+"1"+temp.substring(19);

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid1.activityid).child("sprintDailyPoints").setValue(modify);
                        btCalendarDay19.setBackgroundColor(Color.GREEN);
                        Dashboard.userActivityContributionid1.sprintDailyPoints = modify;

                        String Modifystr = modify+"";

                        int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                        Dashboard.userActivityContributionid1.actualPoints = countOnes+"";
                        setTextView(countOnes+"");

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid1.activityid).child("actualPoints").setValue(countOnes+"");
                    }

                }
            });


            final Button btCalendarDay20 = (Button)view.findViewById(R.id.bt20);
            btCalendarDay20.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){

                    String temp = Dashboard.userActivityContributionid1.sprintDailyPoints;

                    int color = 0;
                    Drawable backgroundcolor = btCalendarDay20.getBackground();
                    if (backgroundcolor instanceof ColorDrawable) {
                        color = ((ColorDrawable)backgroundcolor).getColor();
                    }

                    //110010011001001001011
                    if(color == Color.GREEN){

                        //means its already green so the user wants to DESELECT so update the database
                        String modify1 = temp.substring(0,19)+"0"+temp.substring(20);

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid1.activityid).child("sprintDailyPoints").setValue(modify1);
                        btCalendarDay20.setBackgroundColor(Color.LTGRAY);
                        Dashboard.userActivityContributionid1.sprintDailyPoints = modify1;

                        String Modifystr = modify1+"";

                        int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                        Dashboard.userActivityContributionid1.actualPoints = countOnes+"";
                        setTextView(countOnes+"");

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid1.activityid).child("actualPoints").setValue(countOnes+"");

                    }else{

                        //means the user click this button for first time
                        String modify = temp.substring(0,19)+"1"+temp.substring(20);

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid1.activityid).child("sprintDailyPoints").setValue(modify);
                        btCalendarDay20.setBackgroundColor(Color.GREEN);
                        Dashboard.userActivityContributionid1.sprintDailyPoints = modify;

                        String Modifystr = modify+"";

                        int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                        Dashboard.userActivityContributionid1.actualPoints = countOnes+"";
                        setTextView(countOnes+"");

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid1.activityid).child("actualPoints").setValue(countOnes+"");
                    }

                }
            });


            final Button btCalendarDay21 = (Button)view.findViewById(R.id.bt21);
            btCalendarDay21.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){

                    String temp = Dashboard.userActivityContributionid1.sprintDailyPoints;

                    int color = 0;
                    Drawable backgroundcolor = btCalendarDay21.getBackground();
                    if (backgroundcolor instanceof ColorDrawable) {
                        color = ((ColorDrawable)backgroundcolor).getColor();
                    }

                    //110010011001001001011
                    if(color == Color.GREEN){

                        //means its already green so the user wants to DESELECT so update the database
                        String modify1 = temp.substring(0,20)+"0"+temp.substring(21);

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid1.activityid).child("sprintDailyPoints").setValue(modify1);
                        btCalendarDay21.setBackgroundColor(Color.LTGRAY);
                        Dashboard.userActivityContributionid1.sprintDailyPoints = modify1;

                        String Modifystr = modify1+"";

                        int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                        Dashboard.userActivityContributionid1.actualPoints = countOnes+"";
                        setTextView(countOnes+"");

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid1.activityid).child("actualPoints").setValue(countOnes+"");

                    }else{

                        //means the user click this button for first time
                        String modify = temp.substring(0,20)+"1"+temp.substring(21);

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid1.activityid).child("sprintDailyPoints").setValue(modify);
                        btCalendarDay21.setBackgroundColor(Color.GREEN);
                        Dashboard.userActivityContributionid1.sprintDailyPoints = modify;

                        String Modifystr = modify+"";

                        int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                        Dashboard.userActivityContributionid1.actualPoints = countOnes+"";
                        setTextView(countOnes+"");

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid1.activityid).child("actualPoints").setValue(countOnes+"");
                    }
                }
            });

            // Buttons for Activity #2
            final Button btCalendarDay22 = (Button)view.findViewById(R.id.bt22);
            btCalendarDay22.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){

                    String temp = Dashboard.userActivityContributionid2.sprintDailyPoints;

                    int color = 0;
                    Drawable backgroundcolor = btCalendarDay22.getBackground();
                    if (backgroundcolor instanceof ColorDrawable) {
                        color = ((ColorDrawable)backgroundcolor).getColor();
                    }

                    if(color == Color.GREEN){

                        //means its already green so the user wants to DESELECT so update the database
                        String modify1 = "0"+temp.substring(1);

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid2.activityid).child("sprintDailyPoints").setValue(modify1);
                        btCalendarDay22.setBackgroundColor(Color.LTGRAY);

                        Dashboard.userActivityContributionid2.sprintDailyPoints = modify1; //maintain a copy global instead of accessing to database

                        String Modifystr = modify1+"";

                        int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                        Dashboard.userActivityContributionid2.actualPoints = countOnes+"";
                        setTextView2(countOnes+"");

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid2.activityid).child("actualPoints").setValue(countOnes+"");


                    }else{

                        //means the user click this button for first time
                        String modify = "1"+temp.substring(1);

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid2.activityid).child("sprintDailyPoints").setValue(modify);
                        btCalendarDay22.setBackgroundColor(Color.GREEN);

                        Dashboard.userActivityContributionid2.sprintDailyPoints = modify;

                        String Modifystr = modify+"";

                        int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                        Dashboard.userActivityContributionid2.actualPoints = countOnes+"";
                        setTextView2(countOnes+"");

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid2.activityid).child("actualPoints").setValue(countOnes+"");


                    }
                }
            });
            final Button btCalendarDay23 = (Button)view.findViewById(R.id.bt23);
            btCalendarDay23.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){

                    String temp = Dashboard.userActivityContributionid2.sprintDailyPoints;

                    int color = 0;
                    Drawable backgroundcolor = btCalendarDay23.getBackground();
                    if (backgroundcolor instanceof ColorDrawable) {
                        color = ((ColorDrawable)backgroundcolor).getColor();
                    }

                    if(color == Color.GREEN){

                        //means its already green so the user wants to DESELECT so update the database
                        String modify1 = temp.substring(0,1)+"0"+temp.substring(2);

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid2.activityid).child("sprintDailyPoints").setValue(modify1);
                        btCalendarDay23.setBackgroundColor(Color.LTGRAY);

                        Dashboard.userActivityContributionid2.sprintDailyPoints = modify1; //maintain a copy global instead of accessing to database

                        String Modifystr = modify1+"";

                        int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                        Dashboard.userActivityContributionid2.actualPoints = countOnes+"";
                        setTextView2(countOnes+"");

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid2.activityid).child("actualPoints").setValue(countOnes+"");


                    }else{

                        //means the user click this button for first time
                        String modify = temp.substring(0,1)+"1"+temp.substring(2);

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid2.activityid).child("sprintDailyPoints").setValue(modify);
                        btCalendarDay23.setBackgroundColor(Color.GREEN);

                        Dashboard.userActivityContributionid2.sprintDailyPoints = modify;

                        String Modifystr = modify+"";

                        int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                        Dashboard.userActivityContributionid2.actualPoints = countOnes+"";
                        setTextView2(countOnes+"");

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid2.activityid).child("actualPoints").setValue(countOnes+"");
                    }
                }
            });
            final Button btCalendarDay24 = (Button)view.findViewById(R.id.bt24);
            btCalendarDay24.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){

                    String temp = Dashboard.userActivityContributionid2.sprintDailyPoints;

                    int color = 0;
                    Drawable backgroundcolor = btCalendarDay24.getBackground();
                    if (backgroundcolor instanceof ColorDrawable) {
                        color = ((ColorDrawable)backgroundcolor).getColor();
                    }

                    if(color == Color.GREEN){

                        //means its already green so the user wants to DESELECT so update the database
                        String modify1 = temp.substring(0,2)+"0"+temp.substring(3);

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid2.activityid).child("sprintDailyPoints").setValue(modify1);
                        btCalendarDay24.setBackgroundColor(Color.LTGRAY);

                        Dashboard.userActivityContributionid2.sprintDailyPoints = modify1; //maintain a copy global instead of accessing to database

                        String Modifystr = modify1+"";

                        int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                        Dashboard.userActivityContributionid2.actualPoints = countOnes+"";
                        setTextView2(countOnes+"");

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid2.activityid).child("actualPoints").setValue(countOnes+"");


                    }else{

                        //means the user click this button for first time
                        String modify = temp.substring(0,2)+"1"+temp.substring(3);

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid2.activityid).child("sprintDailyPoints").setValue(modify);
                        btCalendarDay24.setBackgroundColor(Color.GREEN);

                        Dashboard.userActivityContributionid2.sprintDailyPoints = modify;

                        String Modifystr = modify+"";

                        int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                        Dashboard.userActivityContributionid2.actualPoints = countOnes+"";
                        setTextView2(countOnes+"");

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid2.activityid).child("actualPoints").setValue(countOnes+"");
                    }
                }
            });
            final Button btCalendarDay25 = (Button)view.findViewById(R.id.bt25);
            btCalendarDay25.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){

                    String temp = Dashboard.userActivityContributionid2.sprintDailyPoints;

                    int color = 0;
                    Drawable backgroundcolor = btCalendarDay25.getBackground();
                    if (backgroundcolor instanceof ColorDrawable) {
                        color = ((ColorDrawable)backgroundcolor).getColor();
                    }

                    if(color == Color.GREEN){

                        //means its already green so the user wants to DESELECT so update the database
                        String modify1 = temp.substring(0,3)+"0"+temp.substring(4);

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid2.activityid).child("sprintDailyPoints").setValue(modify1);
                        btCalendarDay25.setBackgroundColor(Color.LTGRAY);

                        Dashboard.userActivityContributionid2.sprintDailyPoints = modify1; //maintain a copy global instead of accessing to database

                        String Modifystr = modify1+"";

                        int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                        Dashboard.userActivityContributionid2.actualPoints = countOnes+"";
                        setTextView2(countOnes+"");

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid2.activityid).child("actualPoints").setValue(countOnes+"");

                    }else{

                        //means the user click this button for first time
                        String modify = temp.substring(0,3)+"1"+temp.substring(4);

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid2.activityid).child("sprintDailyPoints").setValue(modify);
                        btCalendarDay25.setBackgroundColor(Color.GREEN);

                        Dashboard.userActivityContributionid2.sprintDailyPoints = modify;

                        String Modifystr = modify+"";

                        int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                        Dashboard.userActivityContributionid2.actualPoints = countOnes+"";
                        setTextView2(countOnes+"");

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid2.activityid).child("actualPoints").setValue(countOnes+"");
                    }

                }
            });
            final Button btCalendarDay26 = (Button)view.findViewById(R.id.bt26);
            btCalendarDay26.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){

                    String temp = Dashboard.userActivityContributionid2.sprintDailyPoints;

                    int color = 0;
                    Drawable backgroundcolor = btCalendarDay26.getBackground();
                    if (backgroundcolor instanceof ColorDrawable) {
                        color = ((ColorDrawable)backgroundcolor).getColor();
                    }

                    if(color == Color.GREEN){

                        //means its already green so the user wants to DESELECT so update the database
                        String modify1 = temp.substring(0,4)+"0"+temp.substring(5);

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid2.activityid).child("sprintDailyPoints").setValue(modify1);
                        btCalendarDay26.setBackgroundColor(Color.LTGRAY);

                        Dashboard.userActivityContributionid2.sprintDailyPoints = modify1; //maintain a copy global instead of accessing to database

                        String Modifystr = modify1+"";

                        int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                        Dashboard.userActivityContributionid2.actualPoints = countOnes+"";
                        setTextView2(countOnes+"");

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid2.activityid).child("actualPoints").setValue(countOnes+"");

                    }else{

                        //means the user click this button for first time
                        String modify = temp.substring(0,4)+"1"+temp.substring(5);

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid2.activityid).child("sprintDailyPoints").setValue(modify);
                        btCalendarDay26.setBackgroundColor(Color.GREEN);

                        Dashboard.userActivityContributionid2.sprintDailyPoints = modify;

                        String Modifystr = modify+"";

                        int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                        Dashboard.userActivityContributionid2.actualPoints = countOnes+"";
                        setTextView2(countOnes+"");

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid2.activityid).child("actualPoints").setValue(countOnes+"");
                    }
                }
            });
            final Button btCalendarDay27 = (Button)view.findViewById(R.id.bt27);
            btCalendarDay27.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){

                    String temp = Dashboard.userActivityContributionid2.sprintDailyPoints;

                    int color = 0;
                    Drawable backgroundcolor = btCalendarDay27.getBackground();
                    if (backgroundcolor instanceof ColorDrawable) {
                        color = ((ColorDrawable)backgroundcolor).getColor();
                    }

                    if(color == Color.GREEN){

                        //means its already green so the user wants to DESELECT so update the database
                        String modify1 = temp.substring(0,5)+"0"+temp.substring(6);

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid2.activityid).child("sprintDailyPoints").setValue(modify1);
                        btCalendarDay27.setBackgroundColor(Color.LTGRAY);

                        Dashboard.userActivityContributionid2.sprintDailyPoints = modify1; //maintain a copy global instead of accessing to database

                        String Modifystr = modify1+"";

                        int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                        Dashboard.userActivityContributionid2.actualPoints = countOnes+"";
                        setTextView2(countOnes+"");

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid2.activityid).child("actualPoints").setValue(countOnes+"");

                    }else{

                        //means the user click this button for first time
                        String modify = temp.substring(0,5)+"1"+temp.substring(6);

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid2.activityid).child("sprintDailyPoints").setValue(modify);
                        btCalendarDay27.setBackgroundColor(Color.GREEN);

                        Dashboard.userActivityContributionid2.sprintDailyPoints = modify;

                        String Modifystr = modify+"";

                        int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                        Dashboard.userActivityContributionid2.actualPoints = countOnes+"";
                        setTextView2(countOnes+"");

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid2.activityid).child("actualPoints").setValue(countOnes+"");
                    }
                }
            });
            final Button btCalendarDay28 = (Button)view.findViewById(R.id.bt28);
            btCalendarDay28.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){


                    String temp = Dashboard.userActivityContributionid2.sprintDailyPoints;

                    int color = 0;
                    Drawable backgroundcolor = btCalendarDay28.getBackground();
                    if (backgroundcolor instanceof ColorDrawable) {
                        color = ((ColorDrawable)backgroundcolor).getColor();
                    }

                    if(color == Color.GREEN){

                        //means its already green so the user wants to DESELECT so update the database
                        String modify1 = temp.substring(0,6)+"0"+temp.substring(7);

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid2.activityid).child("sprintDailyPoints").setValue(modify1);
                        btCalendarDay28.setBackgroundColor(Color.LTGRAY);

                        Dashboard.userActivityContributionid2.sprintDailyPoints = modify1; //maintain a copy global instead of accessing to database

                        String Modifystr = modify1+"";

                        int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                        Dashboard.userActivityContributionid2.actualPoints = countOnes+"";
                        setTextView2(countOnes+"");

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid2.activityid).child("actualPoints").setValue(countOnes+"");

                    }else{

                        //means the user click this button for first time
                        String modify = temp.substring(0,6)+"1"+temp.substring(7);

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid2.activityid).child("sprintDailyPoints").setValue(modify);
                        btCalendarDay28.setBackgroundColor(Color.GREEN);

                        Dashboard.userActivityContributionid2.sprintDailyPoints = modify;

                        String Modifystr = modify+"";

                        int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                        Dashboard.userActivityContributionid2.actualPoints = countOnes+"";
                        setTextView2(countOnes+"");

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid2.activityid).child("actualPoints").setValue(countOnes+"");
                    }
                }
            });
            final Button btCalendarDay29 = (Button)view.findViewById(R.id.bt29);
            btCalendarDay29.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){

                    String temp = Dashboard.userActivityContributionid2.sprintDailyPoints;

                    int color = 0;
                    Drawable backgroundcolor = btCalendarDay29.getBackground();
                    if (backgroundcolor instanceof ColorDrawable) {
                        color = ((ColorDrawable)backgroundcolor).getColor();
                    }

                    if(color == Color.GREEN){

                        //means its already green so the user wants to DESELECT so update the database
                        String modify1 = temp.substring(0,7)+"0"+temp.substring(8);

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid2.activityid).child("sprintDailyPoints").setValue(modify1);
                        btCalendarDay29.setBackgroundColor(Color.LTGRAY);

                        Dashboard.userActivityContributionid2.sprintDailyPoints = modify1; //maintain a copy global instead of accessing to database

                        String Modifystr = modify1+"";

                        int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                        Dashboard.userActivityContributionid2.actualPoints = countOnes+"";
                        setTextView2(countOnes+"");

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid2.activityid).child("actualPoints").setValue(countOnes+"");

                    }else{

                        //means the user click this button for first time
                        String modify = temp.substring(0,7)+"1"+temp.substring(8);

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid2.activityid).child("sprintDailyPoints").setValue(modify);
                        btCalendarDay29.setBackgroundColor(Color.GREEN);

                        Dashboard.userActivityContributionid2.sprintDailyPoints = modify;

                        String Modifystr = modify+"";

                        int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                        Dashboard.userActivityContributionid2.actualPoints = countOnes+"";
                        setTextView2(countOnes+"");

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid2.activityid).child("actualPoints").setValue(countOnes+"");
                    }
                }
            });
            final Button btCalendarDay30 = (Button)view.findViewById(R.id.bt30);
            btCalendarDay30.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){

                    String temp = Dashboard.userActivityContributionid2.sprintDailyPoints;

                    int color = 0;
                    Drawable backgroundcolor = btCalendarDay30.getBackground();
                    if (backgroundcolor instanceof ColorDrawable) {
                        color = ((ColorDrawable)backgroundcolor).getColor();
                    }

                    if(color == Color.GREEN){

                        //means its already green so the user wants to DESELECT so update the database
                        String modify1 = temp.substring(0,8)+"0"+temp.substring(9);

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid2.activityid).child("sprintDailyPoints").setValue(modify1);
                        btCalendarDay30.setBackgroundColor(Color.LTGRAY);

                        Dashboard.userActivityContributionid2.sprintDailyPoints = modify1; //maintain a copy global instead of accessing to database

                        String Modifystr = modify1+"";

                        int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                        Dashboard.userActivityContributionid2.actualPoints = countOnes+"";
                        setTextView2(countOnes+"");

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid2.activityid).child("actualPoints").setValue(countOnes+"");

                    }else{

                        //means the user click this button for first time
                        String modify = temp.substring(0,8)+"1"+temp.substring(9);

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid2.activityid).child("sprintDailyPoints").setValue(modify);
                        btCalendarDay30.setBackgroundColor(Color.GREEN);

                        Dashboard.userActivityContributionid2.sprintDailyPoints = modify;

                        String Modifystr = modify+"";

                        int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                        Dashboard.userActivityContributionid2.actualPoints = countOnes+"";
                        setTextView2(countOnes+"");

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid2.activityid).child("actualPoints").setValue(countOnes+"");
                    }
                }
            });
            final Button btCalendarDay31 = (Button)view.findViewById(R.id.bt31);
            btCalendarDay31.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){


                    String temp = Dashboard.userActivityContributionid2.sprintDailyPoints;

                    int color = 0;
                    Drawable backgroundcolor = btCalendarDay31.getBackground();
                    if (backgroundcolor instanceof ColorDrawable) {
                        color = ((ColorDrawable)backgroundcolor).getColor();
                    }

                    if(color == Color.GREEN){

                        //means its already green so the user wants to DESELECT so update the database
                        String modify1 = temp.substring(0,9)+"0"+temp.substring(10);

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid2.activityid).child("sprintDailyPoints").setValue(modify1);
                        btCalendarDay31.setBackgroundColor(Color.LTGRAY);

                        Dashboard.userActivityContributionid2.sprintDailyPoints = modify1; //maintain a copy global instead of accessing to database

                        String Modifystr = modify1+"";

                        int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                        Dashboard.userActivityContributionid2.actualPoints = countOnes+"";
                        setTextView2(countOnes+"");

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid2.activityid).child("actualPoints").setValue(countOnes+"");

                    }else{

                        //means the user click this button for first time
                        String modify = temp.substring(0,9)+"1"+temp.substring(10);

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid2.activityid).child("sprintDailyPoints").setValue(modify);
                        btCalendarDay31.setBackgroundColor(Color.GREEN);

                        Dashboard.userActivityContributionid2.sprintDailyPoints = modify;

                        String Modifystr = modify+"";

                        int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                        Dashboard.userActivityContributionid2.actualPoints = countOnes+"";
                        setTextView2(countOnes+"");

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid2.activityid).child("actualPoints").setValue(countOnes+"");
                    }
                }
            });
            final Button btCalendarDay32 = (Button)view.findViewById(R.id.bt32);
            btCalendarDay32.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){

                    String temp = Dashboard.userActivityContributionid2.sprintDailyPoints;

                    int color = 0;
                    Drawable backgroundcolor = btCalendarDay32.getBackground();
                    if (backgroundcolor instanceof ColorDrawable) {
                        color = ((ColorDrawable)backgroundcolor).getColor();
                    }

                    if(color == Color.GREEN){

                        //means its already green so the user wants to DESELECT so update the database
                        String modify1 = temp.substring(0,10)+"0"+temp.substring(11);

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid2.activityid).child("sprintDailyPoints").setValue(modify1);
                        btCalendarDay32.setBackgroundColor(Color.LTGRAY);

                        Dashboard.userActivityContributionid2.sprintDailyPoints = modify1; //maintain a copy global instead of accessing to database

                        String Modifystr = modify1+"";

                        int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                        Dashboard.userActivityContributionid2.actualPoints = countOnes+"";
                        setTextView2(countOnes+"");

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid2.activityid).child("actualPoints").setValue(countOnes+"");

                    }else{

                        //means the user click this button for first time
                        String modify = temp.substring(0,10)+"1"+temp.substring(11);

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid2.activityid).child("sprintDailyPoints").setValue(modify);
                        btCalendarDay32.setBackgroundColor(Color.GREEN);

                        Dashboard.userActivityContributionid2.sprintDailyPoints = modify;

                        String Modifystr = modify+"";

                        int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                        Dashboard.userActivityContributionid2.actualPoints = countOnes+"";
                        setTextView2(countOnes+"");

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid2.activityid).child("actualPoints").setValue(countOnes+"");
                    }
                }
            });
            final Button btCalendarDay33 = (Button)view.findViewById(R.id.bt33);
            btCalendarDay33.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){


                    String temp = Dashboard.userActivityContributionid2.sprintDailyPoints;

                    int color = 0;
                    Drawable backgroundcolor = btCalendarDay33.getBackground();
                    if (backgroundcolor instanceof ColorDrawable) {
                        color = ((ColorDrawable)backgroundcolor).getColor();
                    }

                    if(color == Color.GREEN){

                        //means its already green so the user wants to DESELECT so update the database
                        String modify1 = temp.substring(0,11)+"0"+temp.substring(12);

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid2.activityid).child("sprintDailyPoints").setValue(modify1);
                        btCalendarDay33.setBackgroundColor(Color.LTGRAY);

                        Dashboard.userActivityContributionid2.sprintDailyPoints = modify1; //maintain a copy global instead of accessing to database

                        String Modifystr = modify1+"";

                        int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                        Dashboard.userActivityContributionid2.actualPoints = countOnes+"";
                        setTextView2(countOnes+"");

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid2.activityid).child("actualPoints").setValue(countOnes+"");

                    }else{

                        //means the user click this button for first time
                        String modify = temp.substring(0,11)+"1"+temp.substring(12);

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid2.activityid).child("sprintDailyPoints").setValue(modify);
                        btCalendarDay33.setBackgroundColor(Color.GREEN);

                        Dashboard.userActivityContributionid2.sprintDailyPoints = modify;

                        String Modifystr = modify+"";

                        int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                        Dashboard.userActivityContributionid2.actualPoints = countOnes+"";
                        setTextView2(countOnes+"");

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid2.activityid).child("actualPoints").setValue(countOnes+"");
                    }
                }
            });
            final Button btCalendarDay34 = (Button)view.findViewById(R.id.bt34);
            btCalendarDay34.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){


                    String temp = Dashboard.userActivityContributionid2.sprintDailyPoints;

                    int color = 0;
                    Drawable backgroundcolor = btCalendarDay34.getBackground();
                    if (backgroundcolor instanceof ColorDrawable) {
                        color = ((ColorDrawable)backgroundcolor).getColor();
                    }

                    if(color == Color.GREEN){

                        //means its already green so the user wants to DESELECT so update the database
                        String modify1 = temp.substring(0,12)+"0"+temp.substring(13);

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid2.activityid).child("sprintDailyPoints").setValue(modify1);
                        btCalendarDay34.setBackgroundColor(Color.LTGRAY);

                        Dashboard.userActivityContributionid2.sprintDailyPoints = modify1; //maintain a copy global instead of accessing to database

                        String Modifystr = modify1+"";

                        int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                        Dashboard.userActivityContributionid2.actualPoints = countOnes+"";
                        setTextView2(countOnes+"");

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid2.activityid).child("actualPoints").setValue(countOnes+"");

                    }else{

                        //means the user click this button for first time
                        String modify = temp.substring(0,12)+"1"+temp.substring(13);

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid2.activityid).child("sprintDailyPoints").setValue(modify);
                        btCalendarDay34.setBackgroundColor(Color.GREEN);

                        Dashboard.userActivityContributionid2.sprintDailyPoints = modify;

                        String Modifystr = modify+"";

                        int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                        Dashboard.userActivityContributionid2.actualPoints = countOnes+"";
                        setTextView2(countOnes+"");

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid2.activityid).child("actualPoints").setValue(countOnes+"");
                    }
                }
            });

            final Button btCalendarDay35 = (Button)view.findViewById(R.id.bt35);
            btCalendarDay35.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){


                    String temp = Dashboard.userActivityContributionid2.sprintDailyPoints;

                    int color = 0;
                    Drawable backgroundcolor = btCalendarDay35.getBackground();
                    if (backgroundcolor instanceof ColorDrawable) {
                        color = ((ColorDrawable)backgroundcolor).getColor();
                    }

                    if(color == Color.GREEN){

                        //means its already green so the user wants to DESELECT so update the database
                        String modify1 = temp.substring(0,13)+"0"+temp.substring(14);

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid2.activityid).child("sprintDailyPoints").setValue(modify1);
                        btCalendarDay35.setBackgroundColor(Color.LTGRAY);

                        Dashboard.userActivityContributionid2.sprintDailyPoints = modify1; //maintain a copy global instead of accessing to database

                        String Modifystr = modify1+"";

                        int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                        Dashboard.userActivityContributionid2.actualPoints = countOnes+"";
                        setTextView2(countOnes+"");

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid2.activityid).child("actualPoints").setValue(countOnes+"");

                    }else{

                        //means the user click this button for first time
                        String modify = temp.substring(0,13)+"1"+temp.substring(14);

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid2.activityid).child("sprintDailyPoints").setValue(modify);
                        btCalendarDay35.setBackgroundColor(Color.GREEN);

                        Dashboard.userActivityContributionid2.sprintDailyPoints = modify;

                        String Modifystr = modify+"";

                        int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                        Dashboard.userActivityContributionid2.actualPoints = countOnes+"";
                        setTextView2(countOnes+"");

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid2.activityid).child("actualPoints").setValue(countOnes+"");
                    }

                }
            });

            //Remove buttons (36-42) if sprint is 2 weeks
            final Button btCalendarDay36 = (Button)view.findViewById(R.id.bt36);
            btCalendarDay36.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){

                    String temp = Dashboard.userActivityContributionid2.sprintDailyPoints;

                    int color = 0;
                    Drawable backgroundcolor = btCalendarDay36.getBackground();
                    if (backgroundcolor instanceof ColorDrawable) {
                        color = ((ColorDrawable)backgroundcolor).getColor();
                    }

                    if(color == Color.GREEN){

                        //means its already green so the user wants to DESELECT so update the database
                        String modify1 = temp.substring(0,14)+"0"+temp.substring(15);

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid2.activityid).child("sprintDailyPoints").setValue(modify1);
                        btCalendarDay36.setBackgroundColor(Color.LTGRAY);

                        Dashboard.userActivityContributionid2.sprintDailyPoints = modify1; //maintain a copy global instead of accessing to database

                        String Modifystr = modify1+"";

                        int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                        Dashboard.userActivityContributionid2.actualPoints = countOnes+"";
                        setTextView2(countOnes+"");

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid2.activityid).child("actualPoints").setValue(countOnes+"");

                    }else{

                        //means the user click this button for first time
                        String modify = temp.substring(0,14)+"1"+temp.substring(15);

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid2.activityid).child("sprintDailyPoints").setValue(modify);
                        btCalendarDay36.setBackgroundColor(Color.GREEN);

                        Dashboard.userActivityContributionid2.sprintDailyPoints = modify;

                        String Modifystr = modify+"";

                        int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                        Dashboard.userActivityContributionid2.actualPoints = countOnes+"";
                        setTextView2(countOnes+"");

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid2.activityid).child("actualPoints").setValue(countOnes+"");
                    }
                }
            });


            final Button btCalendarDay37 = (Button)view.findViewById(R.id.bt37);
            btCalendarDay37.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){


                    String temp = Dashboard.userActivityContributionid2.sprintDailyPoints;

                    int color = 0;
                    Drawable backgroundcolor = btCalendarDay37.getBackground();
                    if (backgroundcolor instanceof ColorDrawable) {
                        color = ((ColorDrawable)backgroundcolor).getColor();
                    }

                    if(color == Color.GREEN){

                        //means its already green so the user wants to DESELECT so update the database
                        String modify1 = temp.substring(0,15)+"0"+temp.substring(16);

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid2.activityid).child("sprintDailyPoints").setValue(modify1);
                        btCalendarDay37.setBackgroundColor(Color.LTGRAY);

                        Dashboard.userActivityContributionid2.sprintDailyPoints = modify1; //maintain a copy global instead of accessing to database

                        String Modifystr = modify1+"";

                        int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                        Dashboard.userActivityContributionid2.actualPoints = countOnes+"";
                        setTextView2(countOnes+"");

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid2.activityid).child("actualPoints").setValue(countOnes+"");

                    }else{

                        //means the user click this button for first time
                        String modify = temp.substring(0,15)+"1"+temp.substring(16);

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid2.activityid).child("sprintDailyPoints").setValue(modify);
                        btCalendarDay37.setBackgroundColor(Color.GREEN);

                        Dashboard.userActivityContributionid2.sprintDailyPoints = modify;

                        String Modifystr = modify+"";

                        int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                        Dashboard.userActivityContributionid2.actualPoints = countOnes+"";
                        setTextView2(countOnes+"");

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid2.activityid).child("actualPoints").setValue(countOnes+"");
                    }
                }
            });


            final Button btCalendarDay38 = (Button)view.findViewById(R.id.bt38);
            btCalendarDay38.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){

                    String temp = Dashboard.userActivityContributionid2.sprintDailyPoints;

                    int color = 0;
                    Drawable backgroundcolor = btCalendarDay38.getBackground();
                    if (backgroundcolor instanceof ColorDrawable) {
                        color = ((ColorDrawable)backgroundcolor).getColor();
                    }

                    if(color == Color.GREEN){

                        //means its already green so the user wants to DESELECT so update the database
                        String modify1 = temp.substring(0,16)+"0"+temp.substring(17);

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid2.activityid).child("sprintDailyPoints").setValue(modify1);
                        btCalendarDay38.setBackgroundColor(Color.LTGRAY);

                        Dashboard.userActivityContributionid2.sprintDailyPoints = modify1; //maintain a copy global instead of accessing to database

                        String Modifystr = modify1+"";

                        int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                        Dashboard.userActivityContributionid2.actualPoints = countOnes+"";
                        setTextView2(countOnes+"");

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid2.activityid).child("actualPoints").setValue(countOnes+"");

                    }else{

                        //means the user click this button for first time
                        String modify = temp.substring(0,16)+"1"+temp.substring(17);

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid2.activityid).child("sprintDailyPoints").setValue(modify);
                        btCalendarDay38.setBackgroundColor(Color.GREEN);

                        Dashboard.userActivityContributionid2.sprintDailyPoints = modify;

                        String Modifystr = modify+"";

                        int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                        Dashboard.userActivityContributionid2.actualPoints = countOnes+"";
                        setTextView2(countOnes+"");

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid2.activityid).child("actualPoints").setValue(countOnes+"");
                    }

                }
            });


            final Button btCalendarDay39 = (Button)view.findViewById(R.id.bt39);
            btCalendarDay39.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){

                    String temp = Dashboard.userActivityContributionid2.sprintDailyPoints;

                    int color = 0;
                    Drawable backgroundcolor = btCalendarDay39.getBackground();
                    if (backgroundcolor instanceof ColorDrawable) {
                        color = ((ColorDrawable)backgroundcolor).getColor();
                    }

                    if(color == Color.GREEN){

                        //means its already green so the user wants to DESELECT so update the database
                        String modify1 = temp.substring(0,17)+"0"+temp.substring(18);

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid2.activityid).child("sprintDailyPoints").setValue(modify1);
                        btCalendarDay39.setBackgroundColor(Color.LTGRAY);

                        Dashboard.userActivityContributionid2.sprintDailyPoints = modify1; //maintain a copy global instead of accessing to database

                        String Modifystr = modify1+"";

                        int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                        Dashboard.userActivityContributionid2.actualPoints = countOnes+"";
                        setTextView2(countOnes+"");

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid2.activityid).child("actualPoints").setValue(countOnes+"");

                    }else{

                        //means the user click this button for first time
                        String modify = temp.substring(0,17)+"1"+temp.substring(18);

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid2.activityid).child("sprintDailyPoints").setValue(modify);
                        btCalendarDay39.setBackgroundColor(Color.GREEN);

                        Dashboard.userActivityContributionid2.sprintDailyPoints = modify;

                        String Modifystr = modify+"";

                        int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                        Dashboard.userActivityContributionid2.actualPoints = countOnes+"";
                        setTextView2(countOnes+"");

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid2.activityid).child("actualPoints").setValue(countOnes+"");
                    }

                }
            });


            final Button btCalendarDay40 = (Button)view.findViewById(R.id.bt40);
            btCalendarDay40.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){

                    String temp = Dashboard.userActivityContributionid2.sprintDailyPoints;

                    int color = 0;
                    Drawable backgroundcolor = btCalendarDay40.getBackground();
                    if (backgroundcolor instanceof ColorDrawable) {
                        color = ((ColorDrawable)backgroundcolor).getColor();
                    }

                    if(color == Color.GREEN){

                        //means its already green so the user wants to DESELECT so update the database
                        String modify1 = temp.substring(0,18)+"0"+temp.substring(19);

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid2.activityid).child("sprintDailyPoints").setValue(modify1);
                        btCalendarDay40.setBackgroundColor(Color.LTGRAY);

                        Dashboard.userActivityContributionid2.sprintDailyPoints = modify1; //maintain a copy global instead of accessing to database

                        String Modifystr = modify1+"";

                        int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                        Dashboard.userActivityContributionid2.actualPoints = countOnes+"";
                        setTextView2(countOnes+"");

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid2.activityid).child("actualPoints").setValue(countOnes+"");

                    }else{

                        //means the user click this button for first time
                        String modify = temp.substring(0,18)+"1"+temp.substring(19);

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid2.activityid).child("sprintDailyPoints").setValue(modify);
                        btCalendarDay40.setBackgroundColor(Color.GREEN);

                        Dashboard.userActivityContributionid2.sprintDailyPoints = modify;

                        String Modifystr = modify+"";

                        int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                        Dashboard.userActivityContributionid2.actualPoints = countOnes+"";
                        setTextView2(countOnes+"");

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid2.activityid).child("actualPoints").setValue(countOnes+"");
                    }

                }
            });


            final Button btCalendarDay41 = (Button)view.findViewById(R.id.bt41);
            btCalendarDay41.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){


                    String temp = Dashboard.userActivityContributionid2.sprintDailyPoints;

                    int color = 0;
                    Drawable backgroundcolor = btCalendarDay41.getBackground();
                    if (backgroundcolor instanceof ColorDrawable) {
                        color = ((ColorDrawable)backgroundcolor).getColor();
                    }

                    if(color == Color.GREEN){

                        //means its already green so the user wants to DESELECT so update the database
                        String modify1 = temp.substring(0,19)+"0"+temp.substring(20);

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid2.activityid).child("sprintDailyPoints").setValue(modify1);
                        btCalendarDay41.setBackgroundColor(Color.LTGRAY);

                        Dashboard.userActivityContributionid2.sprintDailyPoints = modify1; //maintain a copy global instead of accessing to database

                        String Modifystr = modify1+"";

                        int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                        Dashboard.userActivityContributionid2.actualPoints = countOnes+"";
                        setTextView2(countOnes+"");

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid2.activityid).child("actualPoints").setValue(countOnes+"");

                    }else{

                        //means the user click this button for first time
                        String modify = temp.substring(0,19)+"1"+temp.substring(20);

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid2.activityid).child("sprintDailyPoints").setValue(modify);
                        btCalendarDay41.setBackgroundColor(Color.GREEN);

                        Dashboard.userActivityContributionid2.sprintDailyPoints = modify;

                        String Modifystr = modify+"";

                        int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                        Dashboard.userActivityContributionid2.actualPoints = countOnes+"";
                        setTextView2(countOnes+"");

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid2.activityid).child("actualPoints").setValue(countOnes+"");
                    }
                }
            });


            final Button btCalendarDay42 = (Button)view.findViewById(R.id.bt42);
            btCalendarDay42.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){

                    String temp = Dashboard.userActivityContributionid2.sprintDailyPoints;

                    int color = 0;
                    Drawable backgroundcolor = btCalendarDay42.getBackground();
                    if (backgroundcolor instanceof ColorDrawable) {
                        color = ((ColorDrawable)backgroundcolor).getColor();
                    }

                    if(color == Color.GREEN){

                        //means its already green so the user wants to DESELECT so update the database
                        String modify1 = temp.substring(0,20)+"0"+temp.substring(21);

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid2.activityid).child("sprintDailyPoints").setValue(modify1);
                        btCalendarDay42.setBackgroundColor(Color.LTGRAY);

                        Dashboard.userActivityContributionid2.sprintDailyPoints = modify1; //maintain a copy global instead of accessing to database

                        String Modifystr = modify1+"";

                        int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                        Dashboard.userActivityContributionid2.actualPoints = countOnes+"";
                        setTextView2(countOnes+"");

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid2.activityid).child("actualPoints").setValue(countOnes+"");

                    }else{

                        //means the user click this button for first time
                        String modify = temp.substring(0,20)+"1"+temp.substring(21);

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid2.activityid).child("sprintDailyPoints").setValue(modify);
                        btCalendarDay42.setBackgroundColor(Color.GREEN);

                        Dashboard.userActivityContributionid2.sprintDailyPoints = modify;

                        String Modifystr = modify+"";

                        int countOnes = Modifystr.length() - Modifystr.replaceAll("1","").length();
                        Dashboard.userActivityContributionid2.actualPoints = countOnes+"";
                        setTextView2(countOnes+"");

                        databaseUpdateActivities = FirebaseDatabase.getInstance().getReference("Activities");
                        databaseUpdateActivities.child(Dashboard.userActivityContributionid2.activityid).child("actualPoints").setValue(countOnes+"");
                    }
                }
            });


            //int d = Dashboard.activitiesJoyMap.size();  //d must be 2


            int numberOfweeks = Integer.parseInt(Dashboard.userContributionSprint.numberOfWeeks);

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
                String sprintJoyDailypoints = Dashboard.userActivityContributionid1.sprintDailyPoints;

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

                String sprintJoyDailypoints2 = Dashboard.userActivityContributionid2.sprintDailyPoints;

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
                String sprintJoyDailypoints = Dashboard.userActivityContributionid1.sprintDailyPoints;
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
                String sprintJoyDailypoints2 = Dashboard.userActivityContributionid2.sprintDailyPoints;

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


                //put the selected days in dashboard
                String sprintContributionDailypoints = Dashboard.userActivityContributionid1.sprintDailyPoints;

                for(int i=0;i<21;i++){

                    if(sprintContributionDailypoints.substring(0,1).equals("1") && i==0){
                        btCalendarDay1.setBackgroundColor(Color.GREEN);
                    }else if(sprintContributionDailypoints.substring(1,2).equals("1") && i==1){
                        btCalendarDay2.setBackgroundColor(Color.GREEN);
                    }else if(sprintContributionDailypoints.substring(2,3).equals("1") && i==2){
                        btCalendarDay3.setBackgroundColor(Color.GREEN);
                    }else if(sprintContributionDailypoints.substring(3,4).equals("1") && i==3){
                        btCalendarDay4.setBackgroundColor(Color.GREEN);
                    }else if(sprintContributionDailypoints.substring(4,5).equals("1") && i==4){
                        btCalendarDay5.setBackgroundColor(Color.GREEN);
                    }else if(sprintContributionDailypoints.substring(5,6).equals("1") && i==5){
                        btCalendarDay6.setBackgroundColor(Color.GREEN);
                    }else if(sprintContributionDailypoints.substring(6,7).equals("1") && i==6){
                        btCalendarDay7.setBackgroundColor(Color.GREEN);
                    }else if(sprintContributionDailypoints.substring(7,8).equals("1") && i==7){
                        btCalendarDay8.setBackgroundColor(Color.GREEN);
                    }else if(sprintContributionDailypoints.substring(8,9).equals("1") && i==8){
                        btCalendarDay9.setBackgroundColor(Color.GREEN);
                    }else if(sprintContributionDailypoints.substring(9,10).equals("1") && i==9){
                        btCalendarDay10.setBackgroundColor(Color.GREEN);
                    }else if(sprintContributionDailypoints.substring(10,11).equals("1") && i==10){
                        btCalendarDay11.setBackgroundColor(Color.GREEN);
                    }else if(sprintContributionDailypoints.substring(11,12).equals("1") && i==11){
                        btCalendarDay12.setBackgroundColor(Color.GREEN);
                    }else if(sprintContributionDailypoints.substring(12,13).equals("1") && i==12){
                        btCalendarDay13.setBackgroundColor(Color.GREEN);
                    }else if(sprintContributionDailypoints.substring(13,14).equals("1") && i==13){
                        btCalendarDay14.setBackgroundColor(Color.GREEN);
                    }else if(sprintContributionDailypoints.substring(14,15).equals("1") && i==14){
                        btCalendarDay15.setBackgroundColor(Color.GREEN);
                    }else if(sprintContributionDailypoints.substring(15,16).equals("1") && i==15){
                        btCalendarDay16.setBackgroundColor(Color.GREEN);
                    }else if(sprintContributionDailypoints.substring(16,17).equals("1") && i==16){
                        btCalendarDay17.setBackgroundColor(Color.GREEN);
                    }else if(sprintContributionDailypoints.substring(17,18).equals("1") && i==17){
                        btCalendarDay18.setBackgroundColor(Color.GREEN);
                    }else if(sprintContributionDailypoints.substring(18,19).equals("1") && i==18){
                        btCalendarDay19.setBackgroundColor(Color.GREEN);
                    }else if(sprintContributionDailypoints.substring(19,20).equals("1") && i==19){
                        btCalendarDay20.setBackgroundColor(Color.GREEN);
                    }else if(sprintContributionDailypoints.substring(20,21).equals("1") && i==20){
                        btCalendarDay21.setBackgroundColor(Color.GREEN);
                    }

                } //end of for




                //second activity (3 rows)


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


                //put the selected days in dashboard
                String sprintJoyDailypoints2 = Dashboard.userActivityContributionid2.sprintDailyPoints;

                for(int i=0;i<21;i++){

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
                    }else if(sprintJoyDailypoints2.substring(14,15).equals("1") && i==14){
                        btCalendarDay36.setBackgroundColor(Color.GREEN);
                    }else if(sprintJoyDailypoints2.substring(15,16).equals("1") && i==15){
                        btCalendarDay37.setBackgroundColor(Color.GREEN);
                    }else if(sprintJoyDailypoints2.substring(16,17).equals("1") && i==16){
                        btCalendarDay38.setBackgroundColor(Color.GREEN);
                    }else if(sprintJoyDailypoints2.substring(17,18).equals("1") && i==17){
                        btCalendarDay39.setBackgroundColor(Color.GREEN);
                    }else if(sprintJoyDailypoints2.substring(18,19).equals("1") && i==18){
                        btCalendarDay40.setBackgroundColor(Color.GREEN);
                    }else if(sprintJoyDailypoints2.substring(19,20).equals("1") && i==19){
                        btCalendarDay41.setBackgroundColor(Color.GREEN);
                    }else if(sprintJoyDailypoints2.substring(20,21).equals("1") && i==20){
                        btCalendarDay42.setBackgroundColor(Color.GREEN);
                    }

                } //end of for

            }


            // Find the components for gaugeJoyActivity1
            final TextView counterContributionActivity1 = (TextView) view.findViewById(R.id.counter_joy_activity1);
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
                    counterContributionActivity1.setText((int) highValue + "%");

                }
            });

            // Find the components for gaugeJoyActivity1
            final TextView counterContributionActivity2 = (TextView) view.findViewById(R.id.counter_joy_activity2);
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
                    counterContributionActivity2.setText((int) highValue + "%");

                }
            });

            // Find the components for gaugeJoyScore
            final TextView counterContributionScore = (TextView) view.findViewById(R.id.counter_joy_score);
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
                    counterContributionScore.setText((int) highValue + "%");

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


            q1_contribution = (EditText)view.findViewById(R.id.q1Goals);
            q2_contribution = (EditText)view.findViewById(R.id.q2Goals);
            q3_contribution = (EditText)view.findViewById(R.id.q3Goals);
            q4_contribution = (EditText)view.findViewById(R.id.q4Goals);


            submitGoalbtn_Contribution = (Button)view.findViewById(R.id.submitButtonGoals);

            submitGoalbtn_Contribution.setOnClickListener(this);

            return view;

        }

    public void onButtonClick(Button view){
        view.setBackgroundColor(Color.GREEN);
    }

    @Override
    public void onClick(View view) {

        if(view == submitGoalbtn_Contribution){
            checkAnswers();
        }

    } //end of onClick



    public void checkAnswers(){

        String answer1 = q1_contribution.getText().toString();
        String answer2 = q2_contribution.getText().toString();
        String answer3 = q3_contribution.getText().toString();
        String answer4 = q4_contribution.getText().toString();

        if(answer1.length()==0) {
            Toast.makeText(getActivity().getApplicationContext(), "Your first answer doesn't have any characters, please try again", Toast.LENGTH_LONG).show();
            return;
        }
        if(answer2.length()==0) {
            Toast.makeText(getActivity().getApplicationContext(), "Your second answer doesn't have any characters, please try again", Toast.LENGTH_LONG).show();
            return;
        }
        if(answer3.length()==0) {
            Toast.makeText(getActivity().getApplicationContext(), "Your third answer don't have any characters, please try again", Toast.LENGTH_LONG).show();
            return;
        }
        if(answer4.length()==0) {
            Toast.makeText(getActivity().getApplicationContext(), "Your fourth answer doesn't have any characters, please try again", Toast.LENGTH_LONG).show();
            return;
        }


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

            databaseUpdateCategories.child(Dashboard.userActivityContributionid1.categoryId).child("ContributionSprints").child(Dashboard.sprintContributionid).child("goal1").setValue(q1_contribution.getText().toString());
            databaseUpdateCategories.child(Dashboard.userActivityContributionid1.categoryId).child("ContributionSprints").child(Dashboard.sprintContributionid).child("goal2").setValue(q2_contribution.getText().toString());
            databaseUpdateCategories.child(Dashboard.userActivityContributionid1.categoryId).child("ContributionSprints").child(Dashboard.sprintContributionid).child("goal3").setValue(q3_contribution.getText().toString());
            databaseUpdateCategories.child(Dashboard.userActivityContributionid1.categoryId).child("ContributionSprints").child(Dashboard.sprintContributionid).child("goal4").setValue(q4_contribution.getText().toString());

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

    public void setTextView(String value){
        textActual1.setText(value);
    }

    public void setTextView2(String value){
        textActual2.setText(value);
    }

    }

package com.example.natalia.lifemanagementfirst;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.KeyListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by Natalia on 10/5/2017.
 */

public class SprintSettingPageFragment extends Fragment implements TextWatcher{

    static TextView sprintStartDate;
    static TextView sprintEndDate;
    private DatePickerDialog.OnDateSetListener mStartDateSetListener;
    private DatePickerDialog.OnDateSetListener mEndDateSetListener;

    private RadioGroup radioGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    static String sprintPeriod;

    //Calendar type variables to use later to calculate difference between dates for sprint selected by user
    Calendar sdate;
    Calendar edate;
    Calendar calToday;
    private final static long MILLISEC_PER_DAY = 24*60*60*1000;

    private EditText sprintGoal;
    //static EditText sprintPeriod;
    static long sprintPeriodInDays;
    private TextView swipeText;
    //KeyListener mKeyListenerSprintPeriod;  // used to disable sprintPeriod EditText to take user input after user successfully selected sprint end date
    //KeyListener mKeyListenerStartDate;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_sprint_setting_page,container,false);

        // User input validations
        sprintGoal = (EditText)rootView.findViewById(R.id.sprintGoalA);
        sprintGoal.addTextChangedListener(this);

        /*
        sprintPeriod = (EditText)rootView.findViewById(R.id.sprintPeriodA);
        sprintPeriod.setVisibility(View.INVISIBLE);
        //mKeyListenerSprintPeriod = sprintPeriod.getKeyListener();
        sprintPeriod.addTextChangedListener(this);
        */

        radioGroup = (RadioGroup)rootView.findViewById(R.id.sprintPeriodButtonsGroup);
        radioGroup.setVisibility(View.INVISIBLE);
        rb1 = (RadioButton)rootView.findViewById(R.id.sprintPeriod1);
        rb2 = (RadioButton)rootView.findViewById(R.id.sprintPeriod2);
        rb3 = (RadioButton)rootView.findViewById(R.id.sprintPeriod3);



        rb1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                onRadioBtnClicked(rb1);
            }
        });

        rb2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                onRadioBtnClicked(rb2);
            }
        });

        rb3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                onRadioBtnClicked(rb3);
            }
        });



        /*
        if(incorrectInput())
        {
            Toast.makeText(getActivity(),"Sprint goal MUST be at least 4 characters long",Toast.LENGTH_SHORT).show();
        }
        */

        // End user input validations



        sprintStartDate = (TextView)rootView.findViewById(R.id.sprintStartDate);
        sprintStartDate.setVisibility(View.INVISIBLE);
        sprintStartDate.addTextChangedListener(this);
        //mKeyListenerStartDate = sprintStartDate.getKeyListener();



        sprintStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                calToday = Calendar.getInstance();
                int year = calToday.get(Calendar.YEAR);
                int month = calToday.get(Calendar.MONTH);
                int day = calToday.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        getActivity(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mStartDateSetListener,
                        year,month,day);

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mStartDateSetListener = new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day){
                // to set Calendar type var sdate to the values selected by user
                sdate = Calendar.getInstance();
                sdate.set(Calendar.DAY_OF_MONTH,day);
                sdate.set(Calendar.MONTH,month);
                sdate.set(Calendar.YEAR,year);

                month = month + 1;
                String date = month + "/" + day + "/" + year;
                sprintStartDate.setText(date);
                // calculate date for end date and set text to sprintEndDate textview

            }
        };


        sprintEndDate = (TextView)rootView.findViewById(R.id.sprintEndDate);
        sprintEndDate.setVisibility(View.INVISIBLE);
        sprintEndDate.addTextChangedListener(this);

        sprintEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        getActivity(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mEndDateSetListener,
                        year,month,day);

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mEndDateSetListener = new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day){
                // to set Calendar type var sdate to the values selected by user
                edate = Calendar.getInstance();
                edate.set(Calendar.DAY_OF_MONTH,day);
                edate.set(Calendar.MONTH,month);
                edate.set(Calendar.YEAR,year);

                month = month + 1;
                String date = month + "/" + day + "/" + year;
                sprintEndDate.setText(date);

            }
        };


        swipeText = (TextView)rootView.findViewById(R.id.swipe);
        swipeText.setVisibility(View.INVISIBLE);



        return rootView;

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count){

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after){

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s == sprintGoal.getEditableText()) {
            //KeyListener mKeyListener = sprintPeriod.getKeyListener();
            if (TextUtils.isEmpty(s)) {
                Toast.makeText(getActivity(), "Please enter sprint goal", Toast.LENGTH_LONG).show();
                // Block other textviews
                //sprintPeriod.setVisibility(View.INVISIBLE);
                radioGroup.setVisibility(View.INVISIBLE);
                //sprintStartDate.setVisibility(View.INVISIBLE);

                //sprintPeriod.setKeyListener(null); // disable sprintPeriod EditText to take user input
                //sprintStartDate.setVisibility(View.INVISIBLE);
                //sprintStartDate.setKeyListener(null); // disable sprintStartDate TextView to take user input
            } else if (s.length() > 5) {
                Toast.makeText(getActivity(), "Sprint goal cannot be more than 160 characters long", Toast.LENGTH_LONG).show();
                s.replace(0, s.length(), s.subSequence(0, 5));
                radioGroup.setVisibility(View.VISIBLE);
                //sprintStartDate.setVisibility(View.VISIBLE);

                //KeyListener mKeyListener = sprintPeriod.getKeyListener();
                //sprintPeriod.setKeyListener(mKeyListenerSprintPeriod); // enable sprintPeriod EditText to take user input
                //sprintPeriod.setVisibility(View.VISIBLE);
                //sprintStartDate.setVisibility(View.VISIBLE);
                //sprintStartDate.setKeyListener(mKeyListenerStartDate); // enable sprintStartDate TextView to take user input
            } else {
                radioGroup.setVisibility(View.VISIBLE);
                //sprintStartDate.setVisibility(View.VISIBLE);

                //KeyListener mKeyListener = sprintPeriod.getKeyListener();
                //sprintPeriod.setKeyListener(mKeyListenerSprintPeriod); // enable sprintPeriod EditText to take user input
                //sprintPeriod.setVisibility(View.VISIBLE);
                //sprintStartDate.setVisibility(View.VISIBLE);
                //sprintStartDate.setKeyListener(mKeyListenerStartDate); // enable sprintStartDate TextView to take user input
            }
        }
        /*
        else if (s == sprintPeriod.getEditableText()) {
            if (TextUtils.isEmpty(s)) {
                Toast.makeText(getActivity(), "Please enter 1, 2, or 3 for sprint period", Toast.LENGTH_LONG).show();
                // Block other textviews for calendars
                sprintStartDate.setVisibility(View.INVISIBLE);
            } else {
                int num = Integer.parseInt(s.toString());
                if (num != 1 && num != 2 && num != 3){
                    //Toast.makeText(getActivity(), "Please enter 1, 2, or 3", Toast.LENGTH_SHORT).show();
                    //s.replace(0,s.length(),"0");
                    s.clear();
                    // Block other textviews for calendars
                }
                else{
                    // Unblock other textviews for calendars
                    sprintStartDate.setVisibility(View.VISIBLE);
                    sprintPeriodInDays = num * 7;
                }
            }


        } */
        else if (s == sprintStartDate.getEditableText()){
            if(TextUtils.isEmpty(s)){
                Toast.makeText(getActivity(), "Please select sprint start date that is Monday", Toast.LENGTH_LONG).show();
                sprintEndDate.setVisibility(View.INVISIBLE);
            }
            else{
                // check if sprint start date is after or equal today (must not be before today)
                if(sdate.compareTo(calToday) < 0){
                    Toast.makeText(getActivity(), "Sprint start date must start today or after", Toast.LENGTH_LONG).show();
                    s.clear();
                }
                else {
                    // check if start date is Monday (Sprint must start on Monday)
                    if(sdate.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY){
                        sprintEndDate.setVisibility(View.VISIBLE);
                    }
                    else {
                        Toast.makeText(getActivity(), "Sprint must start on Monday", Toast.LENGTH_LONG).show();
                        s.clear();
                    }
                }
            }
        }
        else if (s == sprintEndDate.getEditableText()){
            if(TextUtils.isEmpty(s)){
                Toast.makeText(getActivity(), "Please select sprint end date", Toast.LENGTH_LONG).show();
                swipeText.setVisibility(View.INVISIBLE);
            }
            else{
                long daysDif = calculateDifInDays(sdate,edate);
                if (daysDif < 0 || daysDif == 0){
                    Toast.makeText(getActivity(), "Sprint end date must be after sprint start date ", Toast.LENGTH_LONG).show();
                    s.clear();
                }
                else{
                    // check if difference between start and end date is equal to selected sprint period
                    // if it's not equal, prompt to choose correct end date
                    if(daysDif != sprintPeriodInDays ){
                        Toast.makeText(getActivity(), "Difference between end and start dates must be equal to selected sprint period ", Toast.LENGTH_LONG).show();
                        s.clear();
                    }
                    else{
                        //sprintPeriod.setKeyListener(null); // disable sprintPeriod EditText to take user input after user successfully selected sprint end date
                        sprintGoal.setKeyListener(null);
                        rb1.setKeyListener(null);
                        rb2.setKeyListener(null);
                        rb3.setKeyListener(null);
                        sprintStartDate.setKeyListener(null);

                        swipeText.setVisibility(View.VISIBLE);
                        //String d = "" + daysDif;
                        //swipeText.setText(d);
                    }
                }


            }
        }
    }

    // Calculate difference in days between two dates.
    public long calculateDifInDays (Calendar s, Calendar e){
        Calendar s2 = (Calendar)s.clone();
        Calendar e2 = (Calendar)e.clone();
        s2.set(Calendar.HOUR_OF_DAY,0);
        s2.set(Calendar.MINUTE,0);
        s2.set(Calendar.SECOND,0);
        s2.set(Calendar.MILLISECOND,0);
        e2.set(Calendar.HOUR_OF_DAY,0);
        e2.set(Calendar.MINUTE,0);
        e2.set(Calendar.SECOND,0);
        e2.set(Calendar.MILLISECOND,0);
        long dif = e2.getTimeInMillis()-s2.getTimeInMillis();
        long daysDif = dif/MILLISEC_PER_DAY;
        //return Math.abs(daysDif);
        return daysDif;
    }

    public void onRadioBtnClicked(View v){
        sprintStartDate.setVisibility(View.VISIBLE);


        //to check if the current radio btn is now checked
        boolean checked = ((RadioButton)v).isChecked();

        //check which radio button is selected
        switch (v.getId()){
            case R.id.sprintPeriod1:
                if(checked) {
                    //if rb1 is selected, set rb1's text to bold, and set sprintPeriod to 1 (week)
                    //rb1.setTypeface(null, Typeface.BOLD);
                    //rb2.setTypeface(null, Typeface.NORMAL);
                    //rb3.setTypeface(null, Typeface.NORMAL);
                    sprintPeriod = "1";
                    int period = Integer.parseInt(sprintPeriod);
                    sprintPeriodInDays = period * 7;
                }
                break;

            case R.id.sprintPeriod2:
                if(checked) {
                    //if rb2 is selected, set rb2's text to bold, and set sprintPeriod to 2 (weeks)
                    //rb2.setTypeface(null, Typeface.BOLD);
                    //rb1.setTypeface(null, Typeface.NORMAL);
                    //rb3.setTypeface(null, Typeface.NORMAL);
                    sprintPeriod = "2";
                    int period = Integer.parseInt(sprintPeriod);
                    sprintPeriodInDays = period * 7;
                }
                break;

            case R.id.sprintPeriod3:
                if(checked) {
                    //if rb3 is selected, set rb3's text to bold, and set sprintPeriod to 3 (weeks)
                    //rb3.setTypeface(null, Typeface.BOLD);
                    //rb1.setTypeface(null, Typeface.NORMAL);
                    //rb2.setTypeface(null, Typeface.NORMAL);
                    sprintPeriod = "3";
                    int period = Integer.parseInt(sprintPeriod);
                    sprintPeriodInDays = period * 7;
                }
                break;



        }
    }




    public boolean incorrectInput(){
        String sprGoal = sprintGoal.getText().toString();
        if (sprGoal.length() < 4 )
        {
            return true;
        }
        return false;
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

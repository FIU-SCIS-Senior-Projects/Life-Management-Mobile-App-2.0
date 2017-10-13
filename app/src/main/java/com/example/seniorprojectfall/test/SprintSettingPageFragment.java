package com.example.seniorprojectfall.test;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;
import java.util.Calendar;

public class SprintSettingPageFragment extends Fragment {

        private TextView sprintStartDate;
        private TextView sprintEndDate;
        private DatePickerDialog.OnDateSetListener mStartDateSetListener;
        private DatePickerDialog.OnDateSetListener mEndDateSetListener;


        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
            ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_sprint_setting_page,container,false);

            sprintStartDate = (TextView)rootView.findViewById(R.id.sprintStartDate);

            sprintStartDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    Calendar cal = Calendar.getInstance();
                    int year = cal.get(Calendar.YEAR);
                    int month = cal.get(Calendar.MONTH);
                    int day = cal.get(Calendar.DAY_OF_MONTH);

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
                    month = month + 1;
                    String date = month + "/" + day + "/" + year;
                    sprintStartDate.setText(date);

                }
            };

            sprintEndDate = (TextView)rootView.findViewById(R.id.sprintEndDate);

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
                    month = month + 1;
                    String date = month + "/" + day + "/" + year;
                    sprintEndDate.setText(date);

                }
            };

            return rootView;

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


package com.example.seniorprojectfall.test;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.sccomponents.gauges.ScArcGauge;
import com.sccomponents.gauges.ScGauge;

public class FragmentPassion extends Fragment {
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

            TextView textAct1 = (TextView)view.findViewById(R.id.textViewAct1);
            textAct1.setText("Learning");

            TextView textAct2 = (TextView)view.findViewById(R.id.textViewAct2);
            textAct2.setText("Nature");

            TextView textCategScore = (TextView)view.findViewById(R.id.textViewCategScore);
            textCategScore.setText("Passion Score");

            // Buttons for Activity #1
            Button btCalendarDay1 = (Button)view.findViewById(R.id.bt1);
            btCalendarDay1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    onButtonClick((Button) view);
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
            btCalendarDay15.setVisibility(View.GONE);

            Button btCalendarDay16 = (Button)view.findViewById(R.id.bt16);
            btCalendarDay16.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    onButtonClick((Button) view);
                }
            });
            btCalendarDay16.setVisibility(View.GONE);

            Button btCalendarDay17 = (Button)view.findViewById(R.id.bt17);
            btCalendarDay17.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    onButtonClick((Button) view);
                }
            });
            btCalendarDay17.setVisibility(View.GONE);

            Button btCalendarDay18 = (Button)view.findViewById(R.id.bt18);
            btCalendarDay18.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    onButtonClick((Button) view);
                }
            });
            btCalendarDay18.setVisibility(View.GONE);

            Button btCalendarDay19 = (Button)view.findViewById(R.id.bt19);
            btCalendarDay19.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    onButtonClick((Button) view);
                }
            });
            btCalendarDay19.setVisibility(View.GONE);

            Button btCalendarDay20 = (Button)view.findViewById(R.id.bt20);
            btCalendarDay20.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    onButtonClick((Button) view);
                }
            });
            btCalendarDay20.setVisibility(View.GONE);

            Button btCalendarDay21 = (Button)view.findViewById(R.id.bt21);
            btCalendarDay21.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    onButtonClick((Button) view);
                }
            });
            btCalendarDay21.setVisibility(View.GONE);

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
            btCalendarDay36.setVisibility(View.GONE);

            Button btCalendarDay37 = (Button)view.findViewById(R.id.bt37);
            btCalendarDay37.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    onButtonClick((Button) view);
                }
            });
            btCalendarDay37.setVisibility(View.GONE);

            Button btCalendarDay38 = (Button)view.findViewById(R.id.bt38);
            btCalendarDay38.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    onButtonClick((Button) view);
                }
            });
            btCalendarDay38.setVisibility(View.GONE);

            Button btCalendarDay39 = (Button)view.findViewById(R.id.bt39);
            btCalendarDay39.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    onButtonClick((Button) view);
                }
            });
            btCalendarDay39.setVisibility(View.GONE);

            Button btCalendarDay40 = (Button)view.findViewById(R.id.bt40);
            btCalendarDay40.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    onButtonClick((Button) view);
                }
            });
            btCalendarDay40.setVisibility(View.GONE);

            Button btCalendarDay41 = (Button)view.findViewById(R.id.bt41);
            btCalendarDay41.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    onButtonClick((Button) view);
                }
            });
            btCalendarDay41.setVisibility(View.GONE);

            Button btCalendarDay42 = (Button)view.findViewById(R.id.bt42);
            btCalendarDay42.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    onButtonClick((Button) view);
                }
            });
            btCalendarDay42.setVisibility(View.GONE);


            // Find the components for gaugePassionActivity1
            final TextView counterPassionActivity1 = (TextView) view.findViewById(R.id.counter_joy_activity1);
            ScArcGauge gaugePassionActivity1 = (ScArcGauge) view.findViewById(R.id.gauge_joy_activity1);

            // Set the features stroke cap style to rounded
            gaugePassionActivity1.findFeature(ScArcGauge.BASE_IDENTIFIER)
                    .getPainter().setStrokeCap(Paint.Cap.ROUND);
            gaugePassionActivity1.findFeature(ScArcGauge.PROGRESS_IDENTIFIER)
                    .getPainter().setStrokeCap(Paint.Cap.ROUND);

            // If you set the value from the xml that not produce an event so I will change the
            // value from code.
            gaugePassionActivity1.setHighValue(60);

            // Each time I will change the value I must write it inside the counter text.
            gaugePassionActivity1.setOnEventListener(new ScGauge.OnEventListener() {
                @Override
                public void onValueChange(float lowValue, float highValue) {
                    counterPassionActivity1.setText((int) highValue + "%");

                }
            });

            // Find the components for gaugePassionActivity1
            final TextView counterPassionActivity2 = (TextView) view.findViewById(R.id.counter_joy_activity2);
            ScArcGauge gaugePassionActivity2 = (ScArcGauge) view.findViewById(R.id.gauge_joy_activity2);

            // Set the features stroke cap style to rounded
            gaugePassionActivity2.findFeature(ScArcGauge.BASE_IDENTIFIER)
                    .getPainter().setStrokeCap(Paint.Cap.ROUND);
            gaugePassionActivity2.findFeature(ScArcGauge.PROGRESS_IDENTIFIER)
                    .getPainter().setStrokeCap(Paint.Cap.ROUND);

            // If you set the value from the xml that not produce an event so I will change the
            // value from code.
            gaugePassionActivity2.setHighValue(60);

            // Each time I will change the value I must write it inside the counter text.
            gaugePassionActivity2.setOnEventListener(new ScGauge.OnEventListener() {
                @Override
                public void onValueChange(float lowValue, float highValue) {
                    counterPassionActivity2.setText((int) highValue + "%");

                }
            });

            // Find the components for gaugePassionScore
            final TextView counterPassionScore = (TextView) view.findViewById(R.id.counter_joy_score);
            ScArcGauge gaugePassionScore = (ScArcGauge) view.findViewById(R.id.gauge_joy_score);

            // Set the features stroke cap style to rounded
            gaugePassionScore.findFeature(ScArcGauge.BASE_IDENTIFIER)
                    .getPainter().setStrokeCap(Paint.Cap.ROUND);
            gaugePassionScore.findFeature(ScArcGauge.PROGRESS_IDENTIFIER)
                    .getPainter().setStrokeCap(Paint.Cap.ROUND);

            // If you set the value from the xml that not produce an event so I will change the
            // value from code.
            gaugePassionScore.setHighValue(60);

            // Each time I will change the value I must write it inside the counter text.
            gaugePassionScore.setOnEventListener(new ScGauge.OnEventListener() {
                @Override
                public void onValueChange(float lowValue, float highValue) {
                    counterPassionScore.setText((int) highValue + "%");

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


            return view;

        }

        public void onButtonClick(Button view){
            view.setBackgroundColor(Color.GREEN);
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

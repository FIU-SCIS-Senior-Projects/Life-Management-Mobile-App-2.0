package com.example.seniorprojectfall.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SprintSettingPage2Fragment extends Fragment implements TextWatcher {

        static EditText joyAct1goal;
        static EditText joyAct2goal;
        private EditText passionAct1goal;
        private EditText passionAct2goal;
        private EditText contribAct1goal;
        private EditText contribAct2goal;
        static Button submitSettings;
        private TextView joyAct1;
        private TextView joyAct2;
        private TextView passionAct1;
        private TextView passionAct2;
        private TextView contribAct1;
        private TextView contribAct2;


        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
            ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_sprint_setting_page2,container,false);

            joyAct1 = (TextView)rootView.findViewById(R.id.joyAct1);
            joyAct2 = (TextView)rootView.findViewById(R.id.joyAct2);
            passionAct1 = (TextView)rootView.findViewById(R.id.passionAct1);
            passionAct2 = (TextView)rootView.findViewById(R.id.passionAct2);
            contribAct1 = (TextView)rootView.findViewById(R.id.contributionAct1);
            contribAct2 = (TextView)rootView.findViewById(R.id.contributionAct2);

            joyAct1.setText(CoverFlowAdapter.n.get(0));
            joyAct2.setText(CoverFlowAdapter.n.get(1));
            passionAct1.setText(CoverFlowAdapterPassion.n.get(0));
            passionAct2.setText(CoverFlowAdapterPassion.n.get(1));
            contribAct1.setText(MainGivingBackAdapter.givBackActSelected.get(0));
            contribAct2.setText(MainGivingBackAdapter.givBackActSelected.get(1));


            joyAct1goal = (EditText)rootView.findViewById(R.id.joyAct1Goal);
            joyAct1goal.addTextChangedListener(this);

            joyAct2goal = (EditText)rootView.findViewById(R.id.joyAct2Goal);
            joyAct2goal.setVisibility(View.INVISIBLE);
            joyAct2goal.addTextChangedListener(this);

            passionAct1goal = (EditText)rootView.findViewById(R.id.passionAct1Goal);
            passionAct1goal.setVisibility(View.INVISIBLE);
            passionAct1goal.addTextChangedListener(this);

            passionAct2goal = (EditText)rootView.findViewById(R.id.passionAct2Goal);
            passionAct2goal.setVisibility(View.INVISIBLE);
            passionAct2goal.addTextChangedListener(this);

            contribAct1goal = (EditText)rootView.findViewById(R.id.contributionAct1Goal);
            contribAct1goal.setVisibility(View.INVISIBLE);
            contribAct1goal.addTextChangedListener(this);

            contribAct2goal = (EditText)rootView.findViewById(R.id.contributionAct2Goal);
            contribAct2goal.setVisibility(View.INVISIBLE);
            contribAct2goal.addTextChangedListener(this);

            submitSettings = (Button)rootView.findViewById(R.id.submitActGoals);
            submitSettings.setVisibility(View.INVISIBLE);
            submitSettings.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SprintSettingActivity.saveSettings();
                    Intent i = new Intent(getActivity(),LoginActivity.class);
                    startActivity(i);

                }
            });





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

            if (s == joyAct1goal.getEditableText()) {
                if (TextUtils.isEmpty(s)) {
                    Toast.makeText(getActivity(), "Please enter activity goal number", Toast.LENGTH_LONG).show();
                    // Block other textviews
                    joyAct2goal.setVisibility(View.INVISIBLE);

                } else {
                    long num = Long.parseLong(s.toString());
                    if (num < 0 || num > SprintSettingPageFragment.sprintPeriodInDays) {
                        Toast.makeText(getActivity(), "Goal number must be positive and < or = number of days in selected sprint period", Toast.LENGTH_LONG).show();
                        s.clear();
                        // Block other textviews
                    } else {
                        // Unblock other textviews
                        joyAct2goal.setVisibility(View.VISIBLE);
                    }
                }


            }
            else if (s == joyAct2goal.getEditableText()) {
                if (TextUtils.isEmpty(s)) {
                    Toast.makeText(getActivity(), "Please enter activity goal number", Toast.LENGTH_LONG).show();
                    // Block other textviews
                    passionAct1goal.setVisibility(View.INVISIBLE);

                } else {
                    long num = Long.parseLong(s.toString());
                    if (num < 0 || num > SprintSettingPageFragment.sprintPeriodInDays) {
                        Toast.makeText(getActivity(), "Goal number must be positive and < or = number of days in selected sprint period", Toast.LENGTH_LONG).show();
                        s.clear();
                        // Block other textviews
                    } else {
                        // Unblock other textviews
                        passionAct1goal.setVisibility(View.VISIBLE);
                    }
                }


            }
            else if (s == passionAct1goal.getEditableText()) {
                if (TextUtils.isEmpty(s)) {
                    Toast.makeText(getActivity(), "Please enter activity goal number", Toast.LENGTH_LONG).show();
                    // Block other textviews
                    passionAct2goal.setVisibility(View.INVISIBLE);

                } else {
                    long num = Long.parseLong(s.toString());
                    if (num < 0 || num > SprintSettingPageFragment.sprintPeriodInDays) {
                        Toast.makeText(getActivity(), "Goal number must be positive and < or = number of days in selected sprint period", Toast.LENGTH_LONG).show();
                        s.clear();
                        // Block other textviews
                    } else {
                        // Unblock other textviews
                        passionAct2goal.setVisibility(View.VISIBLE);
                    }
                }


            }
            else if (s == passionAct2goal.getEditableText()) {
                if (TextUtils.isEmpty(s)) {
                    Toast.makeText(getActivity(), "Please enter activity goal number", Toast.LENGTH_LONG).show();
                    // Block other textviews
                    contribAct1goal.setVisibility(View.INVISIBLE);

                } else {
                    long num = Long.parseLong(s.toString());
                    if (num < 0 || num > SprintSettingPageFragment.sprintPeriodInDays) {
                        Toast.makeText(getActivity(), "Goal number must be positive and < or = number of days in selected sprint period", Toast.LENGTH_LONG).show();
                        s.clear();
                        // Block other textviews
                    } else {
                        // Unblock other textviews
                        contribAct1goal.setVisibility(View.VISIBLE);
                    }
                }


            }
            else if (s == contribAct1goal.getEditableText()) {
                if (TextUtils.isEmpty(s)) {
                    Toast.makeText(getActivity(), "Please enter activity goal number", Toast.LENGTH_LONG).show();
                    // Block other textviews
                    contribAct2goal.setVisibility(View.INVISIBLE);

                } else {
                    long num = Long.parseLong(s.toString());
                    if (num < 0 || num > SprintSettingPageFragment.sprintPeriodInDays) {
                        Toast.makeText(getActivity(), "Goal number must be positive and < or = number of days in selected sprint period", Toast.LENGTH_LONG).show();
                        s.clear();
                        // Block other textviews
                    } else {
                        // Unblock other textviews
                        contribAct2goal.setVisibility(View.VISIBLE);
                    }
                }


            }
            else if (s == contribAct2goal.getEditableText()) {
                if (TextUtils.isEmpty(s)) {
                    Toast.makeText(getActivity(), "Please enter activity goal number", Toast.LENGTH_LONG).show();
                    // Block other textviews
                    submitSettings.setVisibility(View.INVISIBLE);

                } else {
                    long num = Long.parseLong(s.toString());
                    if (num < 0 || num > SprintSettingPageFragment.sprintPeriodInDays) {
                        Toast.makeText(getActivity(), "Goal number must be positive and < or = number of days in selected sprint period", Toast.LENGTH_LONG).show();
                        s.clear();
                        // Block other textviews
                    } else {
                        joyAct1goal.setKeyListener(null);
                        joyAct2goal.setKeyListener(null);
                        passionAct1goal.setKeyListener(null);
                        passionAct2goal.setKeyListener(null);
                        contribAct1goal.setKeyListener(null);
                        // Unblock submit button
                        submitSettings.setVisibility(View.VISIBLE);
                    }
                }


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

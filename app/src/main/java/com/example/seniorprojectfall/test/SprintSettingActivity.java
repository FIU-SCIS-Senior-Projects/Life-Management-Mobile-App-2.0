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


public class SprintSettingActivity extends FragmentActivity {

        private static final int NUM_PAGES = 2; //number of pages to show in Sprint Settings

        private ViewPager mPager;

        private PagerAdapter mPagerAdapter;



        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_sprint_setting);

            mPager = (ViewPager) findViewById(R.id.sprintSettingPager);
            mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
            mPager.setAdapter(mPagerAdapter);



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



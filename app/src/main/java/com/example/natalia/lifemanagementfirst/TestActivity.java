package com.example.natalia.lifemanagementfirst;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class TestActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        ViewPager vp_pages= (ViewPager)findViewById(R.id.vp_pages);
        PagerAdapter pagerAdapter=new FragmentAdapter (getSupportFragmentManager());
        vp_pages.setAdapter(pagerAdapter);

        int[] tabIcons = {R.drawable.joy,R.drawable.passion,R.drawable.giving_back};
        TabLayout tbl_pages= (TabLayout)findViewById(R.id.tbl_pages);
        tbl_pages.setupWithViewPager(vp_pages);
        tbl_pages.getTabAt(0).setIcon(tabIcons[0]);
        tbl_pages.getTabAt(1).setIcon(tabIcons[1]);
        tbl_pages.getTabAt(2).setIcon(tabIcons[2]);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }





    class FragmentAdapter extends FragmentPagerAdapter {

        public FragmentAdapter(FragmentManager fm){
            super(fm);
        }
        @Override
        public android.support.v4.app.Fragment getItem(int position){
            switch (position){
                case 0:
                    return new FragmentJoy();
                case 1:
                    return new FragmentPassion();
                case 2:
                    return new FragmentContribution();

            }
            return null;
        }
        @Override
        public int getCount(){
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position){
            switch (position){
                case 0:
                    return "";
                case 1:
                    return "";
                case 2:
                    return "";
                default:return null;

            }
        }


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.test, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_signout) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_new_cycle) {
            // Handle new cycle action
        } else if (id == R.id.nav_current_cycle) {

        } else if (id == R.id.nav_prev_cycle) {

        } else if (id == R.id.nav_settings) {

        } else if (id == R.id.nav_view_coaches) {

        } else if (id == R.id.nav_share_progress) {

        }
        else if (id == R.id.nav_chat) {

        }
        else if (id == R.id.nav_update_calendar) {

        }
        else if (id == R.id.nav_invite_friend) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

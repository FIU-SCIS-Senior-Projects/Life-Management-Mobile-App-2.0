package com.example.seniorprojectfall.test;


import android.app.Fragment;
import android.graphics.Color;
import android.graphics.PorterDuff;
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
import android.widget.EditText;
import android.content.Intent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;


import java.lang.String;
import java.util.*;
import java.lang.*;


    //This is dashboard activity
    public class DashboardActivity extends AppCompatActivity
            implements NavigationView.OnNavigationItemSelectedListener {

        DatabaseReference databaseCategories, databaseCategories2;
        List<Category> currentCategories;

        ArrayList<Category> array;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_test);


            //temp
            Bundle bundle = getIntent().getExtras();
            ArrayList<User> arr = new ArrayList<>();
            arr = bundle.getParcelableArrayList("mylist");


            for(int i=0; i<arr.size();i++) {
                //System.out.println("testing firstname " + f.firstName);
                System.out.println("testing password " + arr.get(i).password);
                System.out.println("testing firstname " + arr.get(i).firstName);
                System.out.println("testing username " + arr.get(i).username);
                System.out.println("testing coach " + arr.get(i).coachFlag);
                System.out.println("testing lastname " + arr.get(i).lastName);
                System.out.println("testing email " + arr.get(i).email);
                System.out.println("testing admin " + arr.get(i).adminFlag);
                System.out.println("testing dob " + arr.get(i).dob);
                //System.out.println("testing sze " + arr.size());

            }

            Intent in = getIntent();
            String us = in.getExtras().getString("userNameY");
            String pa = in.getExtras().getString("passwordY");

            System.out.println("testing USERNAME " + us);
            System.out.println("testing PASSWORD " + pa);



            //getting the reference of artists node
            databaseCategories = FirebaseDatabase.getInstance().getReference("Categories");

            currentCategories = new ArrayList<>();
            array = new ArrayList<Category>();


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

            tbl_pages.getTabAt(0).getIcon().setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN);
            tbl_pages.getTabAt(1).getIcon().setColorFilter(Color.parseColor("#a8a8a8"), PorterDuff.Mode.SRC_IN);
            tbl_pages.getTabAt(2).getIcon().setColorFilter(Color.parseColor("#a8a8a8"), PorterDuff.Mode.SRC_IN);

            tbl_pages.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab){
                    tab.getIcon().setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN);
                }
                @Override
                public void onTabUnselected(TabLayout.Tab tab){
                    tab.getIcon().setColorFilter(Color.parseColor("#a8a8a8"), PorterDuff.Mode.SRC_IN);
                }

                @Override
                public void onTabReselected(TabLayout.Tab tab){

                }
            });


            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.setDrawerListener(toggle);
            toggle.syncState();

            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);




            databaseCategories.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    //clear the list
                    currentCategories.clear();

                    //iterate through all the node

                    for(DataSnapshot categorySnapshot: dataSnapshot.getChildren()){

                        DataSnapshot activitiesSnapshot = categorySnapshot.child("Category 1").child("Sprint").child("Activities");

                            System.out.println("xxx1 " + activitiesSnapshot.getKey()+ ": " + activitiesSnapshot.getValue());
                            for(DataSnapshot activitySnapshot: activitiesSnapshot.getChildren()){
                                System.out.println("xxx " + activitySnapshot.getKey()+ ": " + activitySnapshot.getValue(String.class));
                            }
                        }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });



        } //end of create


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
                        return "Joy";
                    case 1:
                        return "Passion";
                    case 2:
                        return "Contribution";
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

} //end of class
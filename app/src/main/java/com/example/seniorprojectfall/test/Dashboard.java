package com.example.seniorprojectfall.test;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.*;
import android.content.Intent;


//This is dashboard activity
public class Dashboard extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {



    //JOY variables
    static ArrayList<ActivitiesSprint> allActivities;
    static ArrayList<ActivitiesSprint> userActivitiesAll;
    static ArrayList<Category> currentJoyCategories;
    static ArrayList<Category> userJoysprintsHelper;
    static Category userJoySprint;
    static ActivitiesSprint userActivityJoyid1;
    static ActivitiesSprint userActivityJoyid2;
    static String endingDateFixed;
    static String startingDateFixed;
    static String sprintJoyid;
    //static TreeMap<Integer,ActivitiesSprint> activitiesJoyMap; //for later


    User currentUser; //holds the information of the current logged-in user


    //PASSION variables
    ActivitiesSprint currentPassionActivity;
    static String passion_endingDateFixed;
    static String passion_startingDateFixed;
    static String sprintPassionid;
    static TreeMap<Integer,ActivitiesSprint> activitiesPassionMap;
    static ActivitiesSprint activity1_static_passion;
    static ActivitiesSprint activity2_static_passion;
    static Category passion_currentUserCategory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //JOY
        allActivities = new ArrayList<>();
        userActivitiesAll = new ArrayList<>();
        currentJoyCategories = new ArrayList<>();
        userJoysprintsHelper = new ArrayList<>();
        userJoySprint = new Category();
        userActivityJoyid1 = new ActivitiesSprint();
        userActivityJoyid2 = new ActivitiesSprint();


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
                System.out.println("tabselected " + tab.getIcon());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab){
                tab.getIcon().setColorFilter(Color.parseColor("#a8a8a8"), PorterDuff.Mode.SRC_IN);
                System.out.println("tabunselected ");
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab){

            }
        });



        //Lazaro code for DB

        Intent in = getIntent();
        String usernameRef = in.getExtras().getString("userNameY");  //has username that the user entered
        String passRef = in.getExtras().getString("passwordY");  //the password that user entered

        sprintJoyid = in.getExtras().getString("joySprintId");


        userJoySprint.categoryid = in.getExtras().getString("joy_userJoySprint_categoryid");
        userJoySprint.endingDate = in.getExtras().getString("joy_userJoySprint_endingdate");
        userJoySprint.goal1 = in.getExtras().getString("joy_userJoySprint_goal1");
        userJoySprint.goal2 = in.getExtras().getString("joy_userJoySprint_goal2");
        userJoySprint.goal3 = in.getExtras().getString("joy_userJoySprint_goal3");
        userJoySprint.goal4 = in.getExtras().getString("joy_userJoySprint_goal4");
        userJoySprint.numberOfWeeks = in.getExtras().getString("joy_userJoySprint_numberofweeks");
        userJoySprint.sprintActivityid1 = in.getExtras().getString("joy_userJoySprint_sprintactivityid1");
        userJoySprint.sprintActivityid2 = in.getExtras().getString("joy_UserJoySprint_sprintactivityid2");
        userJoySprint.sprintOverallScore = in.getExtras().getString("joy_UserJoySprint_sprintoverallscore");
        userJoySprint.startingDate = in.getExtras().getString("joy_UserJoySprint_startingdate");
        userJoySprint.userId = in.getExtras().getString("joy_UserJoySprint_userid");


        userActivityJoyid1.activityScore = in.getExtras().getString("joy_activityid1_activityscore");
        userActivityJoyid1.actualPoints = in.getExtras().getString("joy_activityid1_actualpoints");
        userActivityJoyid1.categoryId = in.getExtras().getString("joy_activityid1_categoryid");
        userActivityJoyid1.activityName = in.getExtras().getString("joy_activityid1_activityname");
        userActivityJoyid1.sprintDailyPoints = in.getExtras().getString("joy_activityid1_sprintdailypoints");
        userActivityJoyid1.targetPoints = in.getExtras().getString("joy_activityid1_targetpoints");
        userActivityJoyid1.userId = in.getExtras().getString("joy_activityid1_userid");
        userActivityJoyid1.activityid = in.getExtras().getString("joy_activityid1_activityid");

        userActivityJoyid2.activityScore = in.getExtras().getString("joy_activityid2_activityscore");
        userActivityJoyid2.actualPoints = in.getExtras().getString("joy_activityid2_actualpoints");
        userActivityJoyid2.categoryId = in.getExtras().getString("joy_activityid2_categoryid");
        userActivityJoyid2.activityName = in.getExtras().getString("joy_activityid2_activityname");
        userActivityJoyid2.sprintDailyPoints = in.getExtras().getString("joy_activityid2_sprintdailypoints");
        userActivityJoyid2.targetPoints = in.getExtras().getString("joy_activityid2_targetpoints");
        userActivityJoyid2.userId = in.getExtras().getString("joy_activityid2_userid");
        userActivityJoyid2.activityid = in.getExtras().getString("joy_activityid2_activityid");



        //temp
        Bundle bundle = getIntent().getExtras();
        ArrayList<User> arr = new ArrayList<>();

        arr = bundle.getParcelableArrayList("mylist");


        for(int i=0; i<arr.size();i++) {

    //(email,currentusername,firstN,lastN,Dob,password,false,false,id);
            if((arr.get(i).username.toString().equals(usernameRef) && (arr.get(i).password.toString().equals(passRef)))){
                    currentUser = new User(arr.get(i).email.toString(),arr.get(i).username.toString(),
                                            arr.get(i).firstName.toString(),arr.get(i).lastName.toString(),
                                            arr.get(i).dob.toString(),arr.get(i).password.toString(),
                                            arr.get(i).adminFlag,arr.get(i).coachFlag,arr.get(i).id.toString());
            }
        }

        allActivities = bundle.getParcelableArrayList("allActivities");
        userActivitiesAll = bundle.getParcelableArrayList("userActivitiesAllList");
        currentJoyCategories = bundle.getParcelableArrayList("categoriesJoyCategories");
        userJoysprintsHelper = bundle.getParcelableArrayList("userJoysprintHelperList");


        //ArrayList<ActivitiesSprint> activitiesJoyList = new ArrayList<>();








/*
        System.out.println(" 1qact " + activity1_static_passion.activityScore);
        System.out.println(" 1qactpoints " + activity1_static_passion.actualPoints);
        System.out.println(" 1qcatg id " + activity1_static_passion.categoryId);
        System.out.println(" 1qname " + activity1_static_passion.activityName);
        System.out.println(" 1qsprintpoints " + activity1_static_passion.sprintDailyPoints);
        System.out.println(" 1qtarget " + activity1_static_passion.targetPoints);
        System.out.println(" 1quserid " + activity1_static_passion.userId);
        System.out.println(" 1qactid " + activity1_static_passion.activityid);
*/



        int j = 0;
        int k = 0;


        //JOY



        //convert to format mm/dd/yyyy
        endingDateFixed = userJoySprint.endingDate.substring(0,2) + "/" +
                userJoySprint.endingDate.substring(2,4) + "/" + userJoySprint.endingDate.substring(4);

        //convert to format mm/dd/yyyy
        //System.out.println("heystart " + startRef);
        startingDateFixed = userJoySprint.startingDate.substring(0,2) + "/" +
                userJoySprint.startingDate.substring(2,4) + "/" + userJoySprint.startingDate.substring(4);


        System.out.println("sprintjoyid " + sprintJoyid);







        /*
        passion_currentUserCategory.categoryid = in.getExtras().getString("categoryPassion_categoryid");
        passion_currentUserCategory.endingDate = in.getExtras().getString("categoryPassion_endingDate");
        passion_currentUserCategory.goal1 = in.getExtras().getString("categoryPassion_goal1");
        passion_currentUserCategory.goal2 = in.getExtras().getString("categoryPassion_goal2");
        passion_currentUserCategory.goal3 = in.getExtras().getString("categoryPassion_goal3");
        passion_currentUserCategory.goal4 = in.getExtras().getString("categoryPassion_goal4");
        passion_currentUserCategory.numberOfWeeks = in.getExtras().getString("categoryPassion_numberofweeks");
        passion_currentUserCategory.sprintActivityid1 = in.getExtras().getString("categoryPassion_sprintact1");
        passion_currentUserCategory.sprintActivityid2 = in.getExtras().getString("categoryPassion_sprintact2");
        passion_currentUserCategory.sprintOverallScore = in.getExtras().getString("categoryPassion_overallscore");
        passion_currentUserCategory.startingDate = in.getExtras().getString("categoryPassion_startingDate");
        passion_currentUserCategory.userId = in.getExtras().getString("categoryPassion_userId");



        //convert to format mm/dd/yyyy
        passion_endingDateFixed = passion_currentUserCategory.endingDate.substring(0,2) + "/" +
                passion_currentUserCategory.endingDate.substring(2,4) + "/" + passion_currentUserCategory.endingDate.substring(4);

        //convert to format mm/dd/yyyy
        //System.out.println("heystart " + startRef);
        passion_startingDateFixed = passion_currentUserCategory.startingDate.substring(0,2) + "/" +
                passion_currentUserCategory.startingDate.substring(2,4) + "/" + passion_currentUserCategory.startingDate.substring(4);


        System.out.println("sprintjoyid " + sprintPassionid);
        */


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        
    } //end of onCreate




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

                    return new FragmentGivingBack();

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
        getMenuInflater().inflate(R.menu.dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        System.out.println("id in optionSelected method " + id);

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

            Intent i = new Intent(Dashboard.this,CycleActivity.class);
            this.startActivity(i);

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

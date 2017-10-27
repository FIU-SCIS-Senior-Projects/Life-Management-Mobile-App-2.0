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


    User currentUser; //holds the information of the current logged-in user


    //PASSION variables

    static ArrayList<Category> currentPassionCategories;
    static ArrayList<Category> userPassionsprintsHelper;
    static Category userPassionSprint;
    static ActivitiesSprint userActivityPassionid1;
    static ActivitiesSprint userActivityPassionid2;
    static String endingDateFixed_passion;
    static String startingDateFixed_passion;
    static String sprintPassionid;

    //GIVING BACK variables

    static ArrayList<Category> currentContributionCategories;
    static ArrayList<Category> userContributionsprintsHelper;
    static Category userContributionSprint;
    static ActivitiesSprint userActivityContributionid1;
    static ActivitiesSprint userActivityContributionid2;
    static String endingDateFixed_contribution;
    static String startingDateFixed_contribution;
    static String sprintContributionid;


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

        //PASSION
        currentPassionCategories = new ArrayList<>();
        userPassionsprintsHelper = new ArrayList<>();
        userPassionSprint = new Category();
        userActivityPassionid1 = new ActivitiesSprint();
        userActivityPassionid2 = new ActivitiesSprint();

        //GIVING BACK
        currentContributionCategories = new ArrayList<>();
        userContributionsprintsHelper = new ArrayList<>();
        userContributionSprint = new Category();
        userActivityContributionid1 = new ActivitiesSprint();
        userActivityContributionid2 = new ActivitiesSprint();


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

        //JOY

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


        //PASSION

        sprintPassionid = in.getExtras().getString("passionSprintId");

        userPassionSprint.categoryid = in.getExtras().getString("passion_userPassionSprint_categoryid");
        userPassionSprint.endingDate = in.getExtras().getString("passion_userPassionSprint_endingdate");
        userPassionSprint.goal1 = in.getExtras().getString("passion_userPassionSprint_goal1");
        userPassionSprint.goal2 = in.getExtras().getString("passion_userPassionSprint_goal2");
        userPassionSprint.goal3 = in.getExtras().getString("passion_userPassionSprint_goal3");
        userPassionSprint.goal4 = in.getExtras().getString("passion_userPassionSprint_goal4");
        userPassionSprint.numberOfWeeks = in.getExtras().getString("passion_userPassionSprint_numberofweeks");
        userPassionSprint.sprintActivityid1 = in.getExtras().getString("passion_userPassionSprint_sprintactivityid1");
        userPassionSprint.sprintActivityid2 = in.getExtras().getString("passion_userPassionSprint_sprintactivityid2");
        userPassionSprint.sprintOverallScore = in.getExtras().getString("passion_userPassionSprint_sprintoverallscore");
        userPassionSprint.startingDate = in.getExtras().getString("passion_userPassionSprint_startingdate");
        userPassionSprint.userId = in.getExtras().getString("passion_userPassionSprint_userid");


        userActivityPassionid1.activityScore = in.getExtras().getString("passion_activityid1_activityscore");
        userActivityPassionid1.actualPoints = in.getExtras().getString("passion_activityid1_actualpoints");
        userActivityPassionid1.categoryId = in.getExtras().getString("passion_activityid1_categoryid");
        userActivityPassionid1.activityName = in.getExtras().getString("passion_activityid1_activityname");
        userActivityPassionid1.sprintDailyPoints = in.getExtras().getString("passion_activityid1_sprintdailypoints");
        userActivityPassionid1.targetPoints = in.getExtras().getString("passion_activityid1_targetpoints");
        userActivityPassionid1.userId = in.getExtras().getString("passion_activityid1_userid");
        userActivityPassionid1.activityid = in.getExtras().getString("passion_activityid1_activityid");

        userActivityPassionid2.activityScore = in.getExtras().getString("passion_activityid2_activityscore");
        userActivityPassionid2.actualPoints = in.getExtras().getString("passion_activityid2_actualpoints");
        userActivityPassionid2.categoryId = in.getExtras().getString("passion_activityid2_categoryid");
        userActivityPassionid2.activityName = in.getExtras().getString("passion_activityid2_activityname");
        userActivityPassionid2.sprintDailyPoints = in.getExtras().getString("passion_activityid2_sprintdailypoints");
        userActivityPassionid2.targetPoints = in.getExtras().getString("passion_activityid2_targetpoints");
        userActivityPassionid2.userId = in.getExtras().getString("passion_activityid2_userid");
        userActivityPassionid2.activityid = in.getExtras().getString("passion_activityid2_activityid");


        //GIVING BACK

        sprintContributionid = in.getExtras().getString("contributionSprintId");

        userContributionSprint.categoryid = in.getExtras().getString("contribution_userContributionSprint_categoryid");
        userContributionSprint.endingDate = in.getExtras().getString("contribution_userContributionSprint_endingdate");
        userContributionSprint.goal1 = in.getExtras().getString("contribution_userContributionSprint_goal1");
        userContributionSprint.goal2 = in.getExtras().getString("contribution_userContributionSprint_goal2");
        userContributionSprint.goal3 = in.getExtras().getString("contribution_userContributionSprint_goal3");
        userContributionSprint.goal4 = in.getExtras().getString("contribution_userContributionSprint_goal4");
        userContributionSprint.numberOfWeeks = in.getExtras().getString("contribution_userContributionSprint_numberofweeks");
        userContributionSprint.sprintActivityid1 = in.getExtras().getString("contribution_userContributionSprint_sprintactivityid1");
        userContributionSprint.sprintActivityid2 = in.getExtras().getString("contribution_userContributionSprint_sprintactivityid2");
        userContributionSprint.sprintOverallScore = in.getExtras().getString("contribution_userContributionSprint_sprintoverallscore");
        userContributionSprint.startingDate = in.getExtras().getString("contribution_userContributionSprint_startingdate");
        userContributionSprint.userId = in.getExtras().getString("contribution_userContributionSprint_userid");


        userActivityContributionid1.activityScore = in.getExtras().getString("contribution_activityid1_activityscore");
        userActivityContributionid1.actualPoints = in.getExtras().getString("contribution_activityid1_actualpoints");
        userActivityContributionid1.categoryId = in.getExtras().getString("contribution_activityid1_categoryid");
        userActivityContributionid1.activityName = in.getExtras().getString("contribution_activityid1_activityname");
        userActivityContributionid1.sprintDailyPoints = in.getExtras().getString("contribution_activityid1_sprintdailypoints");
        userActivityContributionid1.targetPoints = in.getExtras().getString("contribution_activityid1_targetpoints");
        userActivityContributionid1.userId = in.getExtras().getString("contribution_activityid1_userid");
        userActivityContributionid1.activityid = in.getExtras().getString("contribution_activityid1_activityid");

        userActivityContributionid2.activityScore = in.getExtras().getString("contribution_activityid2_activityscore");
        userActivityContributionid2.actualPoints = in.getExtras().getString("contribution_activityid2_actualpoints");
        userActivityContributionid2.categoryId = in.getExtras().getString("contribution_activityid2_categoryid");
        userActivityContributionid2.activityName = in.getExtras().getString("contribution_activityid2_activityname");
        userActivityContributionid2.sprintDailyPoints = in.getExtras().getString("contribution_activityid2_sprintdailypoints");
        userActivityContributionid2.targetPoints = in.getExtras().getString("contribution_activityid2_targetpoints");
        userActivityContributionid2.userId = in.getExtras().getString("contribution_activityid2_userid");
        userActivityContributionid2.activityid = in.getExtras().getString("contribution_activityid2_activityid");

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

        //JOY
        currentJoyCategories = bundle.getParcelableArrayList("categoriesJoyCategories");
        userJoysprintsHelper = bundle.getParcelableArrayList("userJoysprintHelperList");

        //PASSION
        currentPassionCategories = bundle.getParcelableArrayList("categoriesPassionCategories");
        userPassionsprintsHelper = bundle.getParcelableArrayList("userPassionsprintHelperList");

        //GIVING BACK
        currentContributionCategories = bundle.getParcelableArrayList("categoriesContributionCategories");
        userContributionsprintsHelper = bundle.getParcelableArrayList("userContributionsprintHelperList");


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


        //PASSION

        //convert to format mm/dd/yyyy
        endingDateFixed_passion = userPassionSprint.endingDate.substring(0,2) + "/" +
                userPassionSprint.endingDate.substring(2,4) + "/" + userPassionSprint.endingDate.substring(4);

        //convert to format mm/dd/yyyy
        //System.out.println("heystart " + startRef);
        startingDateFixed_passion = userPassionSprint.startingDate.substring(0,2) + "/" +
                userPassionSprint.startingDate.substring(2,4) + "/" + userPassionSprint.startingDate.substring(4);


        System.out.println("sprintPassionid " + sprintPassionid);

        //GIVING BACK

        //convert to format mm/dd/yyyy
        endingDateFixed_contribution = userContributionSprint.endingDate.substring(0,2) + "/" +
                userContributionSprint.endingDate.substring(2,4) + "/" + userContributionSprint.endingDate.substring(4);

        //convert to format mm/dd/yyyy
        //System.out.println("heystart " + startRef);
        startingDateFixed_contribution = userContributionSprint.startingDate.substring(0,2) + "/" +
                userContributionSprint.startingDate.substring(2,4) + "/" + userContributionSprint.startingDate.substring(4);


        System.out.println("sprintContributionid " + sprintContributionid);


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

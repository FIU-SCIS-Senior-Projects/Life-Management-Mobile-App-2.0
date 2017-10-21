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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import java.util.*;
import android.content.Intent;


//This is dashboard activity
public class Dashboard extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    // Lazaro code for DB
    DatabaseReference databaseCategories;
    ArrayList<Category> currentJoyCategories; //joy category for user
    ActivitiesSprint currentJoyActivity;

    User currentUser; //holds the information of the current logged-in user


    static String activity1Joy;    //save data from DB for user's activity1Joy into this variable
    static String endingDateFixed;
    static String startingDateFixed;

    static String sprintJoyid;
    static String currentcategoryid;
    static TreeMap<Integer,ActivitiesSprint> activitiesJoyMap;

    boolean pass = false;

    //static
    static ActivitiesSprint activity1_static;
    static ActivitiesSprint activity2_static;
    static Category joy_currentUserCategory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
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

        activity1_static = new ActivitiesSprint();
        activity2_static = new ActivitiesSprint();
        joy_currentUserCategory = new Category();

        activitiesJoyMap = new TreeMap<>();


        //Lazaro code for DB

        Intent in = getIntent();
        String usernameRef = in.getExtras().getString("userNameY");  //has username that the user entered
        String passRef = in.getExtras().getString("passwordY");  //the password that user entered

        sprintJoyid = in.getExtras().getString("joySprintId");


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
            //System.out.println("testing firstname " + f.firstName);
            //System.out.println("testing password " + arr.get(i).password);
            //System.out.println("testing firstname " + arr.get(i).firstName);
            //System.out.println("testing username " + arr.get(i).username);
            //System.out.println("testing coach " + arr.get(i).coachFlag);
            //System.out.println("testing lastname " + arr.get(i).lastName);
            //System.out.println("testing email " + arr.get(i).email);
            //System.out.println("testing admin " + arr.get(i).adminFlag);
            //System.out.println("testing dob " + arr.get(i).dob);
            //System.out.println("testing sze " + arr.get(i).id);
        }



        ArrayList<ActivitiesSprint> activitiesJoyList = new ArrayList<>();


        activitiesJoyList = bundle.getParcelableArrayList("currentjoyactivitylist");
        int j = 0;
        for(int i=0; i<activitiesJoyList.size();i++) {

            if(activitiesJoyList.get(i).userId.equals(currentUser.id)){

                currentJoyActivity = new ActivitiesSprint(activitiesJoyList.get(i).activityScore,activitiesJoyList.get(i).actualPoints,
                        activitiesJoyList.get(i).categoryId,activitiesJoyList.get(i).activityName,activitiesJoyList.get(i).sprintDailyPoints,
                        activitiesJoyList.get(i).targetPoints,activitiesJoyList.get(i).userId,activitiesJoyList.get(i).activityid);

                        activitiesJoyMap.put(j,currentJoyActivity);
                        ++j;
            }
        }


        ArrayList<Category> totalCategoriesForUserJoy = new ArrayList<>();
        totalCategoriesForUserJoy = bundle.getParcelableArrayList("categoriesJoyList");

        activity1_static.activityScore = in.getExtras().getString("activity1_score");
        activity1_static.actualPoints = in.getExtras().getString("activity1_actualpoints");
        activity1_static.categoryId = in.getExtras().getString("activity1_categoryid");
        activity1_static.activityName = in.getExtras().getString("activity1_name");
        activity1_static.sprintDailyPoints = in.getExtras().getString("activity1_sprintdailypoints");
        activity1_static.targetPoints = in.getExtras().getString("activity1_targetpoints");
        activity1_static.userId = in.getExtras().getString("activity1_userid");
        activity1_static.activityid = in.getExtras().getString("activity1_activityid");

        activity2_static.activityScore = in.getExtras().getString("activity2_score");
        activity2_static.actualPoints = in.getExtras().getString("activity2_actualpoints");
        activity2_static.categoryId = in.getExtras().getString("activity2_categoryid");
        activity2_static.activityName = in.getExtras().getString("activity2_name");
        activity2_static.sprintDailyPoints = in.getExtras().getString("activity2_sprintdailypoints");
        activity2_static.targetPoints = in.getExtras().getString("activity2_targetpoints");
        activity2_static.userId = in.getExtras().getString("activity2_userid");
        activity2_static.activityid = in.getExtras().getString("activity2_activityid");


        joy_currentUserCategory.categoryid = in.getExtras().getString("categoryJoy_categoryid");
        joy_currentUserCategory.endingDate = in.getExtras().getString("categoryJoy_endingDate");
        joy_currentUserCategory.goal1 = in.getExtras().getString("categoryJoy_goal1");
        joy_currentUserCategory.goal2 = in.getExtras().getString("categoryJoy_goal2");
        joy_currentUserCategory.goal3 = in.getExtras().getString("categoryJoy_goal3");
        joy_currentUserCategory.goal4 = in.getExtras().getString("categoryJoy_goal4");
        joy_currentUserCategory.numberOfWeeks = in.getExtras().getString("categoryJoy_numberofweeks");
        joy_currentUserCategory.sprintActivityid1 = in.getExtras().getString("categoryJoy_sprintact1");
        joy_currentUserCategory.sprintActivityid2 = in.getExtras().getString("categoryJoy_sprintact2");
        joy_currentUserCategory.sprintOverallScore = in.getExtras().getString("categoryJoy_overallscore");
        joy_currentUserCategory.startingDate = in.getExtras().getString("categoryJoy_startingDate");
        joy_currentUserCategory.userId = in.getExtras().getString("categoryJoy_userId");


        //REMEMBER insert code here (traverse currentJoyCategories for the correct sprint, user may have more then 1 sprint)

        //convert to format mm/dd/yyyy
        endingDateFixed = joy_currentUserCategory.endingDate.substring(0,2) + "/" +
                joy_currentUserCategory.endingDate.substring(2,4) + "/" + joy_currentUserCategory.endingDate.substring(4);

        //convert to format mm/dd/yyyy
        //System.out.println("heystart " + startRef);
        startingDateFixed = joy_currentUserCategory.startingDate.substring(0,2) + "/" +
                joy_currentUserCategory.startingDate.substring(2,4) + "/" + joy_currentUserCategory.startingDate.substring(4);



        System.out.println("sprintjoyid " + sprintJoyid);




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

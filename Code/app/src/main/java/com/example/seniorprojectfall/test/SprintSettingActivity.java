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
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class SprintSettingActivity extends FragmentActivity {

    private static final int NUM_PAGES = 2; //number of pages to show in Sprint Settings
    static ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    DatabaseReference databaseUsers;

    static DatabaseReference databaseReference; // contains reference to "Categories"
    static DatabaseReference databaseRefActivities;
    static String categoriesId = "";
    static String userId = "";
    static String username = "";
    static String password = "";
    String currentCategoryuserId; //temporary variable holding the current category user id
    String currentPassionCategoryId; //temporary variable holding the current category user id
    String currentContributionCategoryId; //temporary variable holding the current category user id

    //JOY
    static String sprintJoyid = ""; // id for the newly created joy sprint
    static String activities1joyId = "";
    static String activities2joyId = "";
    static ActivitiesSprint act1Joy;
    static ActivitiesSprint act2Joy;
    static Map<String,String> arJoySprint;

    //PASSION
    static String sprintPassionid = ""; // id for the newly created passion sprint
    static Map<String,String> arPassionSprint;
    static String activities1passionId = "";
    static String activities2passionId = "";
    static ActivitiesSprint act1Passion;
    static ActivitiesSprint act2Passion;

    //CONTRIBUTION
    static String sprintContribid = ""; // id for the newly created contribution sprint
    static Map<String,String> arContrSprint;
    static String activities1contribId = "";
    static String activities2contribId = "";
    static ActivitiesSprint act1Contrib;
    static ActivitiesSprint act2Contrib;

    //to be saved into Bundle (SprintSettingPage2Fragment) to transfer to Dashboard screen to use it there
    static ArrayList<User> listUsers; //store all users from firebase database
    static ArrayList<ActivitiesSprint> allActivities;
    static ArrayList<ActivitiesSprint> userActivitiesAll;
    static ArrayList<Category> currentJoyCategories;
    static ArrayList<Category> userJoysprintsHelper;
    static ArrayList<Category> currentPassionCategories;
    static ArrayList<Category> userPassionSprintHelper;
    static ArrayList<Category> currentContributionCategories;
    static ArrayList<Category> userContributionSprintHelper;
    static ArrayList<ActivitiesSprint> activitiesjoyPrevious;
    static ArrayList<ActivitiesSprint> activitiesPassionPrevious;
    static ArrayList<ActivitiesSprint> activitiesContributionPrevious;

    static String userProfileImageName = "";
    static Map<String,String> profileImagesTotal;

    //COACHES
    static ArrayList<Coach> coachList;

    DatabaseReference databaseProfileImages;
    DatabaseReference databaseCoaches;
    static ArrayList<ActivitiesSprint> currentJoyActivities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sprint_setting);

        mPager = (ViewPager) findViewById(R.id.sprintSettingPager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        // disable swiping untill all the fields have been filled by user
        mPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        databaseUsers = FirebaseDatabase.getInstance().getReference("Users");
        databaseCoaches = FirebaseDatabase.getInstance().getReference("Coaches");

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Categories");
        databaseRefActivities = FirebaseDatabase.getInstance().getReference().child("Activities");
        databaseProfileImages = FirebaseDatabase.getInstance().getReference("ProfileImgs");
        profileImagesTotal = new TreeMap<>();

        Intent in = getIntent();
        userId = in.getStringExtra("userid");
        username = in.getStringExtra("username");
        password = in.getStringExtra("password");

        listUsers = new ArrayList<>();
        allActivities = new ArrayList<>();

        currentJoyCategories = new ArrayList<>();
        userJoysprintsHelper = new ArrayList<>();

        currentPassionCategories = new ArrayList<>();
        userPassionSprintHelper = new ArrayList<>();

        currentContributionCategories = new ArrayList<>();
        userContributionSprintHelper = new ArrayList<>();

        activitiesjoyPrevious = new ArrayList<>();
        activitiesPassionPrevious = new ArrayList<>();
        activitiesContributionPrevious = new ArrayList<>();

        //COACH
        coachList = new ArrayList<>();
        currentJoyActivities = new ArrayList<>();
    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                listUsers.clear();

                //iterating through all the nodes
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    if (postSnapshot.getValue() == null){
                        break;
                    }
                    else {

                        User users = postSnapshot.getValue(User.class);
                        //adding user to the list
                        System.out.println("user " + users.id);
                        listUsers.add(users);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        //COACH

        databaseCoaches.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //iterating through all the nodes
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    String str = postSnapshot.getValue().toString();

                    int skills_pos = str.indexOf("skills=");
                    int firstname_pos = str.indexOf("firstName=");
                    int lastname_pos = str.indexOf("lastName=");
                    int rating_pos = str.indexOf("rating=");
                    int id_pos = str.indexOf("id=");
                    int email_pos = str.indexOf("email=");

                    String temp1 = str.substring((skills_pos+7),(firstname_pos-2));
                    String temp2 = str.substring((firstname_pos+10),(lastname_pos-2));
                    String temp3 = str.substring((lastname_pos+9),(rating_pos-2));
                    String temp4 = str.substring((rating_pos+7),(id_pos-2));
                    String temp5 = str.substring((id_pos+3),(email_pos-2));
                    String temp6 =  str.substring((email_pos+6),(str.length()-1));

                    coachList.add(new Coach(temp1,temp2,temp3,temp4,temp5,temp6));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        //check if this user already has categories/sprints in Categories:
        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot categorySnapshot : dataSnapshot.getChildren()) { //id)
                    if (categorySnapshot.getValue() == null) {
                        break;
                    }
                    else{
                        String userIdTmp = categorySnapshot.child("userId").getValue().toString();
                        System.out.println("TEST LOOP" + userIdTmp);
                        if (userIdTmp.equals(userId)) {
                            //get that categorySnapshot id, so that we can add new categories there
                            categoriesId = categorySnapshot.getKey();
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot categorySnapshot : dataSnapshot.getChildren()) {

                    if (categorySnapshot.getValue() == null)
                        break;

                    String[] separator = categorySnapshot.getValue().toString().split(" ");

                    for (int i = separator.length - 1; i >= 0; i--) {

                        if (separator[i].length() > 15 && (separator[i].contains("userId"))) {
                            //get the userId
                            String temp = separator[i];
                            int g = temp.indexOf("=");
                            ++g;

                            currentCategoryuserId = temp.substring(g, temp.length() - 1);
                            DataSnapshot activitiesSnapshottemp = categorySnapshot.child("JoySprints");

                            if (activitiesSnapshottemp.getValue() == null) {
                                break;
                            }

                            String[] f = activitiesSnapshottemp.getValue().toString().split("=");
                            String temporary = f[0];
                            String tempId = temporary.substring(1);

                            for (DataSnapshot activitySnapshot2 : activitiesSnapshottemp.getChildren()) {

                                if (activitySnapshot2.getValue() == null) {
                                    break;
                                }

                                String[] tempArray = new String[(int) activitySnapshot2.getChildrenCount()];
                                int k = 0;
                                for (DataSnapshot activitySnapshot3 : activitySnapshot2.getChildren()) {

                                    if(activitySnapshot3.getValue() == null) {
                                        break;
                                    }

                                    tempArray[k] = activitySnapshot3.getValue().toString();
                                    k++;
                                }

                                String JoySprintId = activitySnapshot2.getKey();

                                //currentJoycategories will have (endingDate, goal1, goal2, goal3, goal4, NumOfWeeks, sprintActid1, sprintactid2, overallscore, startingDate) for that logged-in user
                                currentJoyCategories.add(new Category(tempArray[0], tempArray[1], tempArray[2], tempArray[3], tempArray[4], tempArray[5], tempArray[6],
                                        tempArray[7], tempArray[8], tempArray[9], tempArray[10], currentCategoryuserId,JoySprintId));

                            }
                        }
                        break;
                    }

                    //PASSION

                    for (int i = separator.length - 1; i >= 0; i--) {

                        if (separator[i].length() > 15 && (separator[i].contains("userId"))) {
                            //get the userId
                            String temp = separator[i];
                            int g = temp.indexOf("=");
                            ++g;

                            currentPassionCategoryId = temp.substring(g, temp.length() - 1);
                            DataSnapshot activitiesSnapshottemp = categorySnapshot.child("PassionSprints");

                            if(activitiesSnapshottemp.getValue() == null) {
                                break;
                            }

                            String[] f = activitiesSnapshottemp.getValue().toString().split("=");
                            String temporary = f[0];
                            String tempId = temporary.substring(1);

                            for (DataSnapshot activitySnapshot2 : activitiesSnapshottemp.getChildren()) {
                                if (activitySnapshot2.getValue() == null) {
                                    break;
                                }

                                String[] tempArray = new String[(int) activitySnapshot2.getChildrenCount()];
                                int k = 0;
                                for (DataSnapshot activitySnapshot3 : activitySnapshot2.getChildren()) {
                                    if(activitySnapshot3.getValue() == null) {
                                        break;
                                    }

                                    tempArray[k] = activitySnapshot3.getValue().toString();
                                    k++;
                                }

                                String passionSprintid = activitySnapshot2.getKey();

                                //currentJoycategories will have (endingDate, goal1, goal2, goal3, goal4, NumOfWeeks, sprintActid1, sprintactid2, overallscore, startingDate) for that logged-in user
                                currentPassionCategories.add(new Category(tempArray[0], tempArray[1], tempArray[2], tempArray[3], tempArray[4], tempArray[5], tempArray[6],
                                        tempArray[7], tempArray[8], tempArray[9], tempArray[10], currentPassionCategoryId,passionSprintid));
                            }
                        }
                        break;
                    }

                    // GIVING BACK

                    for (int i = separator.length - 1; i >= 0; i--) {

                        if (separator[i].length() > 15 && (separator[i].contains("userId"))) {
                            //get the userId
                            String temp = separator[i];
                            int g = temp.indexOf("=");
                            ++g;

                            currentContributionCategoryId = temp.substring(g, temp.length() - 1);

                            DataSnapshot activitiesSnapshottemp = categorySnapshot.child("ContributionSprints");
                            if (activitiesSnapshottemp.getValue() == null) {
                                break;
                            }

                            String[] f = activitiesSnapshottemp.getValue().toString().split("=");

                            String temporary = f[0];
                            String tempId = temporary.substring(1);  //joysprint unique id

                            for (DataSnapshot activitySnapshot2 : activitiesSnapshottemp.getChildren()) {
                                if (activitySnapshot2.getValue() == null) {
                                    break;
                                }

                                String[] tempArray = new String[(int) activitySnapshot2.getChildrenCount()];
                                int k = 0;
                                for (DataSnapshot activitySnapshot3 : activitySnapshot2.getChildren()) {
                                    if (activitySnapshot3.getValue() == null) {
                                        break;
                                    }

                                    tempArray[k] = activitySnapshot3.getValue().toString();
                                    k++;
                                }

                                String contributionSprintid = activitySnapshot2.getKey();

                                //currentJoycategories will have (endingDate, goal1, goal2, goal3, goal4, NumOfWeeks, sprintActid1, sprintactid2, overallscore, startingDate) for that logged-in user
                                currentContributionCategories.add(new Category(tempArray[0], tempArray[1], tempArray[2], tempArray[3], tempArray[4], tempArray[5], tempArray[6],
                                        tempArray[7], tempArray[8], tempArray[9], tempArray[10], currentContributionCategoryId,contributionSprintid));
                            }
                        }
                        break;
                    }
                } //end of for
            } //end of onDataChange() method

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        databaseProfileImages.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot activitySnapshot : dataSnapshot.getChildren()) {
                    int temp = activitySnapshot.getValue().toString().length();
                    profileImagesTotal.put(activitySnapshot.getKey(),activitySnapshot.getValue().toString().substring(6,temp-1));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        //retrieve all activities
        databaseRefActivities.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot activitySnapshot : dataSnapshot.getChildren()) {
                    if (activitySnapshot.getValue() == null) {
                        break;
                    } else{

                        String activityId = activitySnapshot.getKey();
                        String temp[] = new String[(int) activitySnapshot.getChildrenCount()];
                        int i = 0;
                        for (DataSnapshot activitySnapshot2 : activitySnapshot.getChildren()) {

                            temp[i] = activitySnapshot2.getValue().toString();
                            ++i;
                        }
                        allActivities.add(new ActivitiesSprint(temp[0], temp[1], temp[2], temp[3], temp[4], temp[5], temp[6], activityId));
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    public static void saveSettings(){

        String sprintPeriod = SprintSettingPageFragment.sprintPeriod;
        String sprintDailyPoints = "";
        if(sprintPeriod.equals("1")){
            sprintDailyPoints = "0000000";
        }
        else if(sprintPeriod.equals("2")){
            sprintDailyPoints = "00000000000000";
        }
        else{
            sprintDailyPoints = "000000000000000000000";
        }

        //generating unique keys for Categories  (if there does not exist categories object with this user yet in Categories)
        if(categoriesId.equals("")) {
            categoriesId = databaseReference.push().getKey();
        }

        // write to db 2 new objects for Joy activities:
        //generating unique keys for Joy activity 1
        activities1joyId = databaseRefActivities.push().getKey();
        databaseRefActivities.child(activities1joyId).setValue(null);

        String act1JoyTargetPoints = SprintSettingPage2Fragment.joyAct1goal.getText().toString();
        String act1JoyName = CoverFlowAdapter.n.get(0);

        //set value
        act1Joy = new ActivitiesSprint("0","0",categoriesId,act1JoyName,sprintDailyPoints,act1JoyTargetPoints,userId);
        databaseRefActivities.child(activities1joyId).setValue(act1Joy);
        currentJoyActivities.add(new ActivitiesSprint(act1Joy.getActivityScore(),act1Joy.getActualPoints(),act1Joy.getCategoryId(),act1Joy.getName(),act1Joy.getSprintDailyPoints(),act1Joy.getTargetPoints(),act1Joy.getUserId(),activities1joyId));

        //generating unique keys for Joy activity 2
        activities2joyId = databaseRefActivities.push().getKey();
        databaseRefActivities.child(activities2joyId).setValue(null);

        String act2JoyTargetPoints = SprintSettingPage2Fragment.joyAct2goal.getText().toString();
        String act2JoyName = CoverFlowAdapter.n.get(1);
        act2Joy = new ActivitiesSprint("0","0",categoriesId,act2JoyName,sprintDailyPoints,act2JoyTargetPoints,userId);
        databaseRefActivities.child(activities2joyId).setValue(act2Joy);
        currentJoyActivities.add(new ActivitiesSprint(act2Joy.getActivityScore(),act2Joy.getActualPoints(),act2Joy.getCategoryId(),act2Joy.getName(),act2Joy.getSprintDailyPoints(),act2Joy.getTargetPoints(),act2Joy.getUserId(),activities2joyId));


        // write to db 2 new objects for Passion activities:

        //generating unique keys for Passion activity 1
        activities1passionId = databaseRefActivities.push().getKey();
        databaseRefActivities.child(activities1passionId).setValue(null);

        String act1PassionTargetPoints = SprintSettingPage2Fragment.passionAct1goal.getText().toString();
        String act1PassionName = CoverFlowAdapterPassion.n.get(0);

        //set value
        act1Passion = new ActivitiesSprint("0","0",categoriesId,act1PassionName,sprintDailyPoints,act1PassionTargetPoints,userId);
        databaseRefActivities.child(activities1passionId).setValue(act1Passion);

        //generating unique keys for Passion activity 2
        activities2passionId = databaseRefActivities.push().getKey();
        databaseRefActivities.child(activities2passionId).setValue(null);

        String act2PassionTargetPoints = SprintSettingPage2Fragment.passionAct2goal.getText().toString();
        String act2PassionName = CoverFlowAdapterPassion.n.get(1);

        act2Passion = new ActivitiesSprint("0","0",categoriesId,act2PassionName,sprintDailyPoints,act2PassionTargetPoints,userId);
        databaseRefActivities.child(activities2passionId).setValue(act2Passion);

        // write to db 2 new objects for Contribution activities:

        //generating unique keys for Contribution activity 1
        activities1contribId = databaseRefActivities.push().getKey();
        databaseRefActivities.child(activities1contribId).setValue(null);

        String act1ContribTargetPoints = SprintSettingPage2Fragment.contribAct1goal.getText().toString();
        String act1ContribName = ActGivingbackAdapter.givBackActSelected.get(0);

        //set value
        act1Contrib = new ActivitiesSprint("0","0",categoriesId,act1ContribName,sprintDailyPoints,act1ContribTargetPoints,userId);
        databaseRefActivities.child(activities1contribId).setValue(act1Contrib);

        //generating unique keys for Contribution activity 2
        activities2contribId = databaseRefActivities.push().getKey();
        databaseRefActivities.child(activities2contribId).setValue(null);

        String act2ContribTargetPoints = SprintSettingPage2Fragment.contribAct2goal.getText().toString();
        String act2ContribName = ActGivingbackAdapter.givBackActSelected.get(1);

        //set value
        act2Contrib = new ActivitiesSprint("0","0",categoriesId,act2ContribName,sprintDailyPoints,act2ContribTargetPoints,userId);
        databaseRefActivities.child(activities2contribId).setValue(act2Contrib);

        // set value to children one by one
        databaseReference.child(categoriesId).child("userId").setValue(userId);

        String startingDate = SprintSettingPageFragment.sprintStartDate.getText().toString();
        startingDate = startingDate.substring(0,2)+ startingDate.substring(3,5) + startingDate.substring(6);

        String endingDate = SprintSettingPageFragment.sprintEndDate.getText().toString();
        endingDate = endingDate.substring(0,2)+ endingDate.substring(3,5) + endingDate.substring(6);

        arContrSprint = new HashMap<>();
        arContrSprint.put("categoryId",categoriesId);
        arContrSprint.put("endingDate", endingDate);
        arContrSprint.put("goal1","");
        arContrSprint.put("goal2","");
        arContrSprint.put("goal3","");
        arContrSprint.put("goal4","");
        arContrSprint.put("numberOfWeeks",sprintPeriod);
        arContrSprint.put("sprintActivityId1",activities1contribId);
        arContrSprint.put("sprintActivityId2",activities2contribId);
        arContrSprint.put("sprintOverallScore","0");
        arContrSprint.put("startingDate", startingDate);
        arContrSprint.put("userId",userId);

        sprintContribid = databaseReference.child(categoriesId).child("ContributionSprints").push().getKey();
        databaseReference.child(categoriesId).child("ContributionSprints").child(sprintContribid).setValue(arContrSprint);

        arJoySprint = new HashMap<>();
        arJoySprint.put("categoryId",categoriesId);
        arJoySprint.put("endingDate", endingDate);
        arJoySprint.put("goal1","");
        arJoySprint.put("goal2","");
        arJoySprint.put("goal3","");
        arJoySprint.put("goal4","");
        arJoySprint.put("numberOfWeeks",sprintPeriod);
        arJoySprint.put("sprintActivityId1",activities1joyId);
        arJoySprint.put("sprintActivityId2",activities2joyId);
        arJoySprint.put("sprintOverallScore","0");
        arJoySprint.put("startingDate", startingDate);
        arJoySprint.put("userId",userId);

        sprintJoyid = databaseReference.child(categoriesId).child("JoySprints").push().getKey();
        databaseReference.child(categoriesId).child("JoySprints").child(sprintJoyid).setValue(arJoySprint);

        arPassionSprint = new HashMap<>();
        arPassionSprint.put("categoryId",categoriesId);
        arPassionSprint.put("endingDate", endingDate);
        arPassionSprint.put("goal1","");
        arPassionSprint.put("goal2","");
        arPassionSprint.put("goal3","");
        arPassionSprint.put("goal4","");
        arPassionSprint.put("numberOfWeeks",sprintPeriod);
        arPassionSprint.put("sprintActivityId1",activities1passionId);
        arPassionSprint.put("sprintActivityId2",activities2passionId);
        arPassionSprint.put("sprintOverallScore","0");
        arPassionSprint.put("startingDate", startingDate);
        arPassionSprint.put("userId",userId);

        sprintPassionid = databaseReference.child(categoriesId).child("PassionSprints").push().getKey();
        databaseReference.child(categoriesId).child("PassionSprints").child(sprintPassionid).setValue(arPassionSprint);

        for(Map.Entry g: profileImagesTotal.entrySet()){

            if(g.getKey().toString().contains(userId)){
                userProfileImageName = g.getValue().toString();
            }
        }

        userActivitiesAll = new ArrayList<>();
        for (int i = 0; i < allActivities.size(); i++) {


            if (allActivities.get(i).userId.contains(userId)) {
                //get all the activities for that user

                userActivitiesAll.add(new ActivitiesSprint(allActivities.get(i).activityScore,
                        allActivities.get(i).actualPoints, allActivities.get(i).categoryId,
                        allActivities.get(i).name, allActivities.get(i).sprintDailyPoints,
                        allActivities.get(i).targetPoints, allActivities.get(i).userId, allActivities.get(i).activityid));
            }
        }

        List<String> tempList = new ArrayList<>(); //list containing all the sprintActivities of the user
        //JOY
        for (int i = 0; i < currentJoyCategories.size(); i++) {

            if (userId.contains(currentJoyCategories.get(i).userId)) {

                System.out.println("categoryid:  " + currentJoyCategories.get(i).categoryid + " -- " + currentJoyCategories.size());

                tempList.add(currentJoyCategories.get(i).sprintActivityid1 + " " + currentJoyCategories.get(i).startingDate + " " + currentJoyCategories.get(i).endingDate);
                tempList.add(currentJoyCategories.get(i).sprintActivityid2 + " " + currentJoyCategories.get(i).startingDate + " " + currentJoyCategories.get(i).endingDate);

                userJoysprintsHelper.add(new Category(currentJoyCategories.get(i).categoryid, currentJoyCategories.get(i).endingDate,
                        currentJoyCategories.get(i).goal1, currentJoyCategories.get(i).goal2, currentJoyCategories.get(i).goal3,
                        currentJoyCategories.get(i).goal4, currentJoyCategories.get(i).numberOfWeeks, currentJoyCategories.get(i).sprintActivityid1,
                        currentJoyCategories.get(i).sprintActivityid2, currentJoyCategories.get(i).sprintOverallScore,
                        currentJoyCategories.get(i).startingDate, currentJoyCategories.get(i).userId,currentJoyCategories.get(i).sprintid));
            }
        }

        //JOY

        for (int i = 0; i < tempList.size(); i++) {

            for (int k = 0; k < userActivitiesAll.size(); k++) {

                String [] splitter = tempList.get(i).split(" ");

                if (userActivitiesAll.get(k).activityid.contains(splitter[0])){

                    activitiesjoyPrevious.add(new ActivitiesSprint(userActivitiesAll.get(k).activityScore,userActivitiesAll.get(k).actualPoints,
                            splitter[1], userActivitiesAll.get(k).name, userActivitiesAll.get(k).sprintDailyPoints,
                            userActivitiesAll.get(k).targetPoints, splitter[2], userActivitiesAll.get(k).activityid));
                    break;
                }
            }
        } //end of for

        //PASSION

        List<String> tempListPassion = new ArrayList<>(); //list containing all the sprintActivities of the user
        for (int i = 0; i < currentPassionCategories.size(); i++) {

            if (userId.contains(currentPassionCategories.get(i).userId)) {

                tempListPassion.add(currentPassionCategories.get(i).sprintActivityid1 + " " + currentPassionCategories.get(i).startingDate + " " + currentPassionCategories.get(i).endingDate);
                tempListPassion.add(currentPassionCategories.get(i).sprintActivityid2 + " " + currentPassionCategories.get(i).startingDate + " " + currentPassionCategories.get(i).endingDate);

                userPassionSprintHelper.add(new Category(currentPassionCategories.get(i).categoryid, currentPassionCategories.get(i).endingDate,
                        currentPassionCategories.get(i).goal1, currentPassionCategories.get(i).goal2, currentPassionCategories.get(i).goal3,
                        currentPassionCategories.get(i).goal4, currentPassionCategories.get(i).numberOfWeeks, currentPassionCategories.get(i).sprintActivityid1,
                        currentPassionCategories.get(i).sprintActivityid2, currentPassionCategories.get(i).sprintOverallScore,
                        currentPassionCategories.get(i).startingDate, currentPassionCategories.get(i).userId,currentPassionCategories.get(i).sprintid));
            }
        }

        for (int i = 0; i < tempListPassion.size(); i++) {

            for (int k = 0; k < userActivitiesAll.size(); k++) {

                String [] splitter = tempListPassion.get(i).split(" ");

                if (userActivitiesAll.get(k).activityid.contains(splitter[0])){

                    activitiesPassionPrevious.add(new ActivitiesSprint(userActivitiesAll.get(k).activityScore,userActivitiesAll.get(k).actualPoints,
                            splitter[1], userActivitiesAll.get(k).name, userActivitiesAll.get(k).sprintDailyPoints,
                            userActivitiesAll.get(k).targetPoints, splitter[2], userActivitiesAll.get(k).activityid));
                    break;
                }
            }
        } //end of for

        //GIVING BACK

        List<String> tempListContribution = new ArrayList<>(); //list containing all the sprintActivities of the user
        for (int i = 0; i < currentContributionCategories.size(); i++) {


            if (userId.contains(currentContributionCategories.get(i).userId)) {

                tempListContribution.add(currentContributionCategories.get(i).sprintActivityid1 + " " + currentContributionCategories.get(i).startingDate + " " + currentContributionCategories.get(i).endingDate);
                tempListContribution.add(currentContributionCategories.get(i).sprintActivityid2 + " " + currentContributionCategories.get(i).startingDate + " " + currentContributionCategories.get(i).endingDate);

                userContributionSprintHelper.add(new Category(currentContributionCategories.get(i).categoryid, currentContributionCategories.get(i).endingDate,
                        currentContributionCategories.get(i).goal1, currentContributionCategories.get(i).goal2, currentContributionCategories.get(i).goal3,
                        currentContributionCategories.get(i).goal4, currentContributionCategories.get(i).numberOfWeeks, currentContributionCategories.get(i).sprintActivityid1,
                        currentContributionCategories.get(i).sprintActivityid2, currentContributionCategories.get(i).sprintOverallScore,
                        currentContributionCategories.get(i).startingDate, currentContributionCategories.get(i).userId,currentContributionCategories.get(i).sprintid));
            }
        }

        for (int i = 0; i < tempListContribution.size(); i++) {

            for (int k = 0; k < userActivitiesAll.size(); k++) {

                String [] splitter = tempListContribution.get(i).split(" ");

                if (userActivitiesAll.get(k).activityid.contains(splitter[0])){

                    activitiesContributionPrevious.add(new ActivitiesSprint(userActivitiesAll.get(k).activityScore,userActivitiesAll.get(k).actualPoints,
                            splitter[1], userActivitiesAll.get(k).name, userActivitiesAll.get(k).sprintDailyPoints,
                            userActivitiesAll.get(k).targetPoints, splitter[2], userActivitiesAll.get(k).activityid));
                    break;
                }
            }
        } //end of for
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

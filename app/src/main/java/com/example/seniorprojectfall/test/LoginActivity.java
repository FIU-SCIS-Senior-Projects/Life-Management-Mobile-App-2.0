package com.example.seniorprojectfall.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.*;

//import in.goodiebag.carouselpicker.CarouselPicker;

public class LoginActivity extends AppCompatActivity {


    EditText username,password;
    Button signIn, registerUser;

    //JOY variables
    ArrayList<Category> userJoysprintsHelper;
    ArrayList<User> listUsers;
    ArrayList<Category> currentJoyCategories;
    ArrayList<ActivitiesSprint> allActivities;
    String userId;
    Map<String,String> joyIdStartingDateMap;
    String sprintjoyid;
    String currentCategoryuserId; //temporary variable holding the current category user id

    //PASSION varibles

    ArrayList<Category> passionprintsForUserHelper;
    ArrayList<Category> currentPassionCategories;
    ArrayList<ActivitiesSprint> currentPassionActivities;
    Map<String,String> currentPassionSprintidtotal;
    String sprintpassionid;


    DatabaseReference databaseCategories;
    DatabaseReference databaseUsers; //our database reference object
    DatabaseReference databaseActivities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //JOY INITIALIZATIONs
        userJoysprintsHelper = new ArrayList<>();
        joyIdStartingDateMap = new TreeMap<>();
        allActivities = new ArrayList<>();
        listUsers = new ArrayList<>();
        currentJoyCategories = new ArrayList<>();

        //PASSION INITIALIZATIONS
        passionprintsForUserHelper = new ArrayList<>();
        currentPassionSprintidtotal = new TreeMap<>();
        currentPassionActivities = new ArrayList<>();
        currentPassionCategories = new ArrayList<>();


        //getting the reference of artists node
        databaseUsers = FirebaseDatabase.getInstance().getReference("Users");
        databaseActivities = FirebaseDatabase.getInstance().getReference("Activities");
        databaseCategories = FirebaseDatabase.getInstance().getReference("Categories");

        username = (EditText) findViewById(R.id.usernameLogin);
        password = (EditText) findViewById(R.id.passwordLogin);

        signIn = (Button) findViewById(R.id.buttonSignInLogin);
        registerUser = (Button) findViewById(R.id.buttonRegisterUserLogin);

        //adding an onclicklistener to button
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //the method is defined below
                //this method is actually performing the write operation
                loginUser();
            }
        });


        registerUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this,CreateUserActivity.class);
                startActivity(i);
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        //attaching value event listener
        databaseUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //clearing the previous artist list
                listUsers.clear();

                //iterating through all the nodes
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting artist

                    User users = postSnapshot.getValue(User.class);
                    //adding artist to the list
                    System.out.println("user " + users.id);
                    listUsers.add(users);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        databaseCategories.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for (DataSnapshot categorySnapshot : dataSnapshot.getChildren()) { //id


                    System.out.println("idddd " + categorySnapshot.getKey());
                    System.out.println("iddddvalue " + categorySnapshot.getValue());

                    //System.out.println("hey11 " + categorySnapshot.getValue(String.class));
                    String[] separator = categorySnapshot.getValue().toString().split(" ");
                    System.out.println("separator length " + dataSnapshot.getChildrenCount());

                    for (int i = separator.length - 1; i >= 0; i--) {

                        if (separator[i].length() > 15 && (separator[i].contains("userId"))) {
                            //get the userId
                            String temp = separator[i];
                            int g = temp.indexOf("=");
                            ++g;

                            currentCategoryuserId = temp.substring(g, temp.length() - 1);
                            System.out.println("ZZZFG THIS IS CURRENTCategoryid " + currentCategoryuserId); //hsshs

                            DataSnapshot activitiesSnapshottemp = categorySnapshot.child("JoySprints");

                            String[] f = activitiesSnapshottemp.getValue().toString().split("=");

                            String temporary = f[0];
                            //System.out.println("heyyy66 " + f[0]);
                            String tempId = temporary.substring(1);  //joysprint unique id


                            for (DataSnapshot activitySnapshot2 : activitiesSnapshottemp.getChildren()) { //ids

                                System.out.println("posible joysprinIDDS " + activitySnapshot2.getKey());

                                String[] tempArray = new String[(int) activitySnapshot2.getChildrenCount()];
                                int k = 0;
                                for (DataSnapshot activitySnapshot3 : activitySnapshot2.getChildren()) { //branch


                                    tempArray[k] = activitySnapshot3.getValue().toString();

                                    System.out.println("heyy temparray[] " + k + " === " + tempArray[k]);
                                    k++;

                                }

                                System.out.println("activitySnapshot2.getKey() " + activitySnapshot2.getKey());
                                System.out.println("temparray[10] " + tempArray[10]);

                                joyIdStartingDateMap.put(activitySnapshot2.getKey() + "", tempArray[10]); //storing the joy sprint id and its starting date

                                //currentJoycategories will have (endingDate, goal1, goal2, goal3, goal4, NumOfWeeks, sprintActid1, sprintactid2, overallscore, startingDate) for that logged-in user
                                currentJoyCategories.add(new Category(tempArray[0], tempArray[1], tempArray[2], tempArray[3], tempArray[4], tempArray[5], tempArray[6],
                                        tempArray[7], tempArray[8], tempArray[9], tempArray[10], currentCategoryuserId));
                                //System.out.println("hey3 " + activitySnapshot3.getKey()+": "+ activitySnapshot3.getValue(String.class));
                                //returning = tempArray[5];


                            }

                        }
                        break;

                    }

            }

                System.out.println("ss22  ");


                for (Map.Entry<String, String> entr : joyIdStartingDateMap.entrySet()) {
                    System.out.println("Keyw : " + entr.getKey() + " Valuew : " + entr.getValue());

                }

                for (int i = 0; i < currentJoyCategories.size(); i++) {

                    System.out.println("ss22 categ " + currentJoyCategories.get(i).categoryid);
                    System.out.println("ss22 ending " + currentJoyCategories.get(i).endingDate);
                    System.out.println("ss22 gol1 " + currentJoyCategories.get(i).goal1);
                    System.out.println("ss22 gol2 " + currentJoyCategories.get(i).goal2);
                    System.out.println("ss22 gol3 " + currentJoyCategories.get(i).goal3);
                    System.out.println("ss22 gol4 " + currentJoyCategories.get(i).goal4);
                    System.out.println("ss22 numwee " + currentJoyCategories.get(i).numberOfWeeks);
                    System.out.println("ss22 sprintid1 " + currentJoyCategories.get(i).sprintActivityid1);
                    System.out.println("ss22 sprintid2 " + currentJoyCategories.get(i).sprintActivityid2);
                    System.out.println("ss22 overallscoe " + currentJoyCategories.get(i).sprintOverallScore);
                    System.out.println("ss22 starting " + currentJoyCategories.get(i).startingDate);
                    System.out.println("ss22 starting " + currentJoyCategories.get(i).userId);
                }

            } //end of datachangeMethod

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        //retrieve activities for JOY (for that user)
        databaseActivities.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {



                for (DataSnapshot activitySnapshot : dataSnapshot.getChildren()) { //id

                    //System.out.println("actifgggg " + activitySnapshot.getKey());
                    String activityId = activitySnapshot.getKey();
                    String temp[] = new String[(int) activitySnapshot.getChildrenCount()];
                    int i = 0;
                    for (DataSnapshot activitySnapshot2 : activitySnapshot.getChildren()) { //ids

                        //System.out.println("actif " + activitySnapshot2.getValue());

                        temp[i] = activitySnapshot2.getValue().toString();
                        ++i;

                    }

                    System.out.println("CURRENT ACTIVITIES  " + temp[0] + " == " + temp[1]);
                    allActivities.add(new ActivitiesSprint(temp[0],temp[1],temp[2],temp[3],temp[4],temp[5],temp[6],activityId));

                }



                for(int i=0;i<allActivities.size();i++){

                    System.out.println("CURRENT ACTIVITIES 0 " + allActivities.get(i).activityScore);
                    System.out.println("CURRENT ACTIVITIES 1 " + allActivities.get(i).actualPoints);
                    System.out.println("CURRENT ACTIVITIES 2 " + allActivities.get(i).categoryId);
                    System.out.println("CURRENT ACTIVITIES 3 " + allActivities.get(i).activityName);
                    System.out.println("CURRENT ACTIVITIES 4 " + allActivities.get(i).sprintDailyPoints);
                    System.out.println("CURRENT ACTIVITIES 5 " + allActivities.get(i).targetPoints);
                    System.out.println("CURRENT ACTIVITIES 6 " + allActivities.get(i).userId);
                    System.out.println("CURRENT ACTIVITIES 7 " + allActivities.get(i).activityid);

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    } //end of startmethod


    private void loginUser() {
        //getting the values to save
        String name = username.getText().toString().trim();
        String pass = password.getText().toString().trim();

        //System.out.println("THIS " + name + " / " + pass);
        //System.out.println("THIS2 " + listUsers.size() + " / " + listUsers.get(0).getUsername());

        boolean isValid = false;
        for(User f: listUsers){

            if(f.getUsername().equals(name) && f.getPassword().equals(pass)){
                //then is an existing user, so login

                Toast.makeText(this, "Logged In ", Toast.LENGTH_LONG).show();
                userId = f.id;
                isValid = true;

            }else{
                System.out.println("VEt " + f.getUsername() + " / " + f.getPassword());
            }
        } //end of for

        if(isValid) {


            ArrayList<ActivitiesSprint> userActivitiesAll = new ArrayList<>();


            for (int i = 0; i < allActivities.size(); i++) {


                if (allActivities.get(i).userId.contains(userId)) {
                    //get all the activities for that user

                    userActivitiesAll.add(new ActivitiesSprint(allActivities.get(i).activityScore,
                            allActivities.get(i).actualPoints, allActivities.get(i).categoryId,
                            allActivities.get(i).activityName, allActivities.get(i).sprintDailyPoints,
                            allActivities.get(i).targetPoints, allActivities.get(i).userId, allActivities.get(i).activityid));
                }

            }


            for (int i = 0; i < userActivitiesAll.size(); i++) {
                System.out.println("useractivities " + userActivitiesAll.get(i).activityScore);
                System.out.println("useractivities 2 " + userActivitiesAll.get(i).actualPoints);
                System.out.println("useractivities 3 " + userActivitiesAll.get(i).categoryId);
                System.out.println("useractivities 4 " + userActivitiesAll.get(i).activityName);
                System.out.println("useractivities 5 " + userActivitiesAll.get(i).sprintDailyPoints);
                System.out.println("useractivities 6 " + userActivitiesAll.get(i).targetPoints);
                System.out.println("useractivities 7 " + userActivitiesAll.get(i).userId);
                System.out.println("useractivities 8 " + userActivitiesAll.get(i).activityid);
            }


            for (int i = 0; i < currentJoyCategories.size(); i++) {


                if (userId.contains(currentJoyCategories.get(i).userId)) {

                    System.out.println("categoryid:  " + currentJoyCategories.get(i).categoryid + " -- " + currentJoyCategories.size());

                    userJoysprintsHelper.add(new Category(currentJoyCategories.get(i).categoryid, currentJoyCategories.get(i).endingDate,
                            currentJoyCategories.get(i).goal1, currentJoyCategories.get(i).goal2, currentJoyCategories.get(i).goal3,
                            currentJoyCategories.get(i).goal4, currentJoyCategories.get(i).numberOfWeeks, currentJoyCategories.get(i).sprintActivityid1,
                            currentJoyCategories.get(i).sprintActivityid2, currentJoyCategories.get(i).sprintOverallScore,
                            currentJoyCategories.get(i).startingDate, currentJoyCategories.get(i).userId));
                }

            }


            int[] firstDateidentifier = new int[userJoysprintsHelper.size()];
            for (int i = 0; i < userJoysprintsHelper.size(); i++) {

                firstDateidentifier[i] = Integer.parseInt(userJoysprintsHelper.get(i).startingDate);

                System.out.println("firstdateidentifier[i]  " + firstDateidentifier[i]);

                System.out.println("ss3 categ " + userJoysprintsHelper.get(i).categoryid);
                System.out.println("ss3 ending " + userJoysprintsHelper.get(i).endingDate);
                System.out.println("ss3 gol1 " + userJoysprintsHelper.get(i).goal1);
                System.out.println("ss3 gol2 " + userJoysprintsHelper.get(i).goal2);
                System.out.println("ss3 gol3 " + userJoysprintsHelper.get(i).goal3);
                System.out.println("ss3 gol4 " + userJoysprintsHelper.get(i).goal4);
                System.out.println("ss3 numwee " + userJoysprintsHelper.get(i).numberOfWeeks);
                System.out.println("ss3 sprintid1 " + userJoysprintsHelper.get(i).sprintActivityid1);
                System.out.println("ss3 sprintid2 " + userJoysprintsHelper.get(i).sprintActivityid2);
                System.out.println("ss3 overallscoe " + userJoysprintsHelper.get(i).sprintOverallScore);
                System.out.println("ss3 starting " + userJoysprintsHelper.get(i).startingDate);
                System.out.println("ss3 starting " + userJoysprintsHelper.get(i).userId);

            }

            //sort it by starting date
            Arrays.sort(firstDateidentifier);


            String startingdateid1 = "";

            String temp = firstDateidentifier[0] + "";

            System.out.println("temp fella " + temp);


            if (temp.length() != 8) {
                startingdateid1 = "0" + firstDateidentifier[0];
            } else {
                startingdateid1 = "" + firstDateidentifier[0];
            }


            System.out.println("startingdate fella " + startingdateid1);

            Category userJoySprint = new Category();
            for (int i = 0; i < userJoysprintsHelper.size(); i++) {

                if (userJoysprintsHelper.get(i).startingDate.contains(startingdateid1)) {


                    userJoySprint = new Category(userJoysprintsHelper.get(i).categoryid, userJoysprintsHelper.get(i).endingDate,
                            userJoysprintsHelper.get(i).goal1, userJoysprintsHelper.get(i).goal2, userJoysprintsHelper.get(i).goal3,
                            userJoysprintsHelper.get(i).goal4, userJoysprintsHelper.get(i).numberOfWeeks, userJoysprintsHelper.get(i).sprintActivityid1,
                            userJoysprintsHelper.get(i).sprintActivityid2, userJoysprintsHelper.get(i).sprintOverallScore,
                            userJoysprintsHelper.get(i).startingDate, userJoysprintsHelper.get(i).userId);
                }
            }

            System.out.println("ss5  " + userJoySprint.categoryid);
            System.out.println("ss5 " + userJoySprint.endingDate);
            System.out.println("ss5 goal1 " + userJoySprint.goal1);
            System.out.println("ss5 goal2 " + userJoySprint.goal2);
            System.out.println("ss5 goal3 " + userJoySprint.goal3);
            System.out.println("ss5 goal4 " + userJoySprint.goal4);
            System.out.println("ss5 " + userJoySprint.numberOfWeeks);
            System.out.println("ss5 sprintid1 " + userJoySprint.sprintActivityid1);
            System.out.println("ss5 sprintid2 " + userJoySprint.sprintActivityid2);
            System.out.println("ss5 overallscoe " + userJoySprint.sprintOverallScore);
            System.out.println("ss5 starting " + userJoySprint.startingDate);
            System.out.println("ss5 userid " + userJoySprint.userId);

            ActivitiesSprint userActivityJoyid1 = new ActivitiesSprint();
            ActivitiesSprint userActivityJoyid2 = new ActivitiesSprint();

            for (int i = 0; i < userActivitiesAll.size(); i++) {


                if (userActivitiesAll.get(i).activityid.contains(userJoySprint.sprintActivityid1)) {
                    userActivityJoyid1 = new ActivitiesSprint(userActivitiesAll.get(i).activityScore,
                            userActivitiesAll.get(i).actualPoints, userActivitiesAll.get(i).categoryId,
                            userActivitiesAll.get(i).activityName, userActivitiesAll.get(i).sprintDailyPoints,
                            userActivitiesAll.get(i).targetPoints, userActivitiesAll.get(i).userId, userActivitiesAll.get(i).activityid);
                }

                if (userActivitiesAll.get(i).activityid.contains(userJoySprint.sprintActivityid2)) {

                    userActivityJoyid2 = new ActivitiesSprint(userActivitiesAll.get(i).activityScore,
                            userActivitiesAll.get(i).actualPoints, userActivitiesAll.get(i).categoryId,
                            userActivitiesAll.get(i).activityName, userActivitiesAll.get(i).sprintDailyPoints,
                            userActivitiesAll.get(i).targetPoints, userActivitiesAll.get(i).userId, userActivitiesAll.get(i).activityid);

                }
            }


            System.out.println("ss8 score " + userActivityJoyid1.activityScore);
            System.out.println("ss8 points " + userActivityJoyid1.actualPoints);
            System.out.println("ss8 catid " + userActivityJoyid1.categoryId);
            System.out.println("ss8 actname" + userActivityJoyid1.activityName);
            System.out.println("ss8 dailypoints " + userActivityJoyid1.sprintDailyPoints);
            System.out.println("ss8 target" + userActivityJoyid1.targetPoints);
            System.out.println("ss8 userid " + userActivityJoyid1.userId);
            System.out.println("ss8 actid " + userActivityJoyid1.activityid);


            System.out.println("ss9 score " + userActivityJoyid2.activityScore);
            System.out.println("ss9 points " + userActivityJoyid2.actualPoints);
            System.out.println("ss9 catid " + userActivityJoyid2.categoryId);
            System.out.println("ss9 actname" + userActivityJoyid2.activityName);
            System.out.println("ss9 dailypoints " + userActivityJoyid2.sprintDailyPoints);
            System.out.println("ss9 target" + userActivityJoyid2.targetPoints);
            System.out.println("ss9 userid " + userActivityJoyid2.userId);
            System.out.println("ss9 actid " + userActivityJoyid2.activityid);



            for(Map.Entry<String,String> entr: joyIdStartingDateMap.entrySet()){
                System.out.println("Keyw : " + entr.getKey() + " Valuew : " + entr.getValue());

                if(userJoySprint.startingDate.contains(entr.getValue())){
                    sprintjoyid = entr.getKey();
                    break;
                }
            }

            System.out.println("sprintjoyid fella " + sprintjoyid);


            Intent i = new Intent(LoginActivity.this,Dashboard.class);


            //saving all data do we can use it in the next screen
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("mylist", listUsers);
            bundle.putParcelableArrayList("allActivities", allActivities);
            bundle.putParcelableArrayList("userActivitiesAllList",userActivitiesAll);
            bundle.putParcelableArrayList("categoriesJoyCategories",currentJoyCategories);
            bundle.putParcelableArrayList("userJoysprintHelperList",userJoysprintsHelper);


            //missing joyIdStartingDateMap for now


            i.putExtras(bundle);
            i.putExtra("userNameY",name);
            i.putExtra("passwordY",pass);

            //JOY
            i.putExtra("joySprintId",sprintjoyid);


            i.putExtra("joy_userJoySprint_categoryid",userJoySprint.categoryid);
            i.putExtra("joy_userJoySprint_endingdate",userJoySprint.endingDate);
            i.putExtra("joy_userJoySprint_goal1",userJoySprint.goal1);
            i.putExtra("joy_userJoySprint_goal2",userJoySprint.goal2);
            i.putExtra("joy_userJoySprint_goal3",userJoySprint.goal3);
            i.putExtra("joy_userJoySprint_goal4",userJoySprint.goal4);
            i.putExtra("joy_userJoySprint_numberofweeks",userJoySprint.numberOfWeeks);
            i.putExtra("joy_userJoySprint_sprintactivityid1",userJoySprint.sprintActivityid1);
            i.putExtra("joy_UserJoySprint_sprintactivityid2",userJoySprint.sprintActivityid2);
            i.putExtra("joy_UserJoySprint_sprintoverallscore",userJoySprint.sprintOverallScore);
            i.putExtra("joy_UserJoySprint_startingdate",userJoySprint.startingDate);
            i.putExtra("joy_UserJoySprint_userid",userJoySprint.userId);



            //JOY
            i.putExtra("joy_activityid1_activityscore",userActivityJoyid1.activityScore);
            i.putExtra("joy_activityid1_actualpoints",userActivityJoyid1.actualPoints);
            i.putExtra("joy_activityid1_categoryid",userActivityJoyid1.categoryId);
            i.putExtra("joy_activityid1_activityname",userActivityJoyid1.activityName);
            i.putExtra("joy_activityid1_sprintdailypoints",userActivityJoyid1.sprintDailyPoints);
            i.putExtra("joy_activityid1_targetpoints",userActivityJoyid1.targetPoints);
            i.putExtra("joy_activityid1_userid",userActivityJoyid1.userId);
            i.putExtra("joy_activityid1_activityid",userActivityJoyid1.activityid);


            i.putExtra("joy_activityid2_activityscore",userActivityJoyid2.activityScore);
            i.putExtra("joy_activityid2_actualpoints",userActivityJoyid2.actualPoints);
            i.putExtra("joy_activityid2_categoryid",userActivityJoyid2.categoryId);
            i.putExtra("joy_activityid2_activityname",userActivityJoyid2.activityName);
            i.putExtra("joy_activityid2_sprintdailypoints",userActivityJoyid2.sprintDailyPoints);
            i.putExtra("joy_activityid2_targetpoints",userActivityJoyid2.targetPoints);
            i.putExtra("joy_activityid2_userid",userActivityJoyid2.userId);
            i.putExtra("joy_activityid2_activityid",userActivityJoyid2.activityid);










            /*
            //JOY
            i.putExtra("categoryJoy_categoryid",currentcategoryForuser.categoryid);
            i.putExtra("categoryJoy_endingDate",currentcategoryForuser.endingDate);
            i.putExtra("categoryJoy_goal1",currentcategoryForuser.goal1);
            i.putExtra("categoryJoy_goal2",currentcategoryForuser.goal2);
            i.putExtra("categoryJoy_goal3",currentcategoryForuser.goal3);
            i.putExtra("categoryJoy_goal4",currentcategoryForuser.goal4);
            i.putExtra("categoryJoy_numberofweeks",currentcategoryForuser.numberOfWeeks);
            i.putExtra("categoryJoy_sprintact1",currentcategoryForuser.sprintActivityid1);
            i.putExtra("categoryJoy_sprintact2",currentcategoryForuser.sprintActivityid2);
            i.putExtra("categoryJoy_overallscore",currentcategoryForuser.sprintOverallScore);
            i.putExtra("categoryJoy_startingDate",currentcategoryForuser.startingDate);
            i.putExtra("categoryJoy_userId",currentcategoryForuser.userId);


            //PASSION
            i.putExtra("passionSprintId",sprintpassionid);

            //PASSION -- The 2 most RECENT activities that the user has agreed to work on
            i.putExtra("activity1_score_passion",passionActivity1ForUser.activityScore);
            i.putExtra("activity1_actualpoints_passion",passionActivity1ForUser.actualPoints);
            i.putExtra("activity1_categoryid_passion",passionActivity1ForUser.categoryId);
            i.putExtra("activity1_name_passion",passionActivity1ForUser.activityName);
            i.putExtra("activity1_sprintdailypoints_passion",passionActivity1ForUser.sprintDailyPoints);
            i.putExtra("activity1_targetpoints_passion",passionActivity1ForUser.targetPoints);
            i.putExtra("activity1_userid_passion",passionActivity1ForUser.userId);
            i.putExtra("activity1_activityid_passion",passionActivity1ForUser.activityid);

            //PASSION
            i.putExtra("activity2_score_passion",passionActivity2ForUser.activityScore);
            i.putExtra("activity2_actualpoints_passion",passionActivity2ForUser.actualPoints);
            i.putExtra("activity2_categoryid_passion",passionActivity2ForUser.categoryId);
            i.putExtra("activity2_name_passion",passionActivity2ForUser.activityName);
            i.putExtra("activity2_sprintdailypoints_passion",passionActivity2ForUser.sprintDailyPoints);
            i.putExtra("activity2_targetpoints_passion",passionActivity2ForUser.targetPoints);
            i.putExtra("activity2_userid_passion",passionActivity2ForUser.userId);
            i.putExtra("activity2_activityid_passion",passionActivity2ForUser.activityid);

            //PASSION
            i.putExtra("categoryPassion_categoryid",currentcategoryForuser2.categoryid);
            i.putExtra("categoryPassion_endingDate",currentcategoryForuser2.endingDate);
            i.putExtra("categoryPassion_goal1",currentcategoryForuser2.goal1);
            i.putExtra("categoryPassion_goal2",currentcategoryForuser2.goal2);
            i.putExtra("categoryPassion_goal3",currentcategoryForuser2.goal3);
            i.putExtra("categoryPassion_goal4",currentcategoryForuser2.goal4);
            i.putExtra("categoryPassion_numberofweeks",currentcategoryForuser2.numberOfWeeks);
            i.putExtra("categoryPassion_sprintact1",currentcategoryForuser2.sprintActivityid1);
            i.putExtra("categoryPassion_sprintact2",currentcategoryForuser2.sprintActivityid2);
            i.putExtra("categoryPassion_overallscore",currentcategoryForuser2.sprintOverallScore);
            i.putExtra("categoryPassion_startingDate",currentcategoryForuser2.startingDate);
            i.putExtra("categoryPassion_userId",currentcategoryForuser2.userId);

            */
            this.startActivity(i);

        }else{
            Toast.makeText(this, "Username/Password does not match, Please try again ", Toast.LENGTH_LONG).show();
            name = "";
        }


    } //end of method
} //end of class




package com.example.natalia.lifemanagementfirst;

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

    //GENERAL
    ArrayList<ActivitiesSprint> allActivities;

    //JOY variables
    ArrayList<Category> userJoysprintsHelper;
    ArrayList<User> listUsers;
    ArrayList<Category> currentJoyCategories;
    ArrayList<ActivitiesSprint> activitiesjoyPrevious;
    String userId;
    //Map<String,String> joyIdStartingDateMap;
    String sprintjoyid;
    String currentCategoryuserId; //temporary variable holding the current category user id

    //PASSION variables

    ArrayList<Category> userPassionSprintHelper;
    ArrayList<Category> currentPassionCategories;
    ArrayList<ActivitiesSprint> activitiesPassionPrevious;
    Map<String,String> passionIdStartingDateMap;
    String sprintpassionid;
    String currentPassionCategoryId; //temporary variable holding the current category user id

    //GIVING BACK

    ArrayList<Category> userContributionSprintHelper;
    ArrayList<Category> currentContributionCategories;
    Map<String,String> contributionIdStartingDateMap;
    ArrayList<ActivitiesSprint> activitiesContributionPrevious;
    String sprintcontributionid;
    String currentContributionCategoryId; //temporary variable holding the current category user id

    DatabaseReference databaseCategories;
    DatabaseReference databaseUsers; //our database reference object
    DatabaseReference databaseActivities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //JOY INITIALIZATIONs
        userJoysprintsHelper = new ArrayList<>();
        allActivities = new ArrayList<>();
        listUsers = new ArrayList<>();
        currentJoyCategories = new ArrayList<>();
        activitiesjoyPrevious = new ArrayList<>();

        //PASSION INITIALIZATIONS
        userPassionSprintHelper = new ArrayList<>();
        passionIdStartingDateMap = new TreeMap<>();
        currentPassionCategories = new ArrayList<>();
        activitiesPassionPrevious = new ArrayList<>();

        //GIVING BACK INITIALIZATIONS
        userContributionSprintHelper = new ArrayList<>();
        contributionIdStartingDateMap = new TreeMap<>();
        currentContributionCategories = new ArrayList<>();
        activitiesContributionPrevious = new ArrayList<>();


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
                finish();
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
                    if (categorySnapshot.getValue() == null)
                        break;


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
                            if (activitiesSnapshottemp.getValue() == null)
                                break;

                            String[] f = activitiesSnapshottemp.getValue().toString().split("=");

                            String temporary = f[0];
                            //System.out.println("heyyy66 " + f[0]);
                            String tempId = temporary.substring(1);  //joysprint unique id


                            for (DataSnapshot activitySnapshot2 : activitiesSnapshottemp.getChildren()) { //ids
                                if (activitySnapshot2.getValue() == null)
                                    break;

                                System.out.println("posible joysprinIDDS " + activitySnapshot2.getKey());

                                String[] tempArray = new String[(int) activitySnapshot2.getChildrenCount()];
                                int k = 0;
                                for (DataSnapshot activitySnapshot3 : activitySnapshot2.getChildren()) { //branch
                                    if (activitySnapshot3.getValue() == null)
                                        break;


                                    tempArray[k] = activitySnapshot3.getValue().toString();

                                    System.out.println("heyy temparray[] " + k + " === " + tempArray[k]);
                                    k++;

                                }

                                System.out.println("activitySnapshot2.getKey() " + activitySnapshot2.getKey());
                                System.out.println("temparray[10] " + tempArray[10]);


                                String JoySprintId = activitySnapshot2.getKey();

                                //currentJoycategories will have (endingDate, goal1, goal2, goal3, goal4, NumOfWeeks, sprintActid1, sprintactid2, overallscore, startingDate) for that logged-in user
                                currentJoyCategories.add(new Category(tempArray[0], tempArray[1], tempArray[2], tempArray[3], tempArray[4], tempArray[5], tempArray[6],
                                        tempArray[7], tempArray[8], tempArray[9], tempArray[10], currentCategoryuserId,JoySprintId));
                                //System.out.println("hey3 " + activitySnapshot3.getKey()+": "+ activitySnapshot3.getValue(String.class));
                                //returning = tempArray[5];


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
                            System.out.println("ZZZFG THIS IS CURRENT PASSION Categoryid " + currentPassionCategoryId); //hsshs

                            DataSnapshot activitiesSnapshottemp = categorySnapshot.child("PassionSprints");
                            if (activitiesSnapshottemp.getValue() == null)
                                break;

                            String[] f = activitiesSnapshottemp.getValue().toString().split("=");

                            String temporary = f[0];
                            //System.out.println("heyyy66 " + f[0]);
                            String tempId = temporary.substring(1);  //joysprint unique id


                            for (DataSnapshot activitySnapshot2 : activitiesSnapshottemp.getChildren()) { //ids
                                if (activitySnapshot2.getValue() == null)
                                    break;

                                System.out.println("posible passionsprinIDDS " + activitySnapshot2.getKey());

                                String[] tempArray = new String[(int) activitySnapshot2.getChildrenCount()];
                                int k = 0;
                                for (DataSnapshot activitySnapshot3 : activitySnapshot2.getChildren()) { //branch
                                    if (activitySnapshot3.getValue() == null)
                                        break;


                                    tempArray[k] = activitySnapshot3.getValue().toString();

                                    System.out.println("heyy temparray[] " + k + " === " + tempArray[k]);
                                    k++;

                                }

                                System.out.println("activitySnapshot2.getKey() " + activitySnapshot2.getKey());
                                System.out.println("temparray[10] " + tempArray[10]);

                                String passionSprintid = activitySnapshot2.getKey();

                                //currentJoycategories will have (endingDate, goal1, goal2, goal3, goal4, NumOfWeeks, sprintActid1, sprintactid2, overallscore, startingDate) for that logged-in user
                                currentPassionCategories.add(new Category(tempArray[0], tempArray[1], tempArray[2], tempArray[3], tempArray[4], tempArray[5], tempArray[6],
                                        tempArray[7], tempArray[8], tempArray[9], tempArray[10], currentPassionCategoryId,passionSprintid));
                                //System.out.println("hey3 " + activitySnapshot3.getKey()+": "+ activitySnapshot3.getValue(String.class));
                                //returning = tempArray[5];

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
                            System.out.println("ZZZFG THIS IS CURRENT CONTRIBUTION Categoryid " + currentContributionCategoryId); //hsshs

                            DataSnapshot activitiesSnapshottemp = categorySnapshot.child("ContributionSprints");
                            if (activitiesSnapshottemp.getValue() == null)
                                break;

                            String[] f = activitiesSnapshottemp.getValue().toString().split("=");

                            String temporary = f[0];
                            //System.out.println("heyyy66 " + f[0]);
                            String tempId = temporary.substring(1);  //joysprint unique id


                            for (DataSnapshot activitySnapshot2 : activitiesSnapshottemp.getChildren()) { //ids
                                if (activitySnapshot2.getValue() == null)
                                    break;

                                System.out.println("posible contributionsprinIDDS " + activitySnapshot2.getKey());

                                String[] tempArray = new String[(int) activitySnapshot2.getChildrenCount()];
                                int k = 0;
                                for (DataSnapshot activitySnapshot3 : activitySnapshot2.getChildren()) { //branch
                                    if (activitySnapshot3.getValue() == null)
                                        break;


                                    tempArray[k] = activitySnapshot3.getValue().toString();

                                    System.out.println("heyy temparray[] " + k + " === " + tempArray[k]);
                                    k++;

                                }

                                System.out.println("activitySnapshot2.getKey() " + activitySnapshot2.getKey());
                                System.out.println("temparray[10] " + tempArray[10]);

                                String contributionSprintid = activitySnapshot2.getKey();

                                //currentJoycategories will have (endingDate, goal1, goal2, goal3, goal4, NumOfWeeks, sprintActid1, sprintactid2, overallscore, startingDate) for that logged-in user
                                currentContributionCategories.add(new Category(tempArray[0], tempArray[1], tempArray[2], tempArray[3], tempArray[4], tempArray[5], tempArray[6],
                                        tempArray[7], tempArray[8], tempArray[9], tempArray[10], currentContributionCategoryId,contributionSprintid));
                                //System.out.println("hey3 " + activitySnapshot3.getKey()+": "+ activitySnapshot3.getValue(String.class));
                                //returning = tempArray[5];

                            }

                        }
                        break;

                    }
                } //end of for

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


            List<String> tempList = new ArrayList<>(); //list containing all the sprintActivities of the user

            //JOY
            for (int i = 0; i < currentJoyCategories.size(); i++) {


                if (userId.contains(currentJoyCategories.get(i).userId)) {

                    System.out.println("categoryid:  " + currentJoyCategories.get(i).categoryid + " -- " + currentJoyCategories.get(i).sprintid);

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

                System.out.println("funtioning templist " + tempList.get(i));

                for (int k = 0; k < userActivitiesAll.size(); k++) {

                    String [] splitter = tempList.get(i).split(" ");

                    if (userActivitiesAll.get(k).activityid.contains(splitter[0])){

                        //fake the data for later use (categoryid and userid)
                        activitiesjoyPrevious.add(new ActivitiesSprint(userActivitiesAll.get(k).activityScore,userActivitiesAll.get(k).actualPoints,
                                splitter[1], userActivitiesAll.get(k).activityName, userActivitiesAll.get(k).sprintDailyPoints,
                                userActivitiesAll.get(k).targetPoints, splitter[2], userActivitiesAll.get(k).activityid));
                        break;
                    }
                }
            } //end of for


            //PASSION
            List<String> tempListPassion = new ArrayList<>(); //list containing all the sprintActivities of the user


            for (int i = 0; i < currentPassionCategories.size(); i++) {


                if (userId.contains(currentPassionCategories.get(i).userId)) {

                    System.out.println("categoryid PASSION:  " + currentPassionCategories.get(i).categoryid + " -- " + currentPassionCategories.size());

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

                System.out.println("funtioning templist passion " + tempListPassion.get(i));

                for (int k = 0; k < userActivitiesAll.size(); k++) {

                    String [] splitter = tempListPassion.get(i).split(" ");

                    if (userActivitiesAll.get(k).activityid.contains(splitter[0])){

                        //fake the data for later use (categoryid and userid)
                        activitiesPassionPrevious.add(new ActivitiesSprint(userActivitiesAll.get(k).activityScore,userActivitiesAll.get(k).actualPoints,
                                splitter[1], userActivitiesAll.get(k).activityName, userActivitiesAll.get(k).sprintDailyPoints,
                                userActivitiesAll.get(k).targetPoints, splitter[2], userActivitiesAll.get(k).activityid));
                        break;
                    }
                }
            } //end of for



            List<String> tempListContribution = new ArrayList<>(); //list containing all the sprintActivities of the user
            //GIVING BACK
            for (int i = 0; i < currentContributionCategories.size(); i++) {


                if (userId.contains(currentContributionCategories.get(i).userId)) {

                    System.out.println("categoryid CONTRIBUTION:  " + currentContributionCategories.get(i).categoryid + " -- " + currentContributionCategories.size());

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

                System.out.println("funtioning templist contribution " + tempListContribution.get(i));

                for (int k = 0; k < userActivitiesAll.size(); k++) {

                    String [] splitter = tempListContribution.get(i).split(" ");

                    if (userActivitiesAll.get(k).activityid.contains(splitter[0])){

                        //fake the data for later use (categoryid and userid)
                        activitiesContributionPrevious.add(new ActivitiesSprint(userActivitiesAll.get(k).activityScore,userActivitiesAll.get(k).actualPoints,
                                splitter[1], userActivitiesAll.get(k).activityName, userActivitiesAll.get(k).sprintDailyPoints,
                                userActivitiesAll.get(k).targetPoints, splitter[2], userActivitiesAll.get(k).activityid));
                        break;
                    }
                }
            } //end of for

            //JOY

            TreeMap<String,String> joydateIdentifier = new TreeMap<String,String>();

            for (int i = 0; i < userJoysprintsHelper.size(); i++) {

                joydateIdentifier.put(userJoysprintsHelper.get(i).startingDate,userJoysprintsHelper.get(i).sprintid);

            }


            String joycurrentDate = "";
            for(Map.Entry<String,String> entr: joydateIdentifier.entrySet()){
                System.out.println("Keyw JOY : " + entr.getKey() + " ValueJOYW : " + entr.getValue());
                //get the most current startind date
                joycurrentDate = entr.getKey();
                sprintjoyid = entr.getValue();
            }


            //PASSION

            TreeMap<String,String> passiondateIdentifier = new TreeMap<String,String>();

            for (int i = 0; i < userPassionSprintHelper.size(); i++) {

                passiondateIdentifier.put(userPassionSprintHelper.get(i).startingDate,userPassionSprintHelper.get(i).sprintid);

            }

            String passionleastDate = "";
            for(Map.Entry<String,String> entr: passiondateIdentifier.entrySet()){
                System.out.println("Keyw PASSION : " + entr.getKey() + " ValuePASSIONW : " + entr.getValue());
                passionleastDate = entr.getKey();
                sprintpassionid = entr.getValue();
                //break
            }

            //GIVING BACK

            TreeMap<String,String> contributiondateIdentifier = new TreeMap<String,String>();

            for (int i = 0; i < userContributionSprintHelper.size(); i++) {

                contributiondateIdentifier.put(userContributionSprintHelper.get(i).startingDate,userContributionSprintHelper.get(i).sprintid);
            }

            String contributionleastDate = "";
            for(Map.Entry<String,String> entr: contributiondateIdentifier.entrySet()){
                System.out.println("Keyw CONTRIBUTION : " + entr.getKey() + " ValueCONTRIBUTION : " + entr.getValue());
                contributionleastDate = entr.getKey();
                sprintcontributionid = entr.getValue();
                //break;
            }



            //JOY
            Category userJoySprint = new Category();
            for (int i = 0; i < userJoysprintsHelper.size(); i++) {

                if (userJoysprintsHelper.get(i).startingDate.contains(joycurrentDate)) {


                    userJoySprint = new Category(userJoysprintsHelper.get(i).categoryid, userJoysprintsHelper.get(i).endingDate,
                            userJoysprintsHelper.get(i).goal1, userJoysprintsHelper.get(i).goal2, userJoysprintsHelper.get(i).goal3,
                            userJoysprintsHelper.get(i).goal4, userJoysprintsHelper.get(i).numberOfWeeks, userJoysprintsHelper.get(i).sprintActivityid1,
                            userJoysprintsHelper.get(i).sprintActivityid2, userJoysprintsHelper.get(i).sprintOverallScore,
                            userJoysprintsHelper.get(i).startingDate, userJoysprintsHelper.get(i).userId,userJoysprintsHelper.get(i).sprintid);
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
            System.out.println("ss5 sprintid " + userJoySprint.sprintid);


            //PASSION
            Category userPassionSprint = new Category();
            for (int i = 0; i < userPassionSprintHelper.size(); i++) {

                if (userPassionSprintHelper.get(i).startingDate.contains(passionleastDate)) {


                    userPassionSprint = new Category(userPassionSprintHelper.get(i).categoryid, userPassionSprintHelper.get(i).endingDate,
                            userPassionSprintHelper.get(i).goal1, userPassionSprintHelper.get(i).goal2, userPassionSprintHelper.get(i).goal3,
                            userPassionSprintHelper.get(i).goal4, userPassionSprintHelper.get(i).numberOfWeeks, userPassionSprintHelper.get(i).sprintActivityid1,
                            userPassionSprintHelper.get(i).sprintActivityid2, userPassionSprintHelper.get(i).sprintOverallScore,
                            userPassionSprintHelper.get(i).startingDate, userPassionSprintHelper.get(i).userId,userPassionSprintHelper.get(i).sprintid);
                }
            }

            System.out.println("ss5 PASSION " + userPassionSprint.categoryid);
            System.out.println("ss5 PASSION " + userPassionSprint.endingDate);
            System.out.println("ss5 PASSION goal1 " + userPassionSprint.goal1);
            System.out.println("ss5 PASSION goal2 " + userPassionSprint.goal2);
            System.out.println("ss5 PASSION goal3 " + userPassionSprint.goal3);
            System.out.println("ss5 PASSION goal4 " + userPassionSprint.goal4);
            System.out.println("ss5 PASSION" + userPassionSprint.numberOfWeeks);
            System.out.println("ss5 PASSION sprintid1 " + userPassionSprint.sprintActivityid1);
            System.out.println("ss5 PASSION sprintid2 " + userPassionSprint.sprintActivityid2);
            System.out.println("ss5 PASSION overallscoe " + userPassionSprint.sprintOverallScore);
            System.out.println("ss5 PASSION starting " + userPassionSprint.startingDate);
            System.out.println("ss5 PASSION userid " + userPassionSprint.userId);
            System.out.println("ss5 PASSION sprintid " + userPassionSprint.sprintid);


            //GIVING BACK
            Category userContributionSprint = new Category();
            for (int i = 0; i < userContributionSprintHelper.size(); i++) {

                if (userContributionSprintHelper.get(i).startingDate.contains(contributionleastDate)) {

                    userContributionSprint = new Category(userContributionSprintHelper.get(i).categoryid, userContributionSprintHelper.get(i).endingDate,
                            userContributionSprintHelper.get(i).goal1, userContributionSprintHelper.get(i).goal2, userContributionSprintHelper.get(i).goal3,
                            userContributionSprintHelper.get(i).goal4, userContributionSprintHelper.get(i).numberOfWeeks, userContributionSprintHelper.get(i).sprintActivityid1,
                            userContributionSprintHelper.get(i).sprintActivityid2, userContributionSprintHelper.get(i).sprintOverallScore,
                            userContributionSprintHelper.get(i).startingDate, userContributionSprintHelper.get(i).userId,userContributionSprintHelper.get(i).sprintid);
                }
            }



            //JOY
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


            //PASSION
            ActivitiesSprint userActivityPassionid1 = new ActivitiesSprint();
            ActivitiesSprint userActivityPassionid2 = new ActivitiesSprint();

            for (int i = 0; i < userActivitiesAll.size(); i++) {


                if (userActivitiesAll.get(i).activityid.contains(userPassionSprint.sprintActivityid1)) {
                    userActivityPassionid1 = new ActivitiesSprint(userActivitiesAll.get(i).activityScore,
                            userActivitiesAll.get(i).actualPoints, userActivitiesAll.get(i).categoryId,
                            userActivitiesAll.get(i).activityName, userActivitiesAll.get(i).sprintDailyPoints,
                            userActivitiesAll.get(i).targetPoints, userActivitiesAll.get(i).userId, userActivitiesAll.get(i).activityid);
                }

                if (userActivitiesAll.get(i).activityid.contains(userPassionSprint.sprintActivityid2)) {

                    userActivityPassionid2 = new ActivitiesSprint(userActivitiesAll.get(i).activityScore,
                            userActivitiesAll.get(i).actualPoints, userActivitiesAll.get(i).categoryId,
                            userActivitiesAll.get(i).activityName, userActivitiesAll.get(i).sprintDailyPoints,
                            userActivitiesAll.get(i).targetPoints, userActivitiesAll.get(i).userId, userActivitiesAll.get(i).activityid);

                }
            }


            System.out.println("ss8 PASSION score " + userActivityPassionid1.activityScore);
            System.out.println("ss8 PASSION points " + userActivityPassionid1.actualPoints);
            System.out.println("ss8 PASSION catid " + userActivityPassionid1.categoryId);
            System.out.println("ss8 PASSION actname" + userActivityPassionid1.activityName);
            System.out.println("ss8 PASSION dailypoints " + userActivityPassionid1.sprintDailyPoints);
            System.out.println("ss8 PASSION target" + userActivityPassionid1.targetPoints);
            System.out.println("ss8 PASSION userid " + userActivityPassionid1.userId);
            System.out.println("ss8 PASSION actid " + userActivityPassionid1.activityid);


            System.out.println("ss9 PASSION score " + userActivityPassionid2.activityScore);
            System.out.println("ss9 PASSION points " + userActivityPassionid2.actualPoints);
            System.out.println("ss9 PASSION catid " + userActivityPassionid2.categoryId);
            System.out.println("ss9 PASSION actname" + userActivityPassionid2.activityName);
            System.out.println("ss9 PASSION dailypoints " + userActivityPassionid2.sprintDailyPoints);
            System.out.println("ss9 PASSION target" + userActivityPassionid2.targetPoints);
            System.out.println("ss9 PASSION userid " + userActivityPassionid2.userId);
            System.out.println("ss9 PASSION actid " + userActivityPassionid2.activityid);


            //GIVING BACK
            ActivitiesSprint userActivityContributionid1 = new ActivitiesSprint();
            ActivitiesSprint userActivityContributionid2 = new ActivitiesSprint();

            for (int i = 0; i < userActivitiesAll.size(); i++) {


                if (userActivitiesAll.get(i).activityid.contains(userContributionSprint.sprintActivityid1)) {
                    userActivityContributionid1 = new ActivitiesSprint(userActivitiesAll.get(i).activityScore,
                            userActivitiesAll.get(i).actualPoints, userActivitiesAll.get(i).categoryId,
                            userActivitiesAll.get(i).activityName, userActivitiesAll.get(i).sprintDailyPoints,
                            userActivitiesAll.get(i).targetPoints, userActivitiesAll.get(i).userId, userActivitiesAll.get(i).activityid);
                }

                if (userActivitiesAll.get(i).activityid.contains(userContributionSprint.sprintActivityid2)) {

                    userActivityContributionid2 = new ActivitiesSprint(userActivitiesAll.get(i).activityScore,
                            userActivitiesAll.get(i).actualPoints, userActivitiesAll.get(i).categoryId,
                            userActivitiesAll.get(i).activityName, userActivitiesAll.get(i).sprintDailyPoints,
                            userActivitiesAll.get(i).targetPoints, userActivitiesAll.get(i).userId, userActivitiesAll.get(i).activityid);

                }
            }


            //SAVE EVERYTHING IS NEEDED
            Intent i = new Intent(LoginActivity.this,Dashboard.class);


            //saving all data do we can use it in the next screen
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("mylist", listUsers);
            bundle.putParcelableArrayList("allActivities", allActivities);
            bundle.putParcelableArrayList("userActivitiesAllList",userActivitiesAll);

            //JOY
            bundle.putParcelableArrayList("categoriesJoyCategories",currentJoyCategories);
            bundle.putParcelableArrayList("userJoysprintHelperList",userJoysprintsHelper);
            bundle.putParcelableArrayList("activitiesJOYPrevious",activitiesjoyPrevious);


            //PASSION
            bundle.putParcelableArrayList("categoriesPassionCategories",currentPassionCategories);
            bundle.putParcelableArrayList("userPassionsprintHelperList",userPassionSprintHelper);
            bundle.putParcelableArrayList("activitiesPassionPrevious",activitiesPassionPrevious);

            //GIVING BACK
            bundle.putParcelableArrayList("categoriesContributionCategories",currentContributionCategories);
            bundle.putParcelableArrayList("userContributionsprintHelperList",userContributionSprintHelper);
            bundle.putParcelableArrayList("activitiesContributionPrevious",activitiesContributionPrevious);

            i.putExtras(bundle);
            i.putExtra("userNameY",name);
            i.putExtra("passwordY",pass);

            //JOY
            i.putExtra("joySprintId",sprintjoyid);

            //PASSION
            i.putExtra("passionSprintId",sprintpassionid);

            //GIVIGNG BACK
            i.putExtra("contributionSprintId",sprintcontributionid);


            //JOY

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


            //PASSION
            i.putExtra("passion_userPassionSprint_categoryid",userPassionSprint.categoryid);
            i.putExtra("passion_userPassionSprint_endingdate",userPassionSprint.endingDate);
            i.putExtra("passion_userPassionSprint_goal1",userPassionSprint.goal1);
            i.putExtra("passion_userPassionSprint_goal2",userPassionSprint.goal2);
            i.putExtra("passion_userPassionSprint_goal3",userPassionSprint.goal3);
            i.putExtra("passion_userPassionSprint_goal4",userPassionSprint.goal4);
            i.putExtra("passion_userPassionSprint_numberofweeks",userPassionSprint.numberOfWeeks);
            i.putExtra("passion_userPassionSprint_sprintactivityid1",userPassionSprint.sprintActivityid1);
            i.putExtra("passion_userPassionSprint_sprintactivityid2",userPassionSprint.sprintActivityid2);
            i.putExtra("passion_userPassionSprint_sprintoverallscore",userPassionSprint.sprintOverallScore);
            i.putExtra("passion_userPassionSprint_startingdate",userPassionSprint.startingDate);
            i.putExtra("passion_userPassionSprint_userid",userPassionSprint.userId);


            i.putExtra("passion_activityid1_activityscore",userActivityPassionid1.activityScore);
            i.putExtra("passion_activityid1_actualpoints",userActivityPassionid1.actualPoints);
            i.putExtra("passion_activityid1_categoryid",userActivityPassionid1.categoryId);
            i.putExtra("passion_activityid1_activityname",userActivityPassionid1.activityName);
            i.putExtra("passion_activityid1_sprintdailypoints",userActivityPassionid1.sprintDailyPoints);
            i.putExtra("passion_activityid1_targetpoints",userActivityPassionid1.targetPoints);
            i.putExtra("passion_activityid1_userid",userActivityPassionid1.userId);
            i.putExtra("passion_activityid1_activityid",userActivityPassionid1.activityid);


            i.putExtra("passion_activityid2_activityscore",userActivityPassionid2.activityScore);
            i.putExtra("passion_activityid2_actualpoints",userActivityPassionid2.actualPoints);
            i.putExtra("passion_activityid2_categoryid",userActivityPassionid2.categoryId);
            i.putExtra("passion_activityid2_activityname",userActivityPassionid2.activityName);
            i.putExtra("passion_activityid2_sprintdailypoints",userActivityPassionid2.sprintDailyPoints);
            i.putExtra("passion_activityid2_targetpoints",userActivityPassionid2.targetPoints);
            i.putExtra("passion_activityid2_userid",userActivityPassionid2.userId);
            i.putExtra("passion_activityid2_activityid",userActivityPassionid2.activityid);



            //GIVING BACK
            i.putExtra("contribution_userContributionSprint_categoryid",userContributionSprint.categoryid);
            i.putExtra("contribution_userContributionSprint_endingdate",userContributionSprint.endingDate);
            i.putExtra("contribution_userContributionSprint_goal1",userContributionSprint.goal1);
            i.putExtra("contribution_userContributionSprint_goal2",userContributionSprint.goal2);
            i.putExtra("contribution_userContributionSprint_goal3",userContributionSprint.goal3);
            i.putExtra("contribution_userContributionSprint_goal4",userContributionSprint.goal4);
            i.putExtra("contribution_userContributionSprint_numberofweeks",userContributionSprint.numberOfWeeks);
            i.putExtra("contribution_userContributionSprint_sprintactivityid1",userContributionSprint.sprintActivityid1);
            i.putExtra("contribution_userContributionSprint_sprintactivityid2",userContributionSprint.sprintActivityid2);
            i.putExtra("contribution_userContributionSprint_sprintoverallscore",userContributionSprint.sprintOverallScore);
            i.putExtra("contribution_userContributionSprint_startingdate",userContributionSprint.startingDate);
            i.putExtra("contribution_userContributionSprint_userid",userContributionSprint.userId);


            i.putExtra("contribution_activityid1_activityscore",userActivityContributionid1.activityScore);
            i.putExtra("contribution_activityid1_actualpoints",userActivityContributionid1.actualPoints);
            i.putExtra("contribution_activityid1_categoryid",userActivityContributionid1.categoryId);
            i.putExtra("contribution_activityid1_activityname",userActivityContributionid1.activityName);
            i.putExtra("contribution_activityid1_sprintdailypoints",userActivityContributionid1.sprintDailyPoints);
            i.putExtra("contribution_activityid1_targetpoints",userActivityContributionid1.targetPoints);
            i.putExtra("contribution_activityid1_userid",userActivityContributionid1.userId);
            i.putExtra("contribution_activityid1_activityid",userActivityContributionid1.activityid);


            i.putExtra("contribution_activityid2_activityscore",userActivityContributionid2.activityScore);
            i.putExtra("contribution_activityid2_actualpoints",userActivityContributionid2.actualPoints);
            i.putExtra("contribution_activityid2_categoryid",userActivityContributionid2.categoryId);
            i.putExtra("contribution_activityid2_activityname",userActivityContributionid2.activityName);
            i.putExtra("contribution_activityid2_sprintdailypoints",userActivityContributionid2.sprintDailyPoints);
            i.putExtra("contribution_activityid2_targetpoints",userActivityContributionid2.targetPoints);
            i.putExtra("contribution_activityid2_userid",userActivityContributionid2.userId);
            i.putExtra("contribution_activityid2_activityid",userActivityContributionid2.activityid);


            this.startActivity(i);


        }else{
            Toast.makeText(this, "Username/Password does not match, Please try again ", Toast.LENGTH_LONG).show();
            name = "";
        }


    } //end of method
} //end of class




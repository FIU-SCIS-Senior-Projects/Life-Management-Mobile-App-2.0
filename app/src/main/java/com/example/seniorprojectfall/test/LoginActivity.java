package com.example.seniorprojectfall.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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
    ListView listViewUsers;
    boolean pass = false;

    //arraylist containing all the joysprints for the user that is logged in
    ArrayList<Category> joysprintsForUserHelper;
    ArrayList<Category> joysprintsForUser;

    ArrayList<User> listUsers; //store all the artist from firebase database
    ArrayList<Category> currentJoyCategories;
    ArrayList<Category> currentJoyCategorieshelper;
    ArrayList<ActivitiesSprint> currentJoyActivities;

    String userId;

    Map<String,String> currentJoySprintidtotal;
    String sprintjoyid;
    String currentCategoryuserId;

    DatabaseReference databaseCategories;
    DatabaseReference databaseUsers; //our database reference object
    DatabaseReference databaseActivities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        joysprintsForUserHelper = new ArrayList<>();
        joysprintsForUser = new ArrayList<>();
        currentJoySprintidtotal = new TreeMap<>();


        //getting the reference of artists node
        databaseUsers = FirebaseDatabase.getInstance().getReference("Users");
        databaseActivities = FirebaseDatabase.getInstance().getReference("Activities");
        databaseCategories = FirebaseDatabase.getInstance().getReference("Categories");

        username = (EditText) findViewById(R.id.usernameLogin);
        password = (EditText) findViewById(R.id.passwordLogin);

        signIn = (Button) findViewById(R.id.buttonSignInLogin);
        registerUser = (Button) findViewById(R.id.buttonRegisterUserLogin);

        currentJoyActivities = new ArrayList<>();
        listUsers = new ArrayList<>();
        currentJoyCategories = new ArrayList<>();
        currentJoyCategorieshelper = new ArrayList<>();




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

                    for (int i = separator.length - 1; i >= 0; i--) {

                        if (separator[i].length() > 15 && (separator[i].contains("userId"))) {
                            //get the userId
                            String temp = separator[i];
                            int g = temp.indexOf("=");
                            ++g;

                            currentCategoryuserId = temp.substring(g,temp.length()-1);
                            System.out.println("ZZZFG THIS IS CURRENTCategoryid " + currentCategoryuserId); //hsshs

                            DataSnapshot activitiesSnapshottemp = categorySnapshot.child("JoySprints");

                            String []f = activitiesSnapshottemp.getValue().toString().split("=");

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

                                System.out.println("temparray[10] " + tempArray[10]);

                                currentJoySprintidtotal.put(activitySnapshot2.getKey()+"",tempArray[10]); //storing the joy sprint id and its starting date

                                //currentJoycategories will have (endingDate, goal1, goal2, goal3, goal4, NumOfWeeks, sprintActid1, sprintactid2, overallscore, startingDate) for that logged-in user
                                currentJoyCategories.add(new Category(tempArray[0], tempArray[1], tempArray[2], tempArray[3], tempArray[4], tempArray[5], tempArray[6],
                                        tempArray[7],tempArray[8],tempArray[9],tempArray[10],currentCategoryuserId));
                                //System.out.println("hey3 " + activitySnapshot3.getKey()+": "+ activitySnapshot3.getValue(String.class));
                                //returning = tempArray[5];


                            }

                    }

                    break;
                    }

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

                    currentJoyActivities.add(new ActivitiesSprint(temp[0],temp[1],temp[2],temp[3],temp[4],temp[5],temp[6],activityId));

                }

                System.out.println("ACTI SIZE LOGIN " + currentJoyActivities.size());
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


            for(int i=0;i<currentJoyCategories.size();i++){


                if(userId.contains(currentJoyCategories.get(i).userId)){

                    System.out.println("categoryid:  " + currentJoyCategories.get(i).categoryid + " -- " + currentJoyCategories.size());

                    joysprintsForUserHelper.add(new Category(currentJoyCategories.get(i).categoryid,currentJoyCategories.get(i).endingDate,
                            currentJoyCategories.get(i).goal1,currentJoyCategories.get(i).goal2,currentJoyCategories.get(i).goal3,
                            currentJoyCategories.get(i).goal4,currentJoyCategories.get(i).numberOfWeeks,currentJoyCategories.get(i).sprintActivityid1,
                            currentJoyCategories.get(i).sprintActivityid2,currentJoyCategories.get(i).sprintOverallScore,
                            currentJoyCategories.get(i).startingDate,currentJoyCategories.get(i).userId));
                }

            }



            int[] firstDateidentifier = new int[joysprintsForUserHelper.size()];
            for(int i=0;i<joysprintsForUserHelper.size();i++){

                firstDateidentifier[i] = Integer.parseInt(joysprintsForUserHelper.get(i).startingDate);

                System.out.println("firstdateidentifier[i]  " + firstDateidentifier[i]);

                System.out.println("ss2 categ " + joysprintsForUserHelper.get(i).categoryid);
                System.out.println("ss2 ending " + joysprintsForUserHelper.get(i).endingDate);
                System.out.println("ss2 gol1 " + joysprintsForUserHelper.get(i).goal1);
                System.out.println("ss2 gol2 " + joysprintsForUserHelper.get(i).goal2);
                System.out.println("ss2 gol3 " + joysprintsForUserHelper.get(i).goal3);
                System.out.println("ss2 gol4 " + joysprintsForUserHelper.get(i).goal4);
                System.out.println("ss2 numwee " + joysprintsForUserHelper.get(i).numberOfWeeks);
                System.out.println("ss2 sprintid1 " + joysprintsForUserHelper.get(i).sprintActivityid1);
                System.out.println("ss2 sprintid2 " + joysprintsForUserHelper.get(i).sprintActivityid2);
                System.out.println("ss2 overallscoe " + joysprintsForUserHelper.get(i).sprintOverallScore);
                System.out.println("ss2 starting " + joysprintsForUserHelper.get(i).startingDate);
                System.out.println("ss2 starting " + joysprintsForUserHelper.get(i).userId);

            }

            //sort it by starting date
            Arrays.sort(firstDateidentifier);


            String startingdateid1 = "";
            String temp = firstDateidentifier[0]+"";

            if(temp.length()!=7){
                startingdateid1 = "0"+firstDateidentifier[0];
            }else{
                startingdateid1 = ""+firstDateidentifier[0];
            }

            Category currentcategoryForuser = new Category();


            for(int i=0;i<joysprintsForUserHelper.size();i++){

                if(joysprintsForUserHelper.get(i).startingDate.contains(startingdateid1)){


                    currentcategoryForuser = new Category(joysprintsForUserHelper.get(i).categoryid,joysprintsForUserHelper.get(i).endingDate,
                            joysprintsForUserHelper.get(i).goal1,joysprintsForUserHelper.get(i).goal2,joysprintsForUserHelper.get(i).goal3,
                            joysprintsForUserHelper.get(i).goal4,joysprintsForUserHelper.get(i).numberOfWeeks,joysprintsForUserHelper.get(i).sprintActivityid1,
                            joysprintsForUserHelper.get(i).sprintActivityid2,joysprintsForUserHelper.get(i).sprintOverallScore,
                            joysprintsForUserHelper.get(i).startingDate,joysprintsForUserHelper.get(i).userId);
                }
            }

            System.out.println("ss5  " + currentcategoryForuser.categoryid);
            System.out.println("ss5 " + currentcategoryForuser.endingDate);
            System.out.println("ss5 " + currentcategoryForuser.goal1);
            System.out.println("ss5 " + currentcategoryForuser.goal2);
            System.out.println("ss5 " + currentcategoryForuser.goal3);
            System.out.println("ss5 " + currentcategoryForuser.goal4);
            System.out.println("ss5 " + currentcategoryForuser.numberOfWeeks);
            System.out.println("ss5 sprintid1 " + currentcategoryForuser.sprintActivityid1);
            System.out.println("ss5 sprintid2 " + currentcategoryForuser.sprintActivityid2);
            System.out.println("ss5 overallscoe " + currentcategoryForuser.sprintOverallScore);
            System.out.println("ss5 starting " + currentcategoryForuser.startingDate);
            System.out.println("ss5 userid " + currentcategoryForuser.userId);



            //so ecurrentcategoryForuser will have the 2 most recent
            //activities id for the 2 activities so lets search for them


            ActivitiesSprint joyActivity1ForUser = new ActivitiesSprint();
            ActivitiesSprint joyActivity2ForUser = new ActivitiesSprint();
            for(int i=0;i<currentJoyActivities.size();i++){


                if(currentJoyActivities.get(i).activityid.equals(currentcategoryForuser.sprintActivityid1) && currentJoyActivities.get(i).userId.equals(currentcategoryForuser.userId)){
                    //get all the activities for that user

                    joyActivity1ForUser = (new ActivitiesSprint(currentJoyActivities.get(i).activityScore,
                            currentJoyActivities.get(i).actualPoints,currentJoyActivities.get(i).categoryId,
                            currentJoyActivities.get(i).activityName,currentJoyActivities.get(i).sprintDailyPoints,
                            currentJoyActivities.get(i).targetPoints,currentJoyActivities.get(i).userId,currentJoyActivities.get(i).activityid));
                }

                if(currentJoyActivities.get(i).activityid.equals(currentcategoryForuser.sprintActivityid2) && currentJoyActivities.get(i).userId.equals(currentcategoryForuser.userId)){
                    //get all the activities for that user

                    joyActivity2ForUser = (new ActivitiesSprint(currentJoyActivities.get(i).activityScore,
                            currentJoyActivities.get(i).actualPoints,currentJoyActivities.get(i).categoryId,
                            currentJoyActivities.get(i).activityName,currentJoyActivities.get(i).sprintDailyPoints,
                            currentJoyActivities.get(i).targetPoints,currentJoyActivities.get(i).userId,currentJoyActivities.get(i).activityid));
                }
/*
                System.out.println("ss3 gol2" + currentJoyActivities.get(i).activityScore);
                System.out.println("ss3 gol3" + currentJoyActivities.get(i).actualPoints);
                System.out.println("ss3 gol4" + currentJoyActivities.get(i).categoryId);
                System.out.println("ss3 sprintid1" + currentJoyActivities.get(i).activityName);
                System.out.println("ss3 sprintid2" + currentJoyActivities.get(i).sprintDailyPoints);
                System.out.println("ss3 overallscoe" + currentJoyActivities.get(i).targetPoints);
                System.out.println("ss3 starting" + currentJoyActivities.get(i).userId);
                */
            }



                System.out.println("ss7 gol " + joyActivity1ForUser.activityScore);
                System.out.println("ss7 gol " + joyActivity1ForUser.actualPoints);
                System.out.println("ss7 gol " + joyActivity1ForUser.categoryId);
                System.out.println("ss7 sprintid1 " + joyActivity1ForUser.activityName);
                System.out.println("ss7 sprintid2 " + joyActivity1ForUser.sprintDailyPoints);
                System.out.println("ss7 overallscore " + joyActivity1ForUser.targetPoints);
                System.out.println("ss7 starting " + joyActivity1ForUser.userId);
                System.out.println("ss7 actiid " + joyActivity1ForUser.activityid);

            System.out.println("ss8 gol " + joyActivity2ForUser.activityScore);
            System.out.println("ss8 points " + joyActivity2ForUser.actualPoints);
            System.out.println("ss8 catid " + joyActivity2ForUser.categoryId);
            System.out.println("ss8 sprintid1 " + joyActivity2ForUser.activityName);
            System.out.println("ss8 sprintid2 " + joyActivity2ForUser.sprintDailyPoints);
            System.out.println("ss8 overallscore " + joyActivity2ForUser.targetPoints);
            System.out.println("ss8 starting " + joyActivity2ForUser.userId);
            System.out.println("ss8 actiid " + joyActivity2ForUser.activityid);


            System.out.println("sprintid test " + currentJoySprintidtotal);


            for(Map.Entry<String,String> entr: currentJoySprintidtotal.entrySet()){
                System.out.println("Keyw : " + entr.getKey() + " Valuew : " + entr.getValue());

                if(currentcategoryForuser.startingDate.contains(entr.getValue())){
                    sprintjoyid = entr.getKey();
                    break;
                }
            }

            Intent i = new Intent(LoginActivity.this,Dashboard.class);


            //saving all data do we can use it in the next screen
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("mylist", listUsers);
            bundle.putParcelableArrayList("currentjoyactivitylist", currentJoyActivities); //total activities (needed for previous cycle)
            bundle.putParcelableArrayList("categoriesJoyList",currentJoyCategories); //total categories (needed for previous cycle)

            i.putExtras(bundle);
            i.putExtra("joySprintId",sprintjoyid);
            i.putExtra("userNameY",name);
            i.putExtra("passwordY",pass);

            //The 2 most RECENT activities that the user has agreed to work on
            i.putExtra("activity1_score",joyActivity1ForUser.activityScore);
            i.putExtra("activity1_actualpoints",joyActivity1ForUser.actualPoints);
            i.putExtra("activity1_categoryid",joyActivity1ForUser.categoryId);
            i.putExtra("activity1_name",joyActivity1ForUser.activityName);
            i.putExtra("activity1_sprintdailypoints",joyActivity1ForUser.sprintDailyPoints);
            i.putExtra("activity1_targetpoints",joyActivity1ForUser.targetPoints);
            i.putExtra("activity1_userid",joyActivity1ForUser.userId);
            i.putExtra("activity1_activityid",joyActivity1ForUser.activityid);


            i.putExtra("activity2_score",joyActivity2ForUser.activityScore);
            i.putExtra("activity2_actualpoints",joyActivity2ForUser.actualPoints);
            i.putExtra("activity2_categoryid",joyActivity2ForUser.categoryId);
            i.putExtra("activity2_name",joyActivity2ForUser.activityName);
            i.putExtra("activity2_sprintdailypoints",joyActivity2ForUser.sprintDailyPoints);
            i.putExtra("activity2_targetpoints",joyActivity2ForUser.targetPoints);
            i.putExtra("activity2_userid",joyActivity2ForUser.userId);
            i.putExtra("activity2_activityid",joyActivity2ForUser.activityid);


            this.startActivity(i);

        }else{
            Toast.makeText(this, "Username/Password does not match, Please try again ", Toast.LENGTH_LONG).show();
            name = "";
        }

    } //end of method
} //end of class




package com.example.seniorprojectfall.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

//import in.goodiebag.carouselpicker.CarouselPicker;

public class LoginActivity extends AppCompatActivity {


    EditText username,password;
    Button signIn, registerUser;
    ListView listViewUsers;
    boolean pass = false;

    ArrayList<User> listUsers; //store all the artist from firebase database
    ArrayList<Category> currentJoyCategories;
    ArrayList<ActivitiesSprint> currentJoyActivities;
    ArrayList<String> joyActivityIds;

    String currentJoySprintid;
    String currentCategoryuserId;

    DatabaseReference databaseCategories;
    DatabaseReference databaseUsers; //our database reference object
    DatabaseReference databaseActivities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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
        joyActivityIds = new ArrayList<>();

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
                    System.out.println("user " + postSnapshot.getValue());
                    User users = postSnapshot.getValue(User.class);
                    //adding artist to the list
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


                    //System.out.println("idddd " + categorySnapshot.getKey());

                    if (pass) {
                        break;
                    }

                    //System.out.println("hey11 " + categorySnapshot.getValue(String.class));
                    String[] separator = categorySnapshot.getValue().toString().split(" ");

                    for (int i = separator.length - 1; i >= 0; i--) {

                        if (separator[i].length() > 15 && (separator[i].contains("userId"))) {
                            //get the userId
                            String temp = separator[i];
                            int g = temp.indexOf("=");
                            ++g;

                            currentCategoryuserId = temp.substring(g,temp.length()-1);

                            pass = true;
                        }

                        if (pass) {
                            //System.out.println("heyHHHPASS boolean  ");
                            break;
                        }
                    }

                    DataSnapshot activitiesSnapshot = categorySnapshot.child("JoySprints");

                    System.out.println("heyyy66 " + activitiesSnapshot.getValue());
                    String []f = activitiesSnapshot.getValue().toString().split("=");

                    String temp = f[0];
                    //System.out.println("heyyy66 " + f[0]);
                    String tempId = temp.substring(1);  //joysprint unique id

                    for (DataSnapshot activitySnapshot2 : activitiesSnapshot.getChildren()) { //ids


                        String[] tempArray = new String[(int) activitySnapshot2.getChildrenCount()];
                        int i = 0;
                        for (DataSnapshot activitySnapshot3 : activitySnapshot2.getChildren()) { //branch


                            tempArray[i] = activitySnapshot3.getValue().toString();

                            System.out.println("heyy temparray[] " + i + " === " + tempArray[i]);
                            i++;

                        }

                        System.out.println("testing currentcatuserid in login " + currentCategoryuserId);

                        //currentJoycategories will have (endingDate, goal1, goal2, goal3, goal4, NumOfWeeks, sprintActid1, sprintactid2, overallscore, startingDate) for that logged-in user
                        currentJoyCategories.add(new Category(tempArray[0], tempArray[1], tempArray[2], tempArray[3], tempArray[4], tempArray[5], tempArray[6],
                                tempArray[7],tempArray[8],tempArray[9],tempArray[10],currentCategoryuserId));
                        //System.out.println("hey3 " + activitySnapshot3.getKey()+": "+ activitySnapshot3.getValue(String.class));
                        //returning = tempArray[5];


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

                    //System.out.println("actif " + g[0]);
                    String temp[] = new String[(int) activitySnapshot.getChildrenCount()];
                    int i = 0;
                    for (DataSnapshot activitySnapshot2 : activitySnapshot.getChildren()) { //ids

                        System.out.println("actif " + activitySnapshot2.getValue());

                        temp[i] = activitySnapshot2.getValue().toString();
                        ++i;

                    }

                    currentJoyActivities.add(new ActivitiesSprint(temp[0],temp[1],temp[2],temp[3],temp[4],temp[5],temp[6]));

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

                isValid = true;

            }else{
                System.out.println("VEt " + f.getUsername() + " / " + f.getPassword());
            }
        } //end of for

        if(isValid) {

            //for (Category f : currentJoyCategories) {
                //System.out.println("porfavir " + f.endingDate);
            //}


            for(int i=0;i<currentJoyCategories.size();i++){
                System.out.println("ss2 ending" + currentJoyCategories.get(0).endingDate);
                System.out.println("ss2 gol1" + currentJoyCategories.get(0).goal1);
                System.out.println("ss2 gol2" + currentJoyCategories.get(0).goal2);
                System.out.println("ss2 gol3" + currentJoyCategories.get(0).goal3);
                System.out.println("ss2 gol4" + currentJoyCategories.get(0).goal4);
                System.out.println("ss2 numwee" + currentJoyCategories.get(0).numberOfWeeks);
                System.out.println("ss2 sprintid1" + currentJoyCategories.get(0).sprintActivityid1);
                System.out.println("ss2 sprintid2" + currentJoyCategories.get(0).sprintActivityid2);
                System.out.println("ss2 overallscoe" + currentJoyCategories.get(0).sprintOverallScore);
                System.out.println("ss2 starting" + currentJoyCategories.get(0).startingDate);
                System.out.println("ss2 starting" + currentJoyCategories.get(0).userId);
                break;
            }

            for(int i=0;i<currentJoyActivities.size();i++){

                System.out.println("ss3 gol2" + currentJoyActivities.get(i).activityScore);
                System.out.println("ss3 gol3" + currentJoyActivities.get(i).actualPoints);
                System.out.println("ss3 gol4" + currentJoyActivities.get(i).categoryId);
                System.out.println("ss3 sprintid1" + currentJoyActivities.get(i).activityName);
                System.out.println("ss3 sprintid2" + currentJoyActivities.get(i).sprintDailyPoints);
                System.out.println("ss3 overallscoe" + currentJoyActivities.get(i).targetPoints);
                System.out.println("ss3 starting" + currentJoyActivities.get(i).userId);
            }


            Intent i = new Intent(LoginActivity.this,Dashboard.class);


            //saving all data do we can use it in the next screen
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("mylist", listUsers);
            bundle.putParcelableArrayList("currentjoyactivitylist", currentJoyActivities);
            bundle.putParcelableArrayList("categoriesJoyList",currentJoyCategories);
            i.putExtras(bundle);


            i.putExtra("userNameY",name);
            i.putExtra("passwordY",pass);


            this.startActivity(i);

        }else{
            Toast.makeText(this, "Username/Password does not match, Please try again ", Toast.LENGTH_LONG).show();
            name = "";
        }

    } //end of method
} //end of class




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

    DatabaseReference databaseCategories;
    DatabaseReference databaseUsers; //our database reference object

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //getting the reference of artists node
        databaseUsers = FirebaseDatabase.getInstance().getReference("Users");

        databaseCategories = FirebaseDatabase.getInstance().getReference("Categories");

        username = (EditText) findViewById(R.id.usernameLogin);
        password = (EditText) findViewById(R.id.passwordLogin);

        signIn = (Button) findViewById(R.id.buttonSignInLogin);
        registerUser = (Button) findViewById(R.id.buttonRegisterUserLogin);

        listUsers = new ArrayList<>();
        currentJoyCategories = new ArrayList<>();

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


                    if (pass) {
                        break;
                    }

                    System.out.println("hey1 " + categorySnapshot.getValue());
                    String[] separator = categorySnapshot.getValue().toString().split(" ");

                    for (int i = separator.length - 1; i >= 0; i--) {

                        if (separator[i].length() > 15 && (separator[i].contains("userId"))) {
                            //get the userId
                            String temp = separator[i];
                            int g = temp.indexOf("=");
                            ++g;

                            //if (temp.substring(g, temp.length() - 1).equals(currentUser.id)) {
                              //  System.out.println("heyHHH  ");
                                //pass = true;
                                //break;
                            //}
                            pass = true;
                        }

                        if (pass) {
                            System.out.println("heyHHHPASS boolean  ");
                            break;
                        }
                    }

                    DataSnapshot activitiesSnapshot = categorySnapshot.child("JoySprints");
                    for (DataSnapshot activitySnapshot2 : activitiesSnapshot.getChildren()) { //ids

                        //System.out.println("hey2 " + activitySnapshot2.getKey()+": "+ activitySnapshot2.getValue(String.class));

                        String[] tempArray = new String[(int) activitySnapshot2.getChildrenCount()];
                        int i = 0;
                        for (DataSnapshot activitySnapshot3 : activitySnapshot2.getChildren()) { //branch


                            tempArray[i] = activitySnapshot3.getValue(String.class);

                            System.out.println("heyyFII " + tempArray[i]);
                            i++;

                        }

                        //currentJoycategories will have (endingDate, NumOfWeeks, sprintActid1, sprintactid2, overallscore, startingDate) for that logged-in user
                        currentJoyCategories.add(new Category(tempArray[0], tempArray[1], tempArray[2], tempArray[3], tempArray[4], tempArray[5]));
                        //System.out.println("hey3 " + activitySnapshot3.getKey()+": "+ activitySnapshot3.getValue(String.class));
                        //returning = tempArray[5];


                    }
                }

            } //end of datachangeMethod

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }


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

            Intent i = new Intent(LoginActivity.this,Dashboard.class);


            //temp
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("mylist", listUsers);
            i.putExtras(bundle);
            i.putExtra("userNameY",name);
            i.putExtra("passwordY",pass);
            i.putExtra("ending",currentJoyCategories.get(0).endingDate);
            i.putExtra("starting",currentJoyCategories.get(0).startingDate);

            this.startActivity(i);

        }else{
            Toast.makeText(this, "Username/Password does not match, Please try again ", Toast.LENGTH_LONG).show();
            name = "";
        }

    } //end of method
} //end of class




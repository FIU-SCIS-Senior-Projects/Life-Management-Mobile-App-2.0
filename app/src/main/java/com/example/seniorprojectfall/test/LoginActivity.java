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

public class LoginActivity extends AppCompatActivity {


    EditText username,password;
    Button signIn, registerUser;
    ListView listViewUsers;

    List<User> listUsers; //store all the artist from firebase database


    DatabaseReference databaseUsers; //our database reference object

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //getting the reference of artists node
        databaseUsers = FirebaseDatabase.getInstance().getReference("Users");

        username = (EditText) findViewById(R.id.usernameLogin);
        password = (EditText) findViewById(R.id.passwordLogin);

        signIn = (Button) findViewById(R.id.buttonSignInLogin);
        registerUser = (Button) findViewById(R.id.buttonRegisterUserLogin);

        listUsers = new ArrayList<>();

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
                    User artist = postSnapshot.getValue(User.class);
                    //adding artist to the list
                    listUsers.add(artist);
                }

            }

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
                Intent i = new Intent(LoginActivity.this,DashboardActivity.class);
                startActivity(i);
            }
        }


        if(!isValid) {
            Toast.makeText(this, "Username/Password does not match, Please try again ", Toast.LENGTH_LONG).show();
            name = "";
        }

    } //end of method
} //end of class




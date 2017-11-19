package com.example.natalia.lifemanagementfirst;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Natalia on 11/17/2017.
 */

public class ChatCoachSelectionActivity extends AppCompatActivity {

    private FirebaseListAdapter<ChatCoach> adapter;
    DatabaseReference databaseReferenceCoaches;
    String userId;
    String username;
    String firstname;
    String coachId;
    String coachFirstName;
    String coachLastName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_chat_coaches_selection);


        //actionBar = getSupportActionBar();
        //if(activityName != null) {
        //actionBar.setTitle(activityName);
        //}
        //actionBar.setSubtitle(activityName);
        //actionBar.setIcon(R.drawable.ic_chevron_left_black_24dp);


        databaseReferenceCoaches = FirebaseDatabase.getInstance().getReference("Coaches");
        //chatSnapshot = databaseReferenceChat.();

        Intent in = getIntent();
        userId = in.getStringExtra("userid");
        System.out.println("NAT TEST userId" + userId);
        username = in.getStringExtra("username");
        System.out.println("NAT TEST username" + username);
        firstname = in.getStringExtra("firstname");
        System.out.println("NAT TEST firstname" + firstname);

        onStart();

        // If does not work like this, put it (addValueEventListener) in a method and call this method here.
        //check if this user already has chats in Chats:
        //findUserChats(databaseReferenceChat);


        //Load chat coaches
        displayCoaches();

    }


    @Override
    protected void onStart() {
        super.onStart();
        //attaching value event listener to read from db
        databaseReferenceCoaches.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot coachSnapshot : dataSnapshot.getChildren()) { //id)
                    if (coachSnapshot.getValue() == null) {
                        break;
                    }
                    else{
                            coachId = coachSnapshot.getKey();
                            //System.out.println("NAT TEST coachId" + coachId);
                            displayCoaches();

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        //displayChatMessage();
    }

    private void displayCoaches() {
        ListView listOfCoaches = (ListView)findViewById(R.id.list_of_coaches);
        // do the following if coachId != null
        if (coachId != null) {
            adapter = new FirebaseListAdapter<ChatCoach>(this, ChatCoach.class, R.layout.list_item_chat_coach, databaseReferenceCoaches) {
                @Override
                protected void populateView(View v, ChatCoach model, int position) {
                    // Get references to the views of list_item_chat_coach.xml
                    TextView coachFirstName = (TextView) v.findViewById(R.id.coach_first_name);
                    TextView coachLastName = (TextView) v.findViewById(R.id.coach_last_name);

                    coachFirstName.setText(model.getFirstName());
                    coachLastName.setText(model.getLastName());
                }
            };
            listOfCoaches.setAdapter(adapter);
        }
        // set on list item on click listener
        listOfCoaches.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ChatCoach clickedCoach = (ChatCoach)parent.getItemAtPosition(position);
                String clickedCoachId = clickedCoach.getId();
                System.out.println("NAT TEST clickedCoachId" + clickedCoachId);
                String clickedCoachFirstName = clickedCoach.getFirstName();

                Intent i = new Intent(ChatCoachSelectionActivity.this, ChatActivity.class);
                //Save user's and clicked coach's info so that we can use it in the following Activity:
                i.putExtra("userid", userId);
                i.putExtra("username", username);
                i.putExtra("firstname", firstname);
                i.putExtra("coachid", clickedCoachId);
                i.putExtra("coachfirstname", clickedCoachFirstName);
                startActivity(i);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.chat_message, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_back:

                super.onBackPressed();
                break;
            default:
                break;
        }
        return true;
    }
}

package com.example.seniorprojectfall.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class viewCoachesActivity extends AppCompatActivity {

    ArrayAdapter<String> adapter;
    String [] elements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_coaches);


        ListView list = (ListView) findViewById(R.id.listviewViewCoaches);
        elements = new String[Dashboard.coachesList.size()];

        for(int i=0;i<elements.length;i++){

            elements[i] = "Full Name: " + Dashboard.coachesList.get(i).firstname + ", " + Dashboard.coachesList.get(i).lastname + "\n"
                    + "Email: " + Dashboard.coachesList.get(i).email + "\n" +
                    "Skills: " + Dashboard.coachesList.get(i).skills + "\n"
                    + "Rating: " + Dashboard.coachesList.get(i).rating + "\n";
        }


        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, elements);


        //temp


        list.setAdapter(adapter);
    }
}

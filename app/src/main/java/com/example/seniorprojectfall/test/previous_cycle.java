package com.example.seniorprojectfall.test;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ListView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.Toast;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.content.Intent;

import java.util.ArrayList;

public class previous_cycle extends AppCompatActivity {

    private TextView mTextMessage;
    ListView list;
    ArrayAdapter<String> adapter;
    String[] elements;
    String[] elementsHelper;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_joy:

                    populateJoy();
                    return true;
                case R.id.navigation_passion:
                    populatePassion();
                    return true;
                case R.id.navigation_contribution:
                    populateContribution();
                    return true;
            }
            return false;
        }

    };


    public void populateJoy(){


        elements = new String[Dashboard.userJoyactivitiesPrevious.size()];
        elementsHelper = new String[Dashboard.userJoyactivitiesPrevious.size()];

        for(int i=0;i<elements.length;i++){
            elements[i] = Dashboard.userJoyactivitiesPrevious.get(i).activityName + " " + Dashboard.userJoyactivitiesPrevious.get(i).categoryId
                    + " " + Dashboard.userJoyactivitiesPrevious.get(i).userId;

            elementsHelper[i] = Dashboard.userJoyactivitiesPrevious.get(i).activityName + " (" + Dashboard.userJoyactivitiesPrevious.get(i).categoryId.substring(0,2)
                    + "/" + Dashboard.userJoyactivitiesPrevious.get(i).categoryId.substring(2,4) + "/"
                    + Dashboard.userJoyactivitiesPrevious.get(i).categoryId.substring(6) + "-"
                    + " " + Dashboard.userJoyactivitiesPrevious.get(i).userId.substring(0,2) + "/"
                    + Dashboard.userJoyactivitiesPrevious.get(i).userId.substring(2,4) + "/" + Dashboard.userJoyactivitiesPrevious.get(i).userId.substring(6) + ")";
        }

        list = (ListView) findViewById(R.id.listview);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,elementsHelper);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                //Toast.makeText(getBaseContext(),parent.getItemAtPosition(i) + " is selected",Toast.LENGTH_LONG).show();
                showContentJoy(i);
            }
        });

    }


    public void populatePassion(){


        elements = new String[Dashboard.userPassionactivitiesPrevious.size()];
        elementsHelper = new String[Dashboard.userPassionactivitiesPrevious.size()];

        for(int i=0;i<elements.length;i++){
            elements[i] = Dashboard.userPassionactivitiesPrevious.get(i).activityName + " " + Dashboard.userPassionactivitiesPrevious.get(i).categoryId
                    + " " + Dashboard.userPassionactivitiesPrevious.get(i).userId;

            elementsHelper[i] = Dashboard.userPassionactivitiesPrevious.get(i).activityName + " (" + Dashboard.userPassionactivitiesPrevious.get(i).categoryId.substring(0,2)
                    + "/" + Dashboard.userPassionactivitiesPrevious.get(i).categoryId.substring(2,4) + "/"
                    + Dashboard.userPassionactivitiesPrevious.get(i).categoryId.substring(6) + "-"
                    + " " + Dashboard.userPassionactivitiesPrevious.get(i).userId.substring(0,2) + "/"
                    + Dashboard.userPassionactivitiesPrevious.get(i).userId.substring(2,4) + "/" + Dashboard.userPassionactivitiesPrevious.get(i).userId.substring(6) + ")";
        }

        list = (ListView) findViewById(R.id.listview);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,elementsHelper);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                //Toast.makeText(getBaseContext(),parent.getItemAtPosition(i) + " is selected",Toast.LENGTH_LONG).show();
                showContentPassion(i);
            }
        });


    }

    public void populateContribution(){

        mTextMessage = (TextView) findViewById(R.id.textViewcycle_title);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        elements = new String[Dashboard.userContributionactivitiesPrevious.size()];
        elementsHelper = new String[Dashboard.userContributionactivitiesPrevious.size()];

        for(int i=0;i<elements.length;i++){
            elements[i] = Dashboard.userContributionactivitiesPrevious.get(i).activityName + " " + Dashboard.userContributionactivitiesPrevious.get(i).categoryId
                    + " " + Dashboard.userContributionactivitiesPrevious.get(i).userId;

            elementsHelper[i] = Dashboard.userContributionactivitiesPrevious.get(i).activityName + " (" + Dashboard.userContributionactivitiesPrevious.get(i).categoryId.substring(0,2)
                    + "/" + Dashboard.userContributionactivitiesPrevious.get(i).categoryId.substring(2,4) + "/"
                    + Dashboard.userContributionactivitiesPrevious.get(i).categoryId.substring(6) + "-"
                    + " " + Dashboard.userContributionactivitiesPrevious.get(i).userId.substring(0,2) + "/"
                    + Dashboard.userContributionactivitiesPrevious.get(i).userId.substring(2,4) + "/" + Dashboard.userContributionactivitiesPrevious.get(i).userId.substring(6) + ")";
        }

        list = (ListView) findViewById(R.id.listview);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,elementsHelper);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                //Toast.makeText(getBaseContext(),parent.getItemAtPosition(i) + " is selected",Toast.LENGTH_LONG).show();
                showContentContribution(i);
            }
        });


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_cycle2);

        mTextMessage = (TextView) findViewById(R.id.textViewcycle_title);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        elements = new String[Dashboard.userJoyactivitiesPrevious.size()];
        elementsHelper = new String[Dashboard.userJoyactivitiesPrevious.size()];

        for(int i=0;i<elements.length;i++){
            elements[i] = Dashboard.userJoyactivitiesPrevious.get(i).activityName + " " + Dashboard.userJoyactivitiesPrevious.get(i).categoryId
                    + " " + Dashboard.userJoyactivitiesPrevious.get(i).userId;

            elementsHelper[i] = Dashboard.userJoyactivitiesPrevious.get(i).activityName + " (" + Dashboard.userJoyactivitiesPrevious.get(i).categoryId.substring(0,2)
                    + "/" + Dashboard.userJoyactivitiesPrevious.get(i).categoryId.substring(2,4) + "/"
                    + Dashboard.userJoyactivitiesPrevious.get(i).categoryId.substring(6) + "-"
                    + " " + Dashboard.userJoyactivitiesPrevious.get(i).userId.substring(0,2) + "/"
                    + Dashboard.userJoyactivitiesPrevious.get(i).userId.substring(2,4) + "/" + Dashboard.userJoyactivitiesPrevious.get(i).userId.substring(6) + ")";
        }

        list = (ListView) findViewById(R.id.listview);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,elementsHelper);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                //Toast.makeText(getBaseContext(),parent.getItemAtPosition(i) + " is selected",Toast.LENGTH_LONG).show();
                showContentJoy(i);
            }
        });

        ImageButton goBack = (ImageButton) findViewById(R.id.imageButton_Cycle);

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();

    }


    //JOY
    public void showContentJoy(int i){

        String element = "";
        String[] elementsplitter = elements[i].split(" ");
        System.out.println("this is the elementsplitter " + elementsplitter[0]);
        System.out.println("this is the element fella " + elements[i]);

        int size = Dashboard.userJoyactivitiesPrevious.size();
        for(int k=0;k<size;k++){

            System.out.println("whatnow1 " + Dashboard.userJoyactivitiesPrevious.get(k).activityName);
            System.out.println("whatnow2 " + Dashboard.userJoyactivitiesPrevious.get(k).categoryId);
            System.out.println("whatnow3 " + Dashboard.userJoyactivitiesPrevious.get(k).userId);

            if(Dashboard.userJoyactivitiesPrevious.get(k).activityName.contains(elementsplitter[0]) &&
                    Dashboard.userJoyactivitiesPrevious.get(k).categoryId.contains(elementsplitter[1]) &&
                    Dashboard.userJoyactivitiesPrevious.get(k).userId.contains(elementsplitter[2])){

                element = Dashboard.userJoyactivitiesPrevious.get(k).activityScore + " "
                        + Dashboard.userJoyactivitiesPrevious.get(k).actualPoints + " "
                        + Dashboard.userJoyactivitiesPrevious.get(k).categoryId + " "
                        + Dashboard.userJoyactivitiesPrevious.get(k).activityName + " "
                        + Dashboard.userJoyactivitiesPrevious.get(k).sprintDailyPoints + " "
                        + Dashboard.userJoyactivitiesPrevious.get(k).targetPoints + " "
                        + Dashboard.userJoyactivitiesPrevious.get(k).userId;
                        break;
            }
        }

        System.out.println("elementafter " + element);

        Intent k = new Intent(previous_cycle.this,cycleActivity.class);


        //saving all data do we can use it in the next screen
        Bundle bundle = new Bundle();

        k.putExtras(bundle);
        k.putExtra("theelement",element);

        this.startActivity(k);
    }

    //PASSION
    public void showContentPassion(int i){

        String element = "";
        String[] elementsplitter = elements[i].split(" ");
        System.out.println("this is the elementsplitter passion " + elementsplitter[0]);
        System.out.println("this is the element fella passion " + elements[i]);

        int size = Dashboard.userPassionactivitiesPrevious.size();
        for(int k=0;k<size;k++){

            System.out.println("whatnow1 " + Dashboard.userPassionactivitiesPrevious.get(k).activityName);
            System.out.println("whatnow2 " + Dashboard.userPassionactivitiesPrevious.get(k).categoryId);
            System.out.println("whatnow3 " + Dashboard.userPassionactivitiesPrevious.get(k).userId);

            if(Dashboard.userPassionactivitiesPrevious.get(k).activityName.contains(elementsplitter[0]) &&
                    Dashboard.userPassionactivitiesPrevious.get(k).categoryId.contains(elementsplitter[1]) &&
                    Dashboard.userPassionactivitiesPrevious.get(k).userId.contains(elementsplitter[2])){

                element = Dashboard.userPassionactivitiesPrevious.get(k).activityScore + " "
                        + Dashboard.userPassionactivitiesPrevious.get(k).actualPoints + " "
                        + Dashboard.userPassionactivitiesPrevious.get(k).categoryId + " "
                        + Dashboard.userPassionactivitiesPrevious.get(k).activityName + " "
                        + Dashboard.userPassionactivitiesPrevious.get(k).sprintDailyPoints + " "
                        + Dashboard.userPassionactivitiesPrevious.get(k).targetPoints + " "
                        + Dashboard.userPassionactivitiesPrevious.get(k).userId;
                break;
            }
        }

        System.out.println("elementafter passion " + element);

        Intent k = new Intent(previous_cycle.this,cycleActivityPassion.class);


        //saving all data do we can use it in the next screen
        Bundle bundle = new Bundle();

        k.putExtras(bundle);
        k.putExtra("theelementpassion",element);

        this.startActivity(k);
    }

    //CONTRIBUTION

    public void showContentContribution(int i){

        String element = "";
        String[] elementsplitter = elements[i].split(" ");
        System.out.println("this is the elementsplitter contribution " + elementsplitter[0]);
        System.out.println("this is the element fella contribution " + elements[i]);

        int size = Dashboard.userContributionactivitiesPrevious.size();
        for(int k=0;k<size;k++){

            System.out.println("whatnow1 " + Dashboard.userContributionactivitiesPrevious.get(k).activityName);
            System.out.println("whatnow2 " + Dashboard.userContributionactivitiesPrevious.get(k).categoryId);
            System.out.println("whatnow3 " + Dashboard.userContributionactivitiesPrevious.get(k).userId);

            if(Dashboard.userContributionactivitiesPrevious.get(k).activityName.contains(elementsplitter[0]) &&
                    Dashboard.userContributionactivitiesPrevious.get(k).categoryId.contains(elementsplitter[1]) &&
                    Dashboard.userContributionactivitiesPrevious.get(k).userId.contains(elementsplitter[2])){

                element = Dashboard.userContributionactivitiesPrevious.get(k).activityScore + " "
                        + Dashboard.userContributionactivitiesPrevious.get(k).actualPoints + " "
                        + Dashboard.userContributionactivitiesPrevious.get(k).categoryId + " "
                        + Dashboard.userContributionactivitiesPrevious.get(k).activityName + " "
                        + Dashboard.userContributionactivitiesPrevious.get(k).sprintDailyPoints + " "
                        + Dashboard.userContributionactivitiesPrevious.get(k).targetPoints + " "
                        + Dashboard.userContributionactivitiesPrevious.get(k).userId;
                break;
            }
        }

        System.out.println("elementafter contribution " + element);

        Intent k = new Intent(previous_cycle.this,cycleActivityContribution.class);


        //saving all data do we can use it in the next screen
        Bundle bundle = new Bundle();

        k.putExtras(bundle);
        k.putExtra("theelementcontribution",element);

        this.startActivity(k);
    }



}


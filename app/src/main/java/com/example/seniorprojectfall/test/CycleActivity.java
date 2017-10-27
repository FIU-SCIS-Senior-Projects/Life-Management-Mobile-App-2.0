package com.example.seniorprojectfall.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class CycleActivity extends AppCompatActivity {

    ListView list;
    ArrayAdapter<String> adapter;
    String[] elements = {"hello1","heyy","fsdfds","gggg"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cycle);

        list = (ListView) findViewById(R.id.listview);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,elements);
        list.setAdapter(adapter);

       list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               Toast.makeText(getBaseContext(),adapterView.getItemAtPosition(i) + " is selected",Toast.LENGTH_LONG).show();
               System.out.println("this is i fella " + i + " and longl " + l);

               goToPreviousCycle(i);
           }
       });

    }

    public void goToPreviousCycle(int i){

        String element = elements[i];
        System.out.println("this is the element fella " + element);

        Intent k = new Intent(CycleActivity.this,previousCycle.class);


        //saving all data do we can use it in the next screen
        Bundle bundle = new Bundle();

        k.putExtras(bundle);
        k.putExtra("theelement",element);

        this.startActivity(k);
    }
}

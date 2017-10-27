package com.example.seniorprojectfall.test;

import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

public class previousCycle extends AppCompatActivity {


    static String element_static;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_cycle);


        Intent in = getIntent();
        element_static = in.getExtras().getString("theelement");

        TextView g = (TextView) findViewById(R.id.textView3);
        g.setText(element_static + "yeyyeyy");

        Button btn = (Button) findViewById(R.id.buttonleave);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackPressed();
                    onBackPressed();

                }
            });

    }


    @Override
    public void onBackPressed() {

            super.onBackPressed();

    }
}

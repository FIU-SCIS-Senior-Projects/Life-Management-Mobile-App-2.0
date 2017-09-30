package com.example.seniorprojectfall.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;

public class ThirdScreenActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_screen);

    }

    public void onArrowClick(View view) {
        Intent intent = new Intent(this, activity_fourth_screen.class);
        startActivity(intent);
    }

    //zoom animation for next (screen3)
    // Animation zoomAnimation = AnimationUtils.loadAnimation(this,R.anim.zoom);
    //nextBtnScreen3.startAnimation(zoomAnimation);

}


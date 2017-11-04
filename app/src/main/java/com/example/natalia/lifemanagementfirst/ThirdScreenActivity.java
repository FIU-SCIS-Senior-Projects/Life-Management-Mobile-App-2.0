package com.example.natalia.lifemanagementfirst;

/**
 * Created by Natalia
 */
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ThirdScreenActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_screen);
    }

    public void onArrowClick(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);


    }
}


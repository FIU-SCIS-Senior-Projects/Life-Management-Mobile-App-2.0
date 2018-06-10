package com.example.natalia.lifemanagementfirst;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Scanner;

/**
 * Created by Natalia on 1/18/2018.
 */

public class SettingsActivity extends AppCompatActivity {

    private User currentUser;
    private String currentUserId;
    private DatabaseReference databaseReferenceUser;
    private EditText settingsFirstName;
    private String settingsFname;
    private EditText settingsLastName;
    private String reverseFname = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        databaseReferenceUser = FirebaseDatabase.getInstance().getReference("Users");

        Intent in = getIntent();
        currentUserId = in.getStringExtra("userid");
        System.out.println("NAT TEST userId" + currentUserId);

        settingsFirstName = (EditText)findViewById(R.id.settings_firstname);
        settingsLastName = (EditText)findViewById(R.id.settings_lastname);
        //settingsFname = settingsFirstName.getText().toString();
        //System.out.println("NAT TEST SETTINGS " + settingsFname);

        //EditText settingsLastName = (EditText)findViewById(R.id.settings_lastname);

        int[] a = new int[]{2,3,4,3,6};
        //boolean arSidesSum = arraySidesSum(a);
        System.out.println("NAT TEST ARRAY SIDES SUM " + arraySidesSum(a));
        System.out.println("NAT TEST ARRAY MULTIPLES SUM " + multiplesSum(a));

        int[] b = new int[]{3,3};
        System.out.println("NAT TEST ARRAY SIDES SUM " + arraySidesSum(b));
        System.out.println("NAT TEST ARRAY MULTIPLES SUM " + multiplesSum(b));

        b = new int[]{3,4,5,7,15,18,12,19,25};
        System.out.println("NAT TEST ARRAY SIDES SUM " + arraySidesSum(b));
        System.out.println("NAT TEST ARRAY MULTIPLES SUM " + multiplesSum(b));

        b = new int[]{2,3,4,3,3};
        System.out.println("NAT TEST ARRAY SIDES SUM " + arraySidesSum(b));

        Button btnSubmitProfileSetting = (Button)findViewById(R.id.buttonSubmitProfileSettings);
        btnSubmitProfileSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitProfileSettings();
            }
        });

    }

    // Method to take user input typed in first name field, reverse each substring, and set the result in last name field
    public void submitProfileSettings(){

        settingsFname = settingsFirstName.getText().toString();


        int j = 0; // to store index for space char
        int k = 0; // to store index of first char of substring to reverse
        for (int i=0; i < settingsFname.length(); i++){
            //Search for space and reverse substring
            if (settingsFname.charAt(i) ==  ' '){
                k = j;
                j = i;
                reverseFname += reverseString(settingsFname.substring(k,j)); // k as start index(inclusive), j as end index(exclusive)
                j++; // to point to the next chat after the just found space
            }
            //Or search for end of the string and reverse substring
            else if (i == settingsFname.length() - 1){
                k = j;
                reverseFname += reverseString(settingsFname.substring(k));
            }
        }

        //EditText settingsLastName = (EditText)findViewById(R.id.settings_lastname);
        settingsLastName.setText(reverseFname);
        System.out.println("NAT TEST SETTINGS " + reverseFname);

    }

    // Alternative method to take user input typed in first name field, reverse each substring, and set the result in last name field
    public void submitProfileSettings2(){
        settingsFname = settingsFirstName.getText().toString();
        Scanner scanner = new Scanner(settingsFname);
        while(scanner.hasNext()){
            String str = scanner.next();
            reverseFname += reverseString(str);
        }

        settingsLastName.setText(reverseFname);
        System.out.println("NAT TEST SETTINGS " + reverseFname);

    }

    // Method to reverse input string and return the resulting reversed string
    private static String reverseString(String str){
        String reverseStr = "";
        for (int i = str.length() - 1; i >= 0 ; i--){
            reverseStr += str.charAt(i);
        }
        return reverseStr + " ";
    }

    // Method to check if there is any way to sum up left and right sides of the array so that sums are equal (any way to divide array in such a way so that sums
    // of left and right sides of the array are equal. Assumes that input array contains only positive numbers; returns sum if it's possible to divide array, -1 otherwise.
    // If there exist negative numbers in the input array, order/sort array first (insertSort, mergeSort, quickSort)??? (or do look one step ahead before moving indexes?)
    private static int arraySidesSum(int[] a){
    int i = 0;
    int j = a.length - 1;
    int leftSum = a[i];
    int rightSum = a[j];
    //for(i=0,j=a.length - 1; i < j; i++, j--){

    //}

    while ((i < j) && ((j-i) > 1)){
        if(leftSum <= rightSum){
            i++;
            leftSum+= a[i];
        }
        else{
            j--;
            rightSum+= a[j];
        }
    }


    if(leftSum == rightSum)
        return leftSum;
    else
        return -1;


    //return (leftSum == rightSum);
    }

    // Method to search input array for the numbers that are multiples of 3 or 5, and returns sum of those numbers
    private static int multiplesSum(int[] a){
        int sum = 0;
        for (int i = 0; i < a.length; i++){
            if (((a[i] % 3) == 0) || ((a[i] % 5) == 0)){
                sum += a[i];
            }
        }
        return sum;
    }
}

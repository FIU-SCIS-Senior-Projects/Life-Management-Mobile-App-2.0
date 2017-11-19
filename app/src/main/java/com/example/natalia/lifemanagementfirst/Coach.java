package com.example.natalia.lifemanagementfirst;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by lazaro on 11/13/17.
 */

public class Coach implements Parcelable{
    public String email;
    public String firstname;
    public String id;
    public String lastname;
    public String rating;
    public String skills;

    public Coach(String skills, String firstname, String lastname, String rating, String id, String email) {
        this.skills = skills;
        this.firstname = firstname;
        this.lastname = lastname;
        this.rating = rating;
        this.id = id;
        this.email = email;
    }

    public Coach(){
        skills = "";
        firstname = "";
        lastname = "";
        rating = "";
        id = "";
        email = "";
    }

    public Coach(Parcel in){

        this.skills = in.readString();
        this.firstname = in.readString();
        this.lastname = in.readString();
        this.rating = in.readString();
        this.id = in.readString();
        this.email = in.readString();

    }


    public static final Creator<Coach> CREATOR = new Creator<Coach>() {
        @Override
        public Coach createFromParcel(Parcel in) {
            return new Coach(in);
        }

        @Override
        public Coach[] newArray(int size) {
            return new Coach[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {

        parcel.writeString(skills);
        parcel.writeString(firstname);
        parcel.writeString(lastname);
        parcel.writeString(rating);
        parcel.writeString(id);
        parcel.writeString(email);

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

}

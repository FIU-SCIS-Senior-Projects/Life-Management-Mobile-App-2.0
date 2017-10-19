package com.example.natalia.lifemanagementfirst;

/**
 * Created by Natalia on 10/17/2017.
 */

import android.os.Parcel;
import android.os.Parcelable;

public class ContributionSprint implements Parcelable{

    String categoryId;
    String endingDate;
    String goal1;
    String goal2;
    String goal3;
    String goal4;
    String numberOfWeeks;
    String sprintActivityId1;
    String sprintActivityId2;
    String sprintOverallScore;
    String startingDate;
    String userId;



    public ContributionSprint(){

        categoryId = "";
        endingDate = "";
        goal1= "";
        goal2= "";
        goal3= "";
        goal4= "";
        numberOfWeeks= "";
        sprintActivityId1= "";
        sprintActivityId2= "";
        sprintOverallScore= "";
        startingDate= "";
        userId = "";

    }

    public ContributionSprint(String categoryid, String endingDate, String goal1, String goal2, String goal3, String goal4, String numberOfWeeks, String sprintActivityid1,
                    String sprintActivityid2, String sprintOverallScore, String startingDate, String userId) {
        this.categoryId = categoryid;
        this.endingDate = endingDate;
        this.goal1 = goal1;
        this.goal2 = goal2;
        this.goal3 = goal3;
        this.goal4 = goal4;
        this.numberOfWeeks = numberOfWeeks;
        this.sprintActivityId1 = sprintActivityid1;
        this.sprintActivityId2 = sprintActivityid2;
        this.sprintOverallScore = sprintOverallScore;
        this.startingDate = startingDate;
        this.userId = userId;

    }

    public ContributionSprint(Parcel in){

        this.categoryId = in.readString();
        this.endingDate = in.readString();
        this.goal1 = in.readString();
        this.goal2 = in.readString();
        this.goal3 = in.readString();
        this.goal4 = in.readString();
        this.numberOfWeeks = in.readString();
        this.sprintActivityId1 = in.readString();
        this.sprintActivityId2 = in.readString();
        this.sprintOverallScore = in.readString();
        this.startingDate = in.readString();
        this.userId = in.readString();
    }

    public String getCategoryid() {
        return categoryId;
    }

    public void setCategoryid(String categoryid) {
        this.categoryId = categoryid;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGoal1() {
        return goal1;
    }

    public void setGoal1(String goal1) {
        this.goal1 = goal1;
    }

    public String getGoal2() {
        return goal2;
    }

    public void setGoal2(String goal2) {
        this.goal2 = goal2;
    }

    public String getGoal3() {
        return goal3;
    }

    public void setGoal3(String goal3) {
        this.goal3 = goal3;
    }

    public String getGoal4() {
        return goal4;
    }

    public void setGoal4(String goal4) {
        this.goal4 = goal4;
    }

    public String getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(String endingDate) {
        this.endingDate = endingDate;
    }

    public String getNumberOfWeeks() {
        return numberOfWeeks;
    }

    public void setNumberOfWeeks(String numberOfWeeks) {
        this.numberOfWeeks = numberOfWeeks;
    }

    public String getSprintActivityid1() {
        return sprintActivityId1;
    }

    public void setSprintActivityid1(String sprintActivityid1) {
        this.sprintActivityId1 = sprintActivityid1;
    }

    public String getSprintActivityid2() {
        return sprintActivityId2;
    }

    public void setSprintActivityid2(String sprintActivityid2) {
        this.sprintActivityId2 = sprintActivityid2;
    }

    public String getSprintOverallScore() {
        return sprintOverallScore;
    }

    public void setSprintOverallScore(String sprintOverallScore) {
        this.sprintOverallScore = sprintOverallScore;
    }

    public String getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(String startingDate) {
        this.startingDate = startingDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeString(categoryId);
        parcel.writeString(endingDate);
        parcel.writeString(goal1);
        parcel.writeString(goal2);
        parcel.writeString(goal3);
        parcel.writeString(goal4);
        parcel.writeString(numberOfWeeks);
        parcel.writeString(sprintActivityId1);
        parcel.writeString(sprintActivityId2);
        parcel.writeString(sprintOverallScore);
        parcel.writeString(startingDate);
        parcel.writeString(userId);
    }

    public static final Parcelable.Creator<ContributionSprint> CREATOR = new Parcelable.Creator<ContributionSprint>() {
        public ContributionSprint createFromParcel(Parcel in) {
            return new ContributionSprint(in);
        }

        @Override
        public ContributionSprint[] newArray(int i) {
            return new ContributionSprint[i];
        }
    };

}

package com.example.seniorprojectfall.test;

import java.util.*;

public class Sprint {

    String nameOfSprint;
    String activityid1;
    String activityid2;
    String activityid3;
    String endingDate;
    int numberOfWeeks;
    int sprintOverallScore;
    String startingDate;


    public Sprint() {

    }

    public Sprint(String nameOfSprint, String activityid1, String activityid2, String activityid3, String endingDate, int numberOfWeeks, int sprintOverallScore, String startingDate) {
        this.nameOfSprint = nameOfSprint;
        this.activityid1 = activityid1;
        this.activityid2 = activityid2;
        this.activityid3 = activityid3;
        this.endingDate = endingDate;
        this.numberOfWeeks = numberOfWeeks;
        this.sprintOverallScore = sprintOverallScore;
        this.startingDate = startingDate;
    }

    public String getNameOfSprint() {
        return nameOfSprint;
    }

    public void setNameOfSprint(String nameOfSprint) {
        this.nameOfSprint = nameOfSprint;
    }

    public String getActivityid1() {
        return activityid1;
    }

    public void setActivityid1(String activityid1) {
        this.activityid1 = activityid1;
    }

    public String getActivityid2() {
        return activityid2;
    }

    public void setActivityid2(String activityid2) {
        this.activityid2 = activityid2;
    }

    public String getActivityid3() {
        return activityid3;
    }

    public void setActivityid3(String activityid3) {
        this.activityid3 = activityid3;
    }

    public String getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(String endingDate) {
        this.endingDate = endingDate;
    }

    public int getNumberOfWeeks() {
        return numberOfWeeks;
    }

    public void setNumberOfWeeks(int numberOfWeeks) {
        this.numberOfWeeks = numberOfWeeks;
    }

    public int getSprintOverallScore() {
        return sprintOverallScore;
    }

    public void setSprintOverallScore(int sprintOverallScore) {
        this.sprintOverallScore = sprintOverallScore;
    }

    public String getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(String startingDate) {
        this.startingDate = startingDate;
    }

}
package com.example.seniorprojectfall.test;

import java.util.*;

public class Category {


    String endingDate;
    String numberOfWeeks;
    String sprintActivityid1;
    String sprintActivityid2;
    String sprintOverallScore;
    String startingDate;




    public Category(){

    }

    public Category(String endingDate, String numberOfWeeks, String sprintActivityid1, String sprintActivityid2, String sprintOverallScore, String startingDate) {
        this.endingDate = endingDate;
        this.numberOfWeeks = numberOfWeeks;
        this.sprintActivityid1 = sprintActivityid1;
        this.sprintActivityid2 = sprintActivityid2;
        this.sprintOverallScore = sprintOverallScore;
        this.startingDate = startingDate;
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
        return sprintActivityid1;
    }

    public void setSprintActivityid1(String sprintActivityid1) {
        this.sprintActivityid1 = sprintActivityid1;
    }

    public String getSprintActivityid2() {
        return sprintActivityid2;
    }

    public void setSprintActivityid2(String sprintActivityid2) {
        this.sprintActivityid2 = sprintActivityid2;
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
}


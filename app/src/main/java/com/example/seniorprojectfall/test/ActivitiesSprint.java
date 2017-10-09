package com.example.seniorprojectfall.test;

/**
 * Created by lazaro on 10/5/17.
 */


public class ActivitiesSprint {

    String id1;
    String id2;
    String id3;


    public ActivitiesSprint(){

    }

    public ActivitiesSprint(String id1, String id2, String id3) {
        this.id1 = id1;
        this.id2 = id2;
        this.id3 = id3;
    }

    public String getId1() {
        return id1;
    }

    public void setId1(String id1) {
        this.id1 = id1;
    }

    public String getId2() {
        return id2;
    }

    public void setId2(String id2) {
        this.id2 = id2;
    }

    public String getId3() {
        return id3;
    }

    public void setId3(String id3) {
        this.id3 = id3;
    }
}

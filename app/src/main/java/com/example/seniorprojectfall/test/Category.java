package com.example.seniorprojectfall.test;

import java.util.*;

public class Category {


    String name;
    Category c1;
    Category c2;
    Category c3;



    public Category(){

    }

    public Category(Category c1, Category c2, Category c3, String n) {
        this.c1 = c1;
        this.c2 = c2;
        this.c3 = c3;
        this.name = n;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getC1() {
        return c1;
    }

    public void setC1(Category c1) {
        this.c1 = c1;
    }

    public Category getC2() {
        return c2;
    }

    public void setC2(Category c2) {
        this.c2 = c2;
    }

    public Category getC3() {
        return c3;
    }

    public void setC3(Category c3) {
        this.c3 = c3;
    }
}


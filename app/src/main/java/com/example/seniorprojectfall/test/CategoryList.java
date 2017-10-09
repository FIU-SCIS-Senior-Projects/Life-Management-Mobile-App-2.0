package com.example.seniorprojectfall.test;

import java.util.ArrayList;

/**
 * Created by lazaro on 10/5/17.
 */

public class CategoryList {

    private ArrayList<Category> list;

    public CategoryList(){

    }

    public CategoryList(ArrayList<Category> t){
        this.list = t;
    }

    public ArrayList<Category> getList() {
        return list;
    }

    public void setList(ArrayList<Category> list) {
        this.list = list;
    }
}

package com.example.natalia.lifemanagementfirst;

/**
 * Created by Natalia on 10/10/2017.
 */

import java.util.ArrayList;


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

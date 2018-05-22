package com.example.seniorprojectfall.test;

public class ActGivingback {
    private String name;
    private int imageSource;


    public ActGivingback(String name, int imageSource) {
        this.name = name;
        this.imageSource = imageSource;
    }

    public String getName() {
        return name;
    }

    public void setName(String nm) {
        name = nm;
    }

    public int getImageSource() {
        return imageSource;
    }

    public void setImageSource(int img) {
        imageSource = img;
    }
}


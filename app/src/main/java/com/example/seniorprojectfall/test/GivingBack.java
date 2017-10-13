package com.example.seniorprojectfall.test;

/**
 * Created by lazaro on 10/12/17.
 */

public class GivingBack {


        private String name;
        private int imageSource;


        public GivingBack(String name, int imageSource) {
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


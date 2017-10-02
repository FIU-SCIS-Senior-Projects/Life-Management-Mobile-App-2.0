package com.example.natalia.lifemanagementfirst;

/**
 * Created by lazaro on 9/17/17.
 */

public class User {

    //String id;
    String email;
    String username;
    String password;
    String firstName;
    String lastName;
    String dob;
    boolean coach;
    boolean admin;




    public User(){

    }

    public User(String email, String username, String firstName, String lastName, String dob, String password, boolean admin, boolean coach) {
        this.email = email;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.coach = coach;
        this.admin = admin;
        this.password = password;
    }


    public boolean isCoach() {
        return coach;
    }

    public void setCoach(boolean coach) {
        this.coach = coach;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }


}

package com.example.natalia.lifemanagementfirst;

/**
 * Created by Natalia on 11/18/2017.
 */

public class ChatCoach {
    private String email;
    private String firstName;
    private String id;
    private String lastName;
    private String rating;
    private String skills;

    public ChatCoach(String email, String firstName, String id, String lastName, String rating, String skills) {
        this.email = email;
        this.firstName = firstName;
        this.id = id;
        this.lastName = lastName;
        this.rating = rating;
        this.skills = skills;
    }

    public ChatCoach() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }
}

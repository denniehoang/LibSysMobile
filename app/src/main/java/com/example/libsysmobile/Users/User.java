package com.example.libsysmobile.Users;

public class User {
    private int userType;
    private String firstName;
    private String lastName;

    public User(String firstName, String lastName, int userType) {
        this.userType = userType;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getUserType() {
        return userType;
    }

    public String getUserName() {
        return firstName + " " + lastName;
    }
}

package com.example.libsysmobile;

import java.util.ArrayList;

public class User {

    public ArrayList<Rental> currentRentalsList;
    private String memberID;
    public double feesOwed;
    public String userLevel;

    public User(String memberID) {
        this.memberID = memberID;
        currentRentalsList = new ArrayList<>();
    }

    public User(String memberID, String userLevel) {
        this.memberID = memberID;
        this.userLevel = userLevel;
        currentRentalsList = new ArrayList<>();

    }

    public String getMemberID() {
        return memberID;
    }

    public void addRental(Rental rental) {
        currentRentalsList.add(rental);
    }
}

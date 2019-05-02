package com.example.libsysmobile;

import java.util.ArrayList;

public class User {

    public ArrayList<Item> currentRentalsList;
    private String memberID;

    public User(String memberID) {
        this.memberID = memberID;
        currentRentalsList = new ArrayList<>();
    }

    public String getMemberID() {
        return memberID;
    }

    public void addRental(Item item) {
        currentRentalsList.add(item);
    }
}

package com.example.libsysmobile;


import java.util.Date;

public class Rental {
    public int loanID;
    public int instanceItemID;
    public Date dueDate;
    public int itemID;
    public String title;

    public Rental(int loanID, int instanceItemID, Date dueDate) {
        this.loanID = loanID;
        this.instanceItemID = instanceItemID;
        this.dueDate = dueDate;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}

package com.example.libsysmobile.user_home_pages;

import android.os.Bundle;
import android.view.View;

import com.example.libsysmobile.R;
import com.example.libsysmobile.Rental;
import com.example.libsysmobile.pages.BrowseBookPage;
import com.example.libsysmobile.pages.GetRentalTitlePage;
import com.example.libsysmobile.pages.Page;
import com.example.libsysmobile.pages.SearchBookPage;
import com.example.libsysmobile.pages.ViewAccountPage;
import com.example.libsysmobile.queries.DbQuery;
import com.example.libsysmobile.queries.QueryCurrentRentals;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class MemberHomePage extends Page {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page_member);
        getUserInfo(currentUser.getMemberID());
    }

    public void searchBookOnClick(View view) {
        changePage(this, SearchBookPage.class);
    }

    public void browseOnClick(View view) {
        changePage(this, BrowseBookPage.class);
    }

    public void extendOnClick(View view) {
        changePage(this, GetRentalTitlePage.class);
    }

    public void myAccountOnClick(View view) {
        changePage(this, ViewAccountPage.class);
    }

    public void getUserInfo(String memberID) {
        updateRentals(memberID);
    }

    public void updateRentals(String memberID) {
        DbQuery queryCurrentRentals = new QueryCurrentRentals(this);
        queryCurrentRentals.delegate = this;
        String id = String.valueOf(memberID);
        queryCurrentRentals.execute(id);
    }

    @Override
    public void processFinish(JSONObject result) throws JSONException {
        String r = result.toString();
        JSONArray itemsArray = result.getJSONArray("data");
        for (int i = 0; i < itemsArray.length(); i++) {
            JSONObject rental = itemsArray.getJSONObject(i);
            int loanID = rental.getInt("loan_ID");
            int itemInstanceID = rental.getInt("item_instance_ID");
            long due = rental.getLong("due_date");
            Date dueDate = new Date(due);
            Rental rent = new Rental(loanID, itemInstanceID, dueDate);
            currentUser.currentRentalsList.add(rent);
        }
    }


}

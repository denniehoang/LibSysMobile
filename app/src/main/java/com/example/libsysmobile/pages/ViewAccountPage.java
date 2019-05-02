package com.example.libsysmobile.pages;

import android.os.Bundle;

import com.example.libsysmobile.queries.DbQuery;
import com.example.libsysmobile.queries.QueryGetFine;

import org.json.JSONException;
import org.json.JSONObject;

public class ViewAccountPage extends Page {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //   super.newPage(R.layout.search_for_book);
        runQuery(currentUser.getMemberID());
    }

    public void runQuery(String memberID) {
        DbQuery queryGetFine = new QueryGetFine(this);
        queryGetFine.delegate = this;
        queryGetFine.execute(memberID);
    }


    @Override
    public void processFinish(JSONObject result) throws JSONException {
        String response = result.toString();
        double fine = result.getDouble("sum");
        currentUser.feesOwed = fine;
    }
}




package com.example.libsysmobile.pages;

import android.os.Bundle;

import com.example.libsysmobile.Rental;
import com.example.libsysmobile.queries.DbQuery;
import com.example.libsysmobile.queries.QueryItemID;

import org.json.JSONException;
import org.json.JSONObject;

/*
Testing getting Item ID using instance ID
 */
public class GetRentalTitlePage extends Page {

    private int index = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        for (Rental rental : currentUser.currentRentalsList) {
            runQuery(String.valueOf(rental.instanceItemID));
        }
    }

    public void runQuery(String itemInstanceID) {
        DbQuery queryItemID = new QueryItemID(this);
        queryItemID.delegate = this;
        queryItemID.execute(itemInstanceID);
    }

    @Override
    public void processFinish(JSONObject result) throws JSONException {
        String response = result.toString();
        //   JSONObject title = result.get("data.title");
        //Log.d("title", title);
        index++;
        if (index == currentUser.currentRentalsList.size()) {
            changePage(this, ExtendRentalPage.class);
        }


    }

}

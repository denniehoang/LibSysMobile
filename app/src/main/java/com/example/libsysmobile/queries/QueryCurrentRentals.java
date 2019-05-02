package com.example.libsysmobile.queries;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

public class QueryCurrentRentals extends DbQuery {
    private String apiUrl = mainURL + "/loans/by/memberID";

    public QueryCurrentRentals(Context context) {
        super(context);
    }

    // Displays process dialog to user indicating process in progress
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        super.runProgressDialog("Searching for rentals...");
    }

    // Runs php script to database
    @Override
    protected String doInBackground(String... params) {
        String memberID = params[0];
        try {
            super.connect(apiUrl, "POST", true, true, createPostData(memberID));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Creating POST data to be sent to the php
    private JSONObject createPostData(String memberID) throws JSONException {
        JSONObject postData = new JSONObject();
        postData.put("memberID", memberID);
        return postData;
    }
}

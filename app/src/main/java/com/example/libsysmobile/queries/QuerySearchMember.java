package com.example.libsysmobile.queries;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

public class QuerySearchMember extends DbQuery {
    String urlApi = mainURL + "/users/by/userID/:userID";


    public QuerySearchMember(Context context) {
        super(context);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        super.runProgressDialog("Searching...");
    }

    // Runs script to database
    @Override
    protected String doInBackground(String... params) {
        String id = params[0];
        int memberID = Integer.parseInt(id);
        try {
            super.connect(urlApi, "GET", true, true, createPostData(memberID));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Creating POST data to be sent to the api
    private JSONObject createPostData(int memberID) throws JSONException {
        JSONObject postData = new JSONObject();
        postData.put("memberID", memberID);
        return postData;
    }
}

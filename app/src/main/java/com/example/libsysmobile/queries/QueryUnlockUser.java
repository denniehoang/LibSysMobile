package com.example.libsysmobile.queries;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

public class QueryUnlockUser extends DbQuery {

    private String urlApi = "http://ec2-18-218-197-217.us-east-2.compute.amazonaws.com:8084/api/v1/users/:email/unlock";

    public QueryUnlockUser(Context context) {
        super(context);
    }


    // Displays process dialog to user indicating process in progress
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    // Runs script to database
    @Override
    protected String doInBackground(String... params) {
        String email = params[0];
        try {
            super.connect(urlApi, "POST", true, true, createPostData(email));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Creating POST data to be sent to the api
    private JSONObject createPostData(String email) throws JSONException {
        JSONObject postData = new JSONObject();
        postData.put("email", email);
        return postData;
    }
}

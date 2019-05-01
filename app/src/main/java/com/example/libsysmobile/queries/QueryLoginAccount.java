package com.example.libsysmobile.queries;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

public class QueryLoginAccount extends DbQuery {
    private String urlApi = "http://ec2-18-218-197-217.us-east-2.compute.amazonaws.com:8084/api/v1/users/login";

    public QueryLoginAccount(Context context) {
        super(context);
    }

    // Displays process dialog to user indicating process in progress
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        super.runProgressDialog("Logging in...");
    }

    // Runs php script to database
    @Override
    protected String doInBackground(String... params) {
        String email = params[0];
        String password = params[1];
        try {
            super.connect(urlApi, "POST", true, true, createPostData(email, password));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Creating POST data to be sent to the api
    private JSONObject createPostData(String email, String password) throws JSONException {
        JSONObject postData = new JSONObject();
        postData.put("email", email);
        postData.put("password", password);
        return postData;
    }
}



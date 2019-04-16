package com.example.libsysmobile.queries;

import android.content.Context;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

public class QueryLoginAccount extends DbQuery {
    private String filepath = "https://libsysmobile.000webhostapp.com/login.php";

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
        createPostData(params[0], params[1]);
        super.setPost_data(post_data);
        super.connect(filepath, "POST", true, true);
        return null;
    }

    // Creating POST data to be sent to the php
    public void createPostData(String email, String password) {
        try {
            this.post_data =
                    URLEncoder.encode("email", super.encodingType) + "=" + URLEncoder.encode(email, super.encodingType) + "&"
                            + URLEncoder.encode("password", super.encodingType) + "=" + URLEncoder.encode(password, super.encodingType);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}



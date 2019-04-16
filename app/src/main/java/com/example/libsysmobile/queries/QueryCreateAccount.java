package com.example.libsysmobile.queries;

import android.content.Context;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

public class QueryCreateAccount extends DbQuery {
    private String filepath = "https://libsysmobile.000webhostapp.com/createAccount.php";

    public QueryCreateAccount(Context context) {
        super(context);
    }

    // Displays process dialog to user indicating process in progress
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        super.runProgressDialog("Creating User Account...");
    }

    // Runs php script to database
    @Override
    protected String doInBackground(String... params) {
        createPostData(params[0], params[1], params[2], params[3]);
        super.setPost_data(post_data);
        super.connect(filepath, "POST", true, true);
        return null;
    }

    // Creating POST data to be sent to the php
    public void createPostData(String firstName, String lastName, String email, String password) {
        Date date = new Date();
        String modifiedDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
        try {
            this.post_data =
                    URLEncoder.encode("firstname", super.encodingType) + "=" + URLEncoder.encode(firstName, super.encodingType) + "&"
                            + URLEncoder.encode("lastname", super.encodingType) + "=" + URLEncoder.encode(lastName, super.encodingType) + "&"
                            + URLEncoder.encode("email", super.encodingType) + "=" + URLEncoder.encode(email, super.encodingType) + "&"
                            + URLEncoder.encode("password", super.encodingType) + "=" + URLEncoder.encode(password, super.encodingType) + "&"
                            + URLEncoder.encode("createdate", super.encodingType) + "=" + URLEncoder.encode(modifiedDate, super.encodingType) + "&"
                            + URLEncoder.encode("islocked", super.encodingType) + "=" + URLEncoder.encode("0", super.encodingType) + "&"
                            + URLEncoder.encode("accesslevel", super.encodingType) + "=" + URLEncoder.encode("Member", super.encodingType);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}



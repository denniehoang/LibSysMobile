package com.example.libsysmobile.pages;

import android.os.Bundle;
import android.view.View;

import com.example.libsysmobile.R;

import org.json.JSONException;
import org.json.JSONObject;

public class IssueBookPage extends Page {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.newPage(R.layout.issue_book);
    }

    public void issueBookOnClick(View view) {
        runQuery();
    }


    public void runQuery() {

    }

    @Override
    public void processFinish(JSONObject result) throws JSONException {
        String response = result.toString();
    }

}


package com.example.libsysmobile.pages;

import android.os.Bundle;
import android.view.View;

import com.example.libsysmobile.R;

import org.json.JSONException;
import org.json.JSONObject;

public class AssignLibrarianPage extends Page {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.newPage(R.layout.assign_librarian);
    }

    public void runQuery() {

    }

    @Override
    public void processFinish(JSONObject result) throws JSONException {
        String response = result.toString();
    }

    public void assignLibrarianOnClick(View view) {
        runQuery();
    }
}

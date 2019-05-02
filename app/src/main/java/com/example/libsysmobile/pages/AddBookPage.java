package com.example.libsysmobile.pages;

import android.os.Bundle;
import android.view.View;

import com.example.libsysmobile.R;

import org.json.JSONException;
import org.json.JSONObject;

public class AddBookPage extends Page {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.newPage(R.layout.add_book);
    }

    public void addBookOnClick(View view) {

    }

    public void runQuery() {

    }

    @Override
    public void processFinish(JSONObject result) throws JSONException {
        String response = result.toString();
    }
}

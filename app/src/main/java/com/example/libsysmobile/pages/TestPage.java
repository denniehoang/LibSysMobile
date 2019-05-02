package com.example.libsysmobile.pages;

import android.os.Bundle;

import com.example.libsysmobile.queries.DbQuery;
import com.example.libsysmobile.queries.QueryItemID;

import org.json.JSONException;
import org.json.JSONObject;

public class TestPage extends Page {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String itemInstanceID = "3";
        runQuery(itemInstanceID);
    }

    public void runQuery(String itemInstanceID) {
        DbQuery queryItemID = new QueryItemID(this);
        queryItemID.delegate = this;
        queryItemID.execute(itemInstanceID);
    }

    @Override
    public void processFinish(JSONObject result) throws JSONException {
        String response = result.toString();
    }

}

package com.example.libsysmobile.pages;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.example.libsysmobile.AsyncResponse;
import com.example.libsysmobile.Item;
import com.example.libsysmobile.Rental;
import com.example.libsysmobile.User;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class Page extends AppCompatActivity implements AsyncResponse {
    public static User currentUser;
    public static Item item;
    public static String token;
    public static Rental itemRental;
    public AlertDialog.Builder alertDialogBuilder;
    public AlertDialog alertDialog;

    protected void newPage(int pageLayout) {
        setContentView(pageLayout);
    }

    protected void changePage(Context thisPage, Class nextClassPage) {
        Intent nextIntent = new Intent(thisPage, nextClassPage);
        startActivity(nextIntent);
    }

    @Override
    public void processFinish(JSONObject result) throws JSONException {
    }


}

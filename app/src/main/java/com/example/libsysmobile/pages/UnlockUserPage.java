package com.example.libsysmobile.pages;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import com.example.libsysmobile.queries.DbQuery;
import com.example.libsysmobile.queries.QueryUnlockUser;
import com.example.libsysmobile.user_home_pages.AdminHomePage;

import org.json.JSONException;
import org.json.JSONObject;


public class UnlockUserPage extends Page {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TODO: CREATE UNLOCK USER PAGE
        // super.newPage(R.layout.);
    }

    public void unlockUserOnClick(View view) {
        //TODO: GET EMAIL ADDRESS FROM UI
        String emailAddress = "";
        runQuery(emailAddress);
    }

    public void runQuery(String emailAddress) {
        DbQuery queryUnlockUser = new QueryUnlockUser(this);
        queryUnlockUser.delegate = this;
        queryUnlockUser.execute(emailAddress);
    }

    @Override
    public void processFinish(JSONObject result) throws JSONException {
        String response = result.toString();
        alertDialogBuilder
                .setTitle("User Unlocked")
                .setMessage("Account has been unlocked and user may now access it.")
                .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        changePage(UnlockUserPage.this, AdminHomePage.class);
                    }
                });
        alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}

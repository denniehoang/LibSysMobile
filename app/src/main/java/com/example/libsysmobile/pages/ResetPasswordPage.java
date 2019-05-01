package com.example.libsysmobile.pages;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import com.example.libsysmobile.R;
import com.example.libsysmobile.queries.DbQuery;
import com.example.libsysmobile.queries.QueryResetPassword;

import org.json.JSONObject;

public class ResetPasswordPage extends Page {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.newPage(R.layout.login_reset_password_page);
        alertDialogBuilder = new AlertDialog.Builder(this);
    }


    public void resetPasswordOnClick(android.view.View view) {
        //TODO: Need to extract from edit text field when UI is created
        String email = "";
        runQuery(email);
    }

    public void runQuery(String emailAddress) {
        DbQuery queryResetPassword = new QueryResetPassword(this);
        queryResetPassword.delegate = this;
        queryResetPassword.execute(emailAddress);
    }


    @Override
    public void processFinish(JSONObject result) {
        String response = result.toString();
        alertDialogBuilder
                .setTitle("Password Reset")
                .setMessage("Please check your email for password reset confirmation.")
                .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        changePage(ResetPasswordPage.this, LoginPage.class);
                    }
                });
        alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

}


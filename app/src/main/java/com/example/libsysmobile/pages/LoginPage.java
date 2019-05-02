package com.example.libsysmobile.pages;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.libsysmobile.R;
import com.example.libsysmobile.User;
import com.example.libsysmobile.queries.DbQuery;
import com.example.libsysmobile.queries.QueryLoginAccount;
import com.example.libsysmobile.user_home_pages.LibrarianHomePage;
import com.example.libsysmobile.user_home_pages.MemberHomePage;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginPage extends Page {
    private EditText et_emailAddress;
    private EditText et_password;
    private boolean isValid;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.newPage(R.layout.login_libsys_login);
        alertDialogBuilder = new AlertDialog.Builder(this);
        et_emailAddress = findViewById(R.id.login_email);
        et_password = findViewById(R.id.login_password);
    }

    public void loginOnClick(View view) {
        String emailAddress = et_emailAddress.getText().toString();
        String password = et_password.getText().toString();
        /*
        TODO:
        Hardcoding login to use DELETE WHEN DONE
         */
        emailAddress = "group2.ics499@gmail.com";
        password = "HorseApple25";
        /*
         */

        isValid = isValidFormat(emailAddress, password);
        if (isValid) {
            runQuery(emailAddress, password);
        } else {
            alertDialogBuilder
                    .setTitle("Invalid Entry:")
                    .setMessage("Please enter a valid password and/or email address.")
                    .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            et_emailAddress.getText().clear();
                            et_password.getText().clear();
                            et_emailAddress.requestFocus();
                        }
                    });
            alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
    }

    public void createAccountOnClick(View view) {
        changePage(this, CreateAccountPage.class);
    }

    public void resetPasswordOnClick(View view) {
        changePage(LoginPage.this, ResetPasswordPage.class);
    }

    public void runQuery(String emailAddress, String password) {
        DbQuery queryLoginAccount = new QueryLoginAccount(this);
        queryLoginAccount.delegate = this;
        queryLoginAccount.execute(emailAddress, password);
    }

    @Override
    public void processFinish(JSONObject result) throws JSONException {
        String response = result.toString();
        token = result.get("token").toString();
        String accessLevel = result.get("accessLevel").toString();
        String memberID = result.get("memberID").toString();
        currentUser = new User(memberID);
        switch (accessLevel) {
            case ("member"):
                changePage(LoginPage.this, MemberHomePage.class);
                break;
            case ("librarian"):
                changePage(LoginPage.this, LibrarianHomePage.class);
                break;
            case ("admin"):
                //   changePage(LoginPage.this, AdminHomePage.class);
                // TODO: USING THIS TO ROUTE TO DIFFERENT PAGES
                changePage(LoginPage.this, MemberHomePage.class);
                break;
            default:
                alertDialogBuilder
                        .setTitle("Login Error:")
                        .setMessage("Account information does not exist.")
                        .setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                et_emailAddress.getText().clear();
                                et_password.getText().clear();
                                et_emailAddress.requestFocus();
                            }
                        });
                alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                    break;
        }
    }

    public boolean isValidFormat(String emailAddress, String password) {
        return !password.trim().equals("") &&
                !emailAddress.trim().equals("") &&
                emailAddress.contains("@") &&
                emailAddress.contains(".com") &&
                (password.length() > 3) &&
                (password.length() < 25);
    }
}

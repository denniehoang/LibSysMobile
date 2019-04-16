package com.example.libsysmobile.login_pages;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.libsysmobile.R;
import com.example.libsysmobile.queries.DbQuery;
import com.example.libsysmobile.queries.QueryLoginAccount;

import java.util.List;

public class LoginPage extends Page {
    private EditText et_emailAddress;
    private EditText et_password;
    private boolean isValid;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.newPage(R.layout.login_login_page, R.id.login_navigation, this.getClass().getSimpleName());
        alertDialogBuilder = new AlertDialog.Builder(this);
        et_emailAddress = findViewById(R.id.email_login_textField);
        et_password = findViewById(R.id.password_login_textField);
    }

    //performs action on Login Button Click on the Login Page
    public void loginOnClick(View view) {
        String emailAddress = et_emailAddress.getText().toString();
        String password = et_password.getText().toString();
      isValid = isValidFormat(emailAddress,password);
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

    public void runQuery(String emailAddress, String password) {
        DbQuery queryLoginAccount = new QueryLoginAccount(this);
        queryLoginAccount.delegate = this;
        queryLoginAccount.execute(emailAddress, password);
    }

    @Override
    public void processFinish(List<String> resultList) {
        int userID = Integer.parseInt(resultList.get(0));
        String accessLevel = resultList.get(1);
        decideTask(userID, accessLevel);
    }

    private void decideTask(int userID, String accessLevel) {
        if (userID < 0) {
            // Account does not exist
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
        } else {
            // Todo: Proceed to Home Page [Dependant on accessLevel]
            switch(accessLevel){
                case ("Member"):
                    // go to member home Page
                    break;
                case ("Librarian"):
                    // Go to librarian home page
                    break;
                case("Administrator"):
                    // go to admin home page
                    break;
            }
        }
    }

    public boolean isValidFormat(String emailAddress, String password) {
        if (
                password.trim().equals("") ||
                        emailAddress.trim().equals("") ||
                        !emailAddress.contains("@") ||
                        !emailAddress.contains(".com") ||
                        (password.length() <= 3) ||
                        (password.length() >= 8)
        ) {
            return false;
        } else {
            return true;
        }
    }
    }


















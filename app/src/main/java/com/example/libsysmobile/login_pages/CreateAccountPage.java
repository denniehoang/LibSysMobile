package com.example.libsysmobile.login_pages;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.AlteredCharSequence;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.libsysmobile.R;
import com.example.libsysmobile.queries.DbQuery;
import com.example.libsysmobile.queries.QueryCreateAccount;
import com.example.libsysmobile.queries.QueryLoginAccount;
import com.example.libsysmobile.user_home_pages.MemberHomePage;

import java.util.List;

public class CreateAccountPage extends Page {
    private EditText et_firstName;
    private EditText et_lastName;
    private EditText et_emailAddress;
    private EditText et_password;
    private DbQuery queryCreateAccount;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.newPage(R.layout.login_create_account_page, R.id.login_navigation, this.getClass().getSimpleName());
        alertDialogBuilder = new AlertDialog.Builder(this);
        et_firstName = findViewById(R.id.firstName_createAccount_textField);
        et_lastName = findViewById(R.id.lastName_createAccount_textField);
        et_emailAddress = findViewById(R.id.emailAddress_createAccount_textField);
        et_password = findViewById(R.id.password_createAccount_textField);
    }

    // Performs action on Create Account button on Create Account Page
    public void createAccountOnClick(View view) {
        String firstName = et_firstName.getText().toString();
        String lastName = et_lastName.getText().toString();
        String emailAddress = et_emailAddress.getText().toString();
        String password = et_password.getText().toString();
        runQuery(firstName, lastName, emailAddress, password);
    }

    // Performs the query function to the database for account creation
    private void runQuery(String firstName, String lastName, String emailAddress, String password) {
        queryCreateAccount = new QueryCreateAccount(this);
        queryCreateAccount.delegate = this;
        queryCreateAccount.execute(firstName, lastName, emailAddress, password);

    }

    // Gets the results from the query
    @Override
    public void processFinish(List<String> resultList) {
        int userID = Integer.parseInt(resultList.get(0));
        String accessLevel = resultList.get(1);
        decideTask(userID, accessLevel);
    }

    // Processes action dependant on query result
    private void decideTask(int user_id, String accessLevel) {
        queryCreateAccount.cancel(true);
        if (user_id < 0) {
            // user already exists, could not create account
            alertDialogBuilder
                    .setTitle("Create Account: Error")
                    .setMessage("Email Address already exists!\nPlease re-enter account information.")
                    .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            et_firstName.getText().clear();
                            et_lastName.getText().clear();
                            et_emailAddress.getText().clear();
                            et_password.getText().clear();
                            et_firstName.requestFocus();
                            alertDialog = alertDialogBuilder.create();
                            alertDialog.show();
                        }
                    });

        } else {
            //TODO: Login to Member Home Page
        }
    }
}

package com.example.libsysmobile.pages;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.libsysmobile.R;
import com.example.libsysmobile.queries.DbQuery;
import com.example.libsysmobile.queries.QueryCreateAccount;

import org.json.JSONException;
import org.json.JSONObject;

public class CreateAccountPage extends Page {
    private EditText et_firstName;
    private EditText et_lastName;
    private EditText et_emailAddress;
    private EditText et_password;
    private DbQuery queryCreateAccount;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        newPage(R.layout.login_create_account);
        alertDialogBuilder = new AlertDialog.Builder(this);
        et_emailAddress = findViewById(R.id.new_email);
        et_password = findViewById(R.id.new_password);
    }

    // Performs action on Create Account button on Create Account Page
    public void createAccountOnClick(View view) {
        String emailAddress = et_emailAddress.getText().toString();
        String password = et_password.getText().toString();
        runQuery(emailAddress, password);
    }

    public void signInOnClick(View view) {
        changePage(CreateAccountPage.this, LoginPage.class);
    }

    // Performs the query function to the database for account creation
    private void runQuery(String emailAddress, String password) {
        queryCreateAccount = new QueryCreateAccount(this);
        queryCreateAccount.delegate = this;
        queryCreateAccount.execute(emailAddress, password);
    }

    // Gets the results from the query
    @Override
    public void processFinish(JSONObject result) throws JSONException {
        String string = result.toString();
        String response = result.get("memberID").toString();
        int user_id = Integer.parseInt(response);
        decideTask(user_id);
    }

    // Processes action dependant on query result
    private void decideTask(int user_id) {
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
                        }
                    });

        } else {
            alertDialogBuilder
                    .setTitle("Create Account: Success!")
                    .setMessage("Please check your email for confirmation and continue to Login.")
                    .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            changePage(CreateAccountPage.this, LoginPage.class);
                        }
                    });

        }
        alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}

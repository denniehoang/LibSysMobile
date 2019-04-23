package com.example.libsysmobile.login_pages;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.libsysmobile.R;
import com.example.libsysmobile.user_home_pages.LibrarianHomePage;
import com.example.libsysmobile.user_home_pages.MemberHomePage;

public class Login extends AppCompatActivity{
    private EditText emailText;
    private EditText passwordText;
    private Button loginButton;
    private TextView createAccount;
    private TextView resetPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.libsys_login);

        emailText = (EditText) findViewById(R.id.login_email);
        passwordText = (EditText) findViewById(R.id.login_password);
        loginButton = (Button) findViewById(R.id.main_login_button);
        resetPassword = (TextView) findViewById(R.id.reset_password);
        createAccount = (TextView) findViewById(R.id.create_account);

        //code to bypass login authentication
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homePageIntent = new Intent(Login.this, LibrarianHomePage.class); //change class to test other user homepages
                startActivity(homePageIntent);
                finish();
            }
        });

        //create an account listener
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent createAccountIntent = new Intent(Login.this, CreateAccountPage.class);
                startActivity(createAccountIntent);
            }
        });

        //reset password listener
        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resetPasswordIntent = new Intent(Login.this, ResetPasswordPage.class);
                startActivity(resetPasswordIntent);
            }
        });

    }
    //end of onCreate()

}

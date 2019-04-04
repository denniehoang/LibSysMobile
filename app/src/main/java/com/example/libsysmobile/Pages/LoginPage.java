package com.example.libsysmobile.Pages;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.example.libsysmobile.R;
import com.example.libsysmobile.Users.Administrator;
import com.example.libsysmobile.Users.Librarian;
import com.example.libsysmobile.Users.Member;
import com.example.libsysmobile.Users.User;


public class LoginPage extends Page {

    private EditText emailEditText;
    private EditText passwordEditText;
    private String emailAddress;
    private String password;
    private User currentUser;
    private String firstName;
    private String lastName;
    private int userType;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.newPage(R.layout.login_login_page, R.id.login_navigation, this.getClass().getSimpleName());
    }

    //performs action on Login Button Click on the Login Page

    public void loginOnClick(View view) {
        emailEditText = findViewById(R.id.email_login_textField);
        passwordEditText = findViewById(R.id.password_login_textField);
        emailAddress = emailEditText.getText().toString();
        password = passwordEditText.getText().toString();

        queryLogin(emailAddress,password);

        // Bypassing the queryLogin to test pages
        login("memberF", "memberL", 0);
    }

    /*
    Validate/Search in SQL for proper email address and password
     */
    private void queryLogin(String emailAddress, String password) {
        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Login Status");

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://10.0.2.2:8084/api/v1/users";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        alertDialog.setMessage("Response is: "+ response);
                        alertDialog.show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                alertDialog.setMessage("That didn't work!");
                alertDialog.show();
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
        
        boolean userExists = true;
        if (userExists) {
            this.firstName = ""; //first name from SQL
            this.lastName = ""; //last name from SQL
            this.userType = 9999; // user type from SQL
            login(firstName, lastName, userType);
        } else {
            // user does not exist logic
        }
    }

    private void login(String firstName, String lastName, int userType) {
        switch (userType) {
            case 0:
                currentUser = new Member(firstName, lastName);
                break;
            case 1:
                currentUser = new Librarian(firstName, lastName);
                break;
            case 2:
                currentUser = new Administrator(firstName, lastName);
                break;
        }

    }
}


















/*
@Author: Thuy Hoang
@Project: LibSys Mobile [Library System]
@Course: ICS499: Senior Capstone Project

    This application will be used by Library Members, Librarians, and Administrators to access a Library System. Each user will have unique functions on the application to interact with the Library System.
 */
package com.example.libsysmobile;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.libsysmobile.login_pages.Login;
import com.example.libsysmobile.login_pages.LoginPage;
import com.example.libsysmobile.user_home_pages.LibrarianHomePage;
import com.example.libsysmobile.user_home_pages.MemberHomePage;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_page);
        Intent loginIntent = new Intent(MainActivity.this, Login.class);
        startActivity(loginIntent);

    }
}

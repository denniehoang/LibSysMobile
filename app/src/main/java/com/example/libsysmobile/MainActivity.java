/*
@Author: Thuy Hoang
@Project: LibSys Mobile [Library System]
@Course: ICS499: Senior Capstone Project

    This application will be used by Library Members, Librarians, and Administrators to access a Library System. Each user will have unique functions on the application to interact with the Library System.
 */
package com.example.libsysmobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.libsysmobile.pages.LoginPage;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_page);
        Intent loginIntent = new Intent(MainActivity.this, LoginPage.class);
        startActivity(loginIntent);

    }
}

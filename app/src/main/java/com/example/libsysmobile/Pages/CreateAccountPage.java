package com.example.libsysmobile.Pages;

import android.os.Bundle;

import com.example.libsysmobile.R;

public class CreateAccountPage extends Page {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.newPage(R.layout.login_create_account_page, R.id.login_navigation,this.getClass().getSimpleName());
    }
}

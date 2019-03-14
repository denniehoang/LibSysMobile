package com.example.libsysmobile.Pages;

import android.os.Bundle;

import com.example.libsysmobile.R;

public class ResetPasswordPage extends Page {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.newPage(R.layout.login_reset_password_page, R.id.login_navigation, this.getClass().getSimpleName());
    }
}

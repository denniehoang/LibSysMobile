package com.example.libsysmobile.user_home_pages;

import android.os.Bundle;
import android.view.View;

import com.example.libsysmobile.R;
import com.example.libsysmobile.pages.AssignLibrarianPage;
import com.example.libsysmobile.pages.IssueFinePage;
import com.example.libsysmobile.pages.Page;
import com.example.libsysmobile.pages.SetFinePage;
import com.example.libsysmobile.pages.UnlockUserPage;

public class AdminHomePage extends Page {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page_admin);
    }

    public void unlockUserOnClick(View view) {
        changePage(this, UnlockUserPage.class);

    }

    public void assignLibrarianOnClick(View view) {
        changePage(this, AssignLibrarianPage.class);

    }

    public void setFineOnClick(View view) {
        changePage(this, SetFinePage.class);

    }

    public void issueFineOnClick(View view) {
        changePage(this, IssueFinePage.class);

    }


}


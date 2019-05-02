package com.example.libsysmobile.pages;

import android.os.Bundle;
import android.view.View;

import com.example.libsysmobile.R;

public class ManageUserPage extends Page {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.newPage(R.layout.librarian_manage_user);
    }


    public void issueBookOnClick(View view) {
        changePage(this, IssueBookPage.class);

    }

    public void returnBookOnClick(View view) {
        changePage(this, ReturnBookPage.class);

    }

    public void payBalanceOnClick(View view) {
        changePage(this, PayBalancePage.class);

    }

    public void issueFineOnClick(View view) {
        changePage(this, IssueFinePage.class);

    }

}

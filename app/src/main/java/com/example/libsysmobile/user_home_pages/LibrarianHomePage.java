package com.example.libsysmobile.user_home_pages;

import android.os.Bundle;
import android.view.View;

import com.example.libsysmobile.R;
import com.example.libsysmobile.pages.AdjustInventoryPage;
import com.example.libsysmobile.pages.ManageUserPage;
import com.example.libsysmobile.pages.Page;
import com.example.libsysmobile.pages.SearchBookPage;
import com.example.libsysmobile.pages.SearchMemberPage;

public class LibrarianHomePage extends Page {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page_librarian);
    }

    // Handles Search Member
    public void searchMemberOnClick(View view) {
        changePage(this, SearchMemberPage.class);
    }

    public void searchBookOnClick(View view) {
        changePage(this, SearchBookPage.class);
    }

    public void adjustInventoryOnClick(View view) {
        changePage(this, AdjustInventoryPage.class);
    }

    public void manageUserOnClick(View view) {
        changePage(this, ManageUserPage.class);
    }
}

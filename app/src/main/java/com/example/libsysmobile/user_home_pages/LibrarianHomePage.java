package com.example.libsysmobile.user_home_pages;

import android.os.Bundle;
import android.view.View;

import com.example.libsysmobile.R;
import com.example.libsysmobile.pages.Page;
import com.example.libsysmobile.pages.SearchBookPage;

public class LibrarianHomePage extends Page {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page_librarian);
    }

    // Handles Search Member
    public void searchMemberOnClick(View view) {

    }

    public void searchBookOnClick(View view) {
        changePage(this, SearchBookPage.class);
    }
    public void adjustInventoryOnClick(View view) {

    }

    public void addBookOnClick(View view) {

    }

    public void removeBookOnClick(View view) {

    }

    public void manageUserOnClick(View view) {

    }
}

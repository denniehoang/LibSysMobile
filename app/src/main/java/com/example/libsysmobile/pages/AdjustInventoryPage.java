package com.example.libsysmobile.pages;

import android.os.Bundle;
import android.view.View;

import com.example.libsysmobile.R;

public class AdjustInventoryPage extends Page {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.newPage(R.layout.adjust_inventory);
    }

    public void addBookOnClick(View view) {
        changePage(this, AddBookPage.class);

    }

    public void removeBookOnClick(View view) {
        changePage(this, RemoveBookPage.class);

    }
}

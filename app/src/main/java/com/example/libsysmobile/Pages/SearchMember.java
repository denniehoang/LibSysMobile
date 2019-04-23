package com.example.libsysmobile.Pages;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;

import com.example.libsysmobile.R;
import com.example.libsysmobile.login_pages.Page;

import static com.example.libsysmobile.R.layout.search_member;

public class SearchMember extends Page {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(search_member);
    }
}

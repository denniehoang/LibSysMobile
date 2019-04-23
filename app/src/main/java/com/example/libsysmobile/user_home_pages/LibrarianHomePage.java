package com.example.libsysmobile.user_home_pages;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.libsysmobile.Pages.SearchMember;
import com.example.libsysmobile.R;
import com.example.libsysmobile.login_pages.Page;

public class LibrarianHomePage extends AppCompatActivity {
    private ImageButton searchMemberButton;
//    private ImageButton searchBook;
//    private ImageButton addBook;
//    private ImageButton removebook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page_librarian);
    }

    private void openSearchMember() {
        Intent intent = new Intent(this, SearchMember.class);
        startActivity(intent);
    }
}
